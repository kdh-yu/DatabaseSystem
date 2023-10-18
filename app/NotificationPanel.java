import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationPanel {
    JPanel notificationPanel = new JPanel(new GridBagLayout());

    public NotificationPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        for (int i=0; i<3; i++) {
            JPanel person = new JPanel(new FlowLayout());

            JLabel personName = new JLabel("◼︎ " + i + " 번째 알림입니다.");
            JButton personButton = new JButton("x");
            //evntButton.setSize(10, 10);
            //evntName.setSize(20, 10);

            person.add(personName);
            person.add(personButton);
            //evnt.setSize(10, 5);

            notificationPanel.add(person, gbc);
            gbc.gridy++;
        }
    }

    public JPanel getPanel() {
        return notificationPanel;
    }
}
