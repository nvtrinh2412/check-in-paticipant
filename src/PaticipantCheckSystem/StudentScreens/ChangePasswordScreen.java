package PaticipantCheckSystem.StudentScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import PaticipantCheckSystem.General.HashPassword;
import PaticipantCheckSystem.General.UserSession;
import daos.LoginDAO;
import pojo.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordScreen extends JDialog{
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnChangePassword;
    private JPanel panelChangePassword;

    public ChangePasswordScreen(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());
                Account currentAccount = UserSession.getInstance();
                String username = currentAccount.getUsername();
                String oldPassword = currentAccount.getPassword();
                if (HashPassword.hash(newPassword).equals(oldPassword)){
                    JOptionPane.showMessageDialog(ChangePasswordScreen.this,"New password must be different from old password");
                }
                else if(newPassword.equals(confirmPassword)){
                    LoginDAO.changePassword(username,newPassword);
                    LoginDAO.changeValidStatus(username,true);
                    JOptionPane.showMessageDialog(ChangePasswordScreen.this,"Password changed successfully");
                    new StudentMenu(null);
                    dispose();
                }
                else if (newPassword.equals("") || confirmPassword.equals("")){
                    JOptionPane.showMessageDialog(ChangePasswordScreen.this,"Please enter password");
                }

                else{
                    JOptionPane.showMessageDialog(ChangePasswordScreen.this, "Password not match");
                }
            }
        });
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this,"Student Menu", panelChangePassword);
    }

}
