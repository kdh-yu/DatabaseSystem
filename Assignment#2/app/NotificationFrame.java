import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationFrame extends JFrame {
    JPanel invitePanel = new JPanel(new GridBagLayout());

    public NotificationFrame() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;

        for (int i=0; i<3; i++) {
            JPanel person = new JPanel(new FlowLayout());

            JLabel personName = new JLabel("◼︎ Alarm " + i);
            JButton personButton = new JButton("⏍");
            //evntButton.setSize(10, 10);
            //evntName.setSize(20, 10);

            person.add(personName);
            person.add(personButton);
            //evnt.setSize(10, 5);

            invitePanel.add(person, gbc);
            gbc.gridy++;
        }
        JButton removeButton = new JButton("🗑️");
        invitePanel.add(removeButton, gbc);
        add(invitePanel);        
        setTitle("알림");
        setVisible(true);
        setSize(500,200);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JPanel getPanel() {
        return invitePanel;
    }
}
