package PaticipantCheckSystem.StudentScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;

import javax.swing.*;

public class CheckInResult extends JDialog{
    private JComboBox comboBoxSubject;
    private JList listCheckInResult;
    private JPanel panelCheckInResult;

    public CheckInResult(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Student Menu", panelCheckInResult);
    }
}
