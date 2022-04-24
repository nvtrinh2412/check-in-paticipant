package PaticipantCheckSystem.General;

import javax.swing.*;
import java.awt.*;

public class ConfigurationDialog {
    public static final int HEIGHT = 800;
    public static final int WIDTH = 600;

    public static void installBasicConfiguration(JDialog dialog, String title, Container container) {
        dialog.setTitle(title);
        dialog.setSize(ConfigurationDialog.WIDTH, ConfigurationDialog.HEIGHT);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(container);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }
}
