package PaticipantCheckSystem.ManagerScreens;

import PaticipantCheckSystem.General.ConfigurationDialog;
import PaticipantCheckSystem.General.UserSession;
import daos.SubjectDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateSubjectScreen extends JDialog{
    private JPanel labelCreateSubject;
    private JTextField textSubjectID;
    private JTextField textSubjectName;
    private JButton btnCreate;
    private JButton btnCancel;
    private JPanel inputArea;


    public CreateSubjectScreen(JFrame parent) {
        super(parent);
        setLocationRelativeTo(parent);
        createUIComponents();
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String subjectID = textSubjectID.getText();
                String subjectName = textSubjectName.getText();
                Integer creatorID = UserSession.getUserID();

                // Check if subjectID is empty
                if (subjectID.equals("") || subjectName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Check if subjectID is already existed
                    try{
                        SubjectDAO.createSubject(subjectID, subjectName,creatorID );
                        JOptionPane.showMessageDialog(null, "Subject created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    catch (Exception ex){
                        // Check if subjectID is already existed
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Subject already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void createUIComponents() {
        ConfigurationDialog.installBasicConfiguration(this, "Create new subject", labelCreateSubject);
    }
}
