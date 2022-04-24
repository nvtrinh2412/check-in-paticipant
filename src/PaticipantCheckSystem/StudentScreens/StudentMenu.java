package PaticipantCheckSystem.StudentScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMenu extends JDialog {
    private JButton btnCheckIn;
    private JButton btnViewResult;
    private JPanel panel1;
    private JPanel panelStudentMenu;

    public StudentMenu(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
        btnCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckInScreen(null);
            }
        });
        btnViewResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckInResult(null);
            }
        });
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Student Menu", panelStudentMenu);
    }
}
