package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMenu extends JDialog{
    private JButton btnArrangeSchedule;
    private JButton btnAddStudent;
    private JButton btnCheckResult;
    private JButton btnAddSubject;
    private JPanel panelManagerMenu;

    public ManagerMenu(JFrame parent){
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();

        btnAddSubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateSubjectScreen(null);
            }
        });
        btnArrangeSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DetailSchedule(null);
            }
        });

        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentScreen(null);
            }
        });

        btnCheckResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AttendantResult(null);
            }
        });
    }
      public void createUIComponents() {
//        setTitle("Login");
//        setSize(Configuration.WIDTH, Configuration.HEIGHT);
//        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        setContentPane(managerMenu);
//        setVisible(true);

        ConfigurationDialog.installBasicConfiguration(this,"Manager Menu",panelManagerMenu);
    }

    public static void main(String[] args) {
        ManagerMenu managerMenu = new ManagerMenu(null);
    }

}
