import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EventPanel {
    JPanel eventPanel = new JPanel(new BorderLayout());
    Calendar cal = Calendar.getInstance();
    int eventNum;
    String selectedDay = cal.get(Calendar.DATE) + "일";
    

    public EventPanel(ActionListener listener) {
        eventPanel.removeAll();
        int monthNow = cal.get(Calendar.MONTH);
        int today = cal.get(Calendar.DATE);
        // 상단 바
        JPanel upperPanel = getUpperPanel(listener, monthNow, today);
        eventPanel.add(upperPanel, BorderLayout.NORTH);
        // 이벤트 리스트
        JScrollPane eventList = loadEvent(monthNow, today, listener);
        eventPanel.add(eventList, BorderLayout.CENTER);
    }
    public JPanel getUpperPanel(ActionListener listener, int month, int date) {

        JPanel upperPanel = new JPanel(new BorderLayout());
        JLabel showDay = new JLabel(month+"월"+" "+date+"일", JLabel.CENTER);

        JButton eventManagement = new JButton("🔔");
        eventManagement.addActionListener(listener);
        eventManagement.setActionCommand("EventManagement");
        JButton userManagement = new JButton("👤");
        userManagement.addActionListener(listener);
        userManagement.setActionCommand("UserManagement");
    
        upperPanel.add(BorderLayout.CENTER, showDay);
        upperPanel.add(BorderLayout.WEST, userManagement);
        upperPanel.add(BorderLayout.EAST, eventManagement);
		upperPanel.setBackground(Color.decode("#B0C4DE"));
        upperPanel.setPreferredSize(new Dimension(0, 39));

        return upperPanel;
    }

    // 해당 날짜의 이벤트 불러와서 그에 맞는 JPanel 생성
    public JScrollPane loadEvent(int selectedMonth, int selectedDay, ActionListener listener) {
        int eventNum = 3;  // 데이터베이스에 연결, 이벤트 개수 불러와야 함
        //event.add(scroll);
        JPanel event = new JPanel();
        event.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        for (int i=0; i<eventNum; i++) {
            JPanel evnt = new JPanel(new FlowLayout());

            JLabel evntName = new JLabel("◼︎ Event");
            JButton evntButton = new JButton("⏍");
            evntButton.addActionListener(listener);
            evntButton.setActionCommand("ModifyEvent");
            //evntButton.setSize(10, 10);
            //evntName.setSize(20, 10);

            evnt.add(evntName);
            evnt.add(evntButton);
            //evnt.setSize(10, 5);

            event.add(evnt, gbc);
            gbc.gridy++;
        }
        JButton addButton = new JButton("➕ 이벤트 추가");
        
        addButton.setBackground(Color.decode("#6eaa5e"));
        event.add(addButton, gbc);

        JScrollPane scroll = new JScrollPane(event);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scroll;
    }

    /* 
    private JPanel eventMaker(String eventName) {
        JPanel event = new JPanel(new BorderLayout());
        event.add(BorderLayout.WEST, new JLabel(eventName));
        
    }
    */

    public void dayChange(int month, int day, ActionListener listener) {
        eventPanel.removeAll();
        // 상단 바
        JPanel upperPanel = getUpperPanel(listener, month, day);
        eventPanel.add(upperPanel, BorderLayout.NORTH);
        // 이벤트 리스트
        JScrollPane eventList = loadEvent(month, day, listener);
        eventPanel.add(eventList, BorderLayout.CENTER);
    }


    public JPanel getPanel() {
        return eventPanel;
    }
}
