import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddPanel {
    JPanel editPanel = new JPanel(new BorderLayout());
    JPanel startDay = dateSelector();
    JPanel endDay = dateSelector();
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel addTemplate;
    Calendar cal = Calendar.getInstance(); //현재날짜
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH)+1;
    int date = cal.get(Calendar.DATE);
    JTextField nameInput = new JTextField(15);
    JTextField descriptionInput = new JTextField(15);
    String nowEditingName;
    JComboBox<Integer> yearCombo = new JComboBox<Integer>();
    DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
    JComboBox<Integer> monthCombo = new JComboBox<Integer>();
    DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
    JComboBox<Integer> dayCombo = new JComboBox<Integer>();
    DefaultComboBoxModel<Integer> dayModel = new DefaultComboBoxModel<Integer>();
    
    public AddPanel(ActionListener listener, int month_, int day_) {
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        // 상단 바
        JPanel upperPanel = new JPanel(new BorderLayout());

        String editPanelName = year + "년 " + month_ + "월 " + day_ + "일 이벤트 추가";
        
        JLabel showDay = new JLabel(editPanelName, JLabel.CENTER);
        JButton backEvent = new JButton("🔙");
        backEvent.addActionListener(listener);
        backEvent.setActionCommand("EventPanel");
        JButton emptyButton = new JButton();
        emptyButton.setEnabled(false);
        emptyButton.setOpaque(false);
        emptyButton.setBackground(new Color(0, 0, 0, 0));
        
        upperPanel.add(BorderLayout.CENTER, showDay);
        upperPanel.add(BorderLayout.WEST, emptyButton);
        upperPanel.add(BorderLayout.EAST, backEvent);
		upperPanel.setBackground(Color.decode("#B0C4DE"));
        upperPanel.setPreferredSize(new Dimension(0, 39));

        editPanel.add(upperPanel, BorderLayout.NORTH);

        JPanel editMain = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy++;

        //nameInput.setSize(100, 50);

        // 하단 수정 / 삭제
        JPanel buttonArea = new JPanel(new FlowLayout());
        JButton editButton = new JButton("추가");
        editButton.addActionListener(listener);
        editButton.setActionCommand("AddEventClicked");
        buttonArea.add(editButton);

        editMain.add(new JLabel("이벤트 이름"), gbc);
        gbc.gridy++;
        editMain.add(nameInput, gbc);
        gbc.gridy++;
        editMain.add(new JLabel("이벤트 설명"), gbc);
        gbc.gridy++;
        editMain.add(descriptionInput, gbc);
        gbc.gridy++;
        editMain.add(new JLabel("시작"), gbc);
        gbc.gridy++;
        startDay = dateSelector(year, month_, day_);
        editMain.add(startDay, gbc);
        gbc.gridy++;
        //endDay = dateSelector(year, month_, day_);
        //editMain.add(new JLabel("종료"), gbc);
        //gbc.gridy++;
        //editMain.add(endDay, gbc);
        //gbc.gridy++;
        editMain.add(buttonArea, gbc);
        editPanel.add(editMain);
    }

    private JPanel dateSelector() {
        cal = Calendar.getInstance();
        JComboBox<Integer> yearCombo = new JComboBox<Integer>();
        DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
        JComboBox<Integer> monthCombo = new JComboBox<Integer>();
        DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
        JComboBox<Integer> dayCombo = new JComboBox<Integer>();
        DefaultComboBoxModel<Integer> dayModel = new DefaultComboBoxModel<Integer>();

		//년
		for(int i=year-100; i<=year+50; i++){
			yearModel.addElement(i);
		}
		
		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year);
		
		//월
		for(int i=1; i<=12; i++) {
			monthModel.addElement(i);
		}
		monthCombo.setModel(monthModel);
		monthCombo.setSelectedItem(month);

        // 일
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i=1; i<=lastDay; i++)
            dayModel.addElement(i);
        dayCombo.setModel(dayModel);
        dayCombo.setSelectedItem(date);

        JPanel dateSelect = new JPanel();
        dateSelect.add(yearCombo);
        dateSelect.add(monthCombo);
        dateSelect.add(dayCombo);

        return dateSelect;
    }
    public JPanel dateSelector(int year_, int month_, int day_) {

		//년
		for(int i=year-100; i<=year+50; i++){
			yearModel.addElement(i);
		}
		
		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year_);
		
		//월
		for(int i=1; i<=12; i++) {
			monthModel.addElement(i);
		}
		monthCombo.setModel(monthModel);
		monthCombo.setSelectedItem(month_);

        // 일
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i=1; i<=lastDay; i++)
            dayModel.addElement(i);
        dayCombo.setModel(dayModel);
        dayCombo.setSelectedItem(day_);

        JPanel dateSelect = new JPanel();
        dateSelect.add(yearCombo);
        dateSelect.add(monthCombo);
        dateSelect.add(dayCombo);

        return dateSelect;
    }

    public JPanel getPanel() {
        return editPanel;
    }
}
