package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import daos.AttendantDAO;
import daos.SubjectDAO;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManagerCheckInResult extends JDialog{
    private JTable tableAttendantResult;
    private JPanel panelAttendantResult;
    private JButton btnCheck;
    private JComboBox<String> comboBoxSubject;
    private JFrame frame;

    public ManagerCheckInResult(JFrame parent){
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();

        //set up the table

        String[] columnNames = {"Attendant ID","Attendant Name",
                                "Week 1" , "Week 2", "Week 3", "Week 4", "Week 5",
                                "Week 6", "Week 7", "Week 8", "Week 9", "Week 10",
                                "Week 11", "Week 12", "Week 13", "Week 14", "Week 15",};



        //set up combo box subject
        HashMap<String, String> subjectList = SubjectDAO.getSubjectList();
        List<String> subjectNameList = new ArrayList<String>(subjectList.keySet());
        comboBoxSubject.setModel(new DefaultComboBoxModel(subjectNameList.toArray()));

        comboBoxSubject.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String subjectName = (String) comboBoxSubject.getSelectedItem();
                String subjectID = subjectList.get(subjectName);
                String[][] studentList = AttendantDAO.getCheckInResult(subjectID);
            }
        });
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Attendant Result",panelAttendantResult);
    }
}
