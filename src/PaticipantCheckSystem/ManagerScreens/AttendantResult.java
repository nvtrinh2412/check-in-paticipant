package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;

import javax.swing.*;

public class AttendantResult extends JDialog{
    private JTable tableAttendantResult;
    private JPanel panelAttendantResult;

    public AttendantResult(JFrame parent){
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Attendant Result",panelAttendantResult);
    }
}
