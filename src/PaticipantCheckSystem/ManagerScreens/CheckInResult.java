package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;

import javax.swing.*;

public class CheckInResult extends JDialog{
    private JTable tableAttendantResult;
    private JPanel panelAttendantResult;

    public CheckInResult(JFrame parent){
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Attendant Result",panelAttendantResult);
    }
}
