package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import daos.SubjectDAO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListScheduleScreen extends JDialog {
    private JPanel panelListSchedule;
    private JComboBox comboBoxSubject;
    private JList scheduleList;
    private JButton btnSelect;

    public ListScheduleScreen(JFrame parent) {
        super(parent);
        createUIComponents();

        HashMap<String, String> subjectList = SubjectDAO.getSubjectList();
        List<String> subjectNameList = new ArrayList<String>(subjectList.values());

        comboBoxSubject.setModel(new DefaultComboBoxModel(subjectNameList.toArray()));
        scheduleList.setModel(new DefaultListModel());

    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"List Schedule", panelListSchedule);
    }
}
