import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InvitePanel {
    JPanel invitePanel = new JPanel(new GridBagLayout());

    public InvitePanel() {
        JLabel titleLabel = new JLabel("구성원 추가");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        invitePanel.add(titleLabel, gbc);
        gbc.gridy++;
        gbc.gridy++;

        for (int i=0; i<3; i++) {
            JPanel person = new JPanel(new FlowLayout());

            JLabel personName = new JLabel("◼︎ Person " + i);
            JButton personButton = new JButton("⏍");
            //evntButton.setSize(10, 10);
            //evntName.setSize(20, 10);

            person.add(personName);
            person.add(personButton);
            //evnt.setSize(10, 5);

            invitePanel.add(person, gbc);
            gbc.gridy++;
        }
        JButton addButton = new JButton("➕ 구성원 초대");
        invitePanel.add(addButton, gbc);
    }

    public JPanel getPanel() {
        return invitePanel;
    }
}
