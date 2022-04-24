package PaticipantCheckSystem.StudentScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;

import javax.swing.*;

public class CheckInScreen extends JDialog{
    private JComboBox comboBoxSubject;
    private JButton btnCheckIn;
    private JPanel comboBoxClass;
    private JComboBox comboBoxWeekDay;
    private JPanel panelCheckIn;

    public CheckInScreen(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Check in",panelCheckIn);
    }
}
