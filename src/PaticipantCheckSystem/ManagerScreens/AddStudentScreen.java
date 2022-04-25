package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import daos.ClassDAO;
import daos.StudentDAO;
import daos.SubjectDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.*;

public class AddStudentScreen extends JDialog{
    private JComboBox<String> comboBoxSubject;
    private JButton btnAddToClass;
    private JLabel labelSubject;
    private JList<Class<?>> listStudent;
    private JComboBox<String> comboBoxClass;
    private JPanel panelAddStudent;
    private JTable tableStudentList;

    public AddStudentScreen(JFrame parent){
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();


        //GET ROOM LIST AND SUBJECT LIST
        HashMap<String,String> subjectMap = SubjectDAO.getSubjectList();
        Set<String> classMap = ClassDAO.getClassList();
        String[] subjectList = subjectMap.keySet().toArray(new String[0]);
        String[] classList = classMap.toArray(new String[0]);
        comboBoxSubject.setModel(new DefaultComboBoxModel<>(subjectList));
        comboBoxClass.setModel(new DefaultComboBoxModel<>(classList));
        //Table student's name
        String[] header = {"Student ID","Student Name","Select"};
        HashMap<String, Integer> studentMap = StudentDAO.getStudentList();
        String[][] studentList = StudentDAO.convertHashmap2ArrayList(studentMap);
        Arrays.sort(studentList, (o1, o2) -> o1[0].compareTo(o2[0]));
        DefaultTableModel tableModel = new DefaultTableModel(){
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0,1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                }
                return String.class;
            }
        };
        tableModel.setColumnIdentifiers(header);
        tableStudentList.setModel(tableModel);
        tableModel.setDataVector(studentList,header);
        JTableHeader headerStudentList = tableStudentList.getTableHeader();
        headerStudentList.setBackground(new java.awt.Color(185,242,215));
        List<Integer> selectedStudentList = new ArrayList<Integer>();

        // Add Action when checkbox is selected
        tableStudentList.getModel().addTableModelListener(e -> {
            if(e.getColumn() == 2){
                int row = e.getFirstRow();
                Boolean checkedStatus = (Boolean) tableStudentList.getValueAt(row,2);
                Integer studentID = Integer.parseInt(tableStudentList.getValueAt(row,0).toString());
                actionWithSelectedList(studentID,selectedStudentList,checkedStatus);
                System.out.println(selectedStudentList);
            }
        });

        // Add Action when OK button is clicked
        btnAddToClass.addActionListener(e -> {
            if(comboBoxClass.getSelectedItem() == null ){
                JOptionPane.showMessageDialog(this,"Please select class ");
            }
            else if (comboBoxSubject.getSelectedItem() == null){
                JOptionPane.showMessageDialog(this,"Please select subject");
            }
            else if(selectedStudentList.size() == 0){
                JOptionPane.showMessageDialog(this,"Please select student");
            }
            else {
                String className = Objects.requireNonNull(comboBoxClass.getSelectedItem()).toString();
                String classID = comboBoxClass.getSelectedItem().toString();
                String subjectName = Objects.requireNonNull(comboBoxSubject.getSelectedItem()).toString();
                String subjectID = subjectMap.get(subjectName);
                ClassDAO.addStudentToClass(selectedStudentList,subjectID,classID);
                JOptionPane.showMessageDialog(this,"Add student to class successfully");
            }
        });

    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Add Student",panelAddStudent);

    }

    public static void actionWithSelectedList(Integer studentID, List<Integer> selectedStudentList, Boolean status){
        if(status){ // add student to class
            selectedStudentList.add(studentID);
        }else{ // remove student from class (Uncheck)
            selectedStudentList.remove(studentID);
        }
    }
}
