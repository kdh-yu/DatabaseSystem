import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationFrame extends JFrame {
    JPanel invitePanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

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

    public NotificationFrame(ActionListener listener, Connection connection, String userid) {
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;
        int i=0;
        try {
            String select1Month = "SELECT * FROM calendar.event WHERE start_date >= CURRENT_DATE AND start_date <= CURRENT_DATE + INTERVAL '1 month' AND userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(select1Month);
            preparedStatement.setString(1, userid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JPanel event = new JPanel(new FlowLayout());
                JLabel eventName = new JLabel(rs.getString("name")+" "+rs.getString("start_date"));

                event.add(eventName);
                invitePanel.add(event, gbc);
                gbc.gridy++;
                i++;
            }
        } catch (Exception ex) {

        }
        if (i == 0) {
            JLabel emptylabel = new JLabel("텅");
            invitePanel.add(emptylabel);
        }
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
