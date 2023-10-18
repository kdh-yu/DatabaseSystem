import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

public class EditPanel {
    JPanel editPanel = new JPanel(new BorderLayout());
    JPanel addTemplate;
    Calendar cal = Calendar.getInstance(); //현재날짜
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH)+1;
    int date = cal.get(Calendar.DATE);

    public EditPanel() {
        // 상단 바
        JPanel upperPanel = new JPanel(new BorderLayout());
        
        JLabel showDay = new JLabel("수정", JLabel.CENTER);
        JButton eventManagement = new JButton("☰");
        JButton userManagement = new JButton("👤");
    
        upperPanel.add(BorderLayout.CENTER, showDay);
        upperPanel.add(BorderLayout.WEST, userManagement);
        upperPanel.add(BorderLayout.EAST, eventManagement);
		upperPanel.setBackground(Color.decode("#B0C4DE"));
        upperPanel.setPreferredSize(new Dimension(0, 39));

        editPanel.add(BorderLayout.NORTH, upperPanel);

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


        // 
        JPanel editMain = new JPanel();
        editMain.setLayout(new BoxLayout(editMain, BoxLayout.Y_AXIS));

        //eventName.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        editMain.add(new JLabel("이벤트 : "));
        addTemplate = new JPanel(new FlowLayout());
        JTextField nameInput = new JTextField(15);
        addTemplate.add(nameInput);
        //nameInput.setSize(100, 50);
        editMain.add(addTemplate);

        // 하단 수정 / 삭제
        JPanel buttonArea = new JPanel(new FlowLayout());
        JButton editButton = new JButton("수정");
        JButton deleteButton = new JButton("삭제");
        buttonArea.add(editButton);
        buttonArea.add(deleteButton);

        editMain.add(dateSelect);
        editMain.add(buttonArea);
        editPanel.add(editMain, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return editPanel;
    }
}
