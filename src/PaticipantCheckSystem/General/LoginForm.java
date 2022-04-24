package PaticipantCheckSystem.General;

import PaticipantCheckSystem.ManagerScreens.ManagerMenu;
import PaticipantCheckSystem.StudentScreens.ChangePasswordScreen;
import PaticipantCheckSystem.StudentScreens.StudentMenu;
import daos.LoginDAO;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginForm extends JFrame{
    private JPanel panelLogin;
    private JButton btnLogin;
    private JTextField textUsername;
    private JPasswordField textPassword;
    private JLabel labelUsername;
    private JLabel labelPassword;

    public LoginForm() {
        setLocationRelativeTo(null);
        createUIComponents();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textUsername.getText();
                String password = new String(textPassword.getPassword());
                String hashedPassword = HashPassword.hash(password);
                String userRole = LoginDAO.authorizedAccount(username, hashedPassword);

                if (userRole.equals("Manager")) {
                    new ManagerMenu(null);
                    dispose();
                }
                else if (userRole.equals("Authorized Student")) {
                    new StudentMenu(null);
                    dispose();
                }
                else if (userRole.equals("Unauthorized Student")) {
                    new ChangePasswordScreen(null);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Username or Password is incorrect!");
                }

            }
        });
    }

    private void createUIComponents() {
        setTitle("Login");
        setSize(ConfigurationDialog.WIDTH, ConfigurationDialog.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelLogin);
        setLocationRelativeTo(null);
        setVisible(true);
    }





}
