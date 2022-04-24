package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import daos.StudentDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Arrays;
import java.util.HashMap;

public class AddStudentScreen extends JDialog{
    private JComboBox comboBoxSubject;
    private JButton btnAddToClass;
    private JLabel labelSubject;
    private JList listStudent;
    private JComboBox comboBoxClass;
    private JPanel panelAddStudent;
    private JTable tableStudentList;

    public AddStudentScreen(JFrame parent){
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();

        //Table student's name
        String header [] = {"Student ID","Student Name","Select"};
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

    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Add Student",panelAddStudent);

    }
}
