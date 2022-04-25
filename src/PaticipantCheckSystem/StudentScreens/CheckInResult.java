package PaticipantCheckSystem.StudentScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;

import javax.swing.*;

public class CheckInResult extends JDialog{
    private JComboBox<String> comboBoxSubject;
    private JPanel panelCheckInResult;
    private JTable tableCheckInResult;

    public CheckInResult(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Student Menu", panelCheckInResult);
    }
}
