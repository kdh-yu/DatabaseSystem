import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarPanel implements ActionListener {
	ActionListener listener;
    // Calendar Panel
    JPanel calendarPanel = new JPanel(new BorderLayout());

	Calendar cal;
	int year, month, date;
	JPanel pane = new JPanel();
	
    // Button
    JButton btn1 = new JButton("◀");  //이전버튼
    JButton btn2 = new JButton("▶"); //다음버튼
    
    // Label
    JLabel yearlb = new JLabel("년");
    JLabel monthlb = new JLabel("월");
    
    // Year, Month, Date
    JComboBox<Integer> yearCombo = new JComboBox<Integer>();
    DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
    JComboBox<Integer> monthCombo = new JComboBox<Integer>();
    DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
    
    // Day
    JPanel pane2 = new JPanel(new BorderLayout());
    JPanel title = new JPanel(new GridLayout(1, 7));
    String titleStr[] = {"일", "월", "화", "수", "목", "금", "토"};
    JPanel datePane = new JPanel(new GridLayout(0, 7));

    // 화면디자인
	public CalendarPanel(ActionListener listener) {
		this.listener = listener;
		cal = Calendar.getInstance(); //현재날짜
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;
		date = cal.get(Calendar.DATE);
		
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
		
		//월화수목금토일
		for(int i=0; i<titleStr.length; i++){
			JLabel lbl = new JLabel(titleStr[i], JLabel.CENTER);
			if(i == 0){
				lbl.setForeground(Color.red);
			}else if(i == 6){
				lbl.setForeground(Color.blue);
			}
			title.add(lbl);
		}
		//날짜 출력
		day(year, month);
		
		//----------------------------
		pane.add(btn1);
		pane.add(yearCombo);
		pane.add(yearlb);
		pane.add(monthCombo);
		pane.add(monthlb);
		pane.add(btn2);
        pane.setSize(0, 39);
		pane.setBackground(Color.decode("#B0C4DE"));
		calendarPanel.add(BorderLayout.NORTH, pane);
		pane2.add(title,"North");
		pane2.add(datePane);
		calendarPanel.add(BorderLayout.CENTER, pane2);
        
        
        //----------기능구현----------
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        yearCombo.addActionListener(this);
        monthCombo.addActionListener(this);

	}

	// 기능구현
	public void actionPerformed(ActionEvent e) {
		Object eventObj = e.getSource();
		if (eventObj instanceof JComboBox) {
			datePane.setVisible(false);	//보여지는 패널을 숨킨다.
			datePane.removeAll();	//라벨 지우기
			day((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
			datePane.setVisible(true);	//패널 재출력
		} else if(eventObj instanceof JButton) {
			JButton eventBtn = (JButton) eventObj;
			int yy = (Integer)yearCombo.getSelectedItem();
			int mm = (Integer)monthCombo.getSelectedItem();
			if(eventBtn.equals(btn1)){	//전달
				if(mm==1){
					yy--; mm=12;
				}else{
					mm--;
				}				
			}else if(eventBtn.equals(btn2)){	//다음달
				if(mm==12){
					yy++; mm=1;
				}else{
					mm++;
				}
			}
			yearCombo.setSelectedItem(yy);
			monthCombo.setSelectedItem(mm);
		}	
	}
	
	//날짜출력
	public void day(int year, int month) {
		Calendar date = Calendar.getInstance();//오늘날짜 + 시간
		date.set(year, month-1, 1);
		int week = date.get(Calendar.DAY_OF_WEEK);
		int lastDay = date.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//공백출력
		for(int space=1; space<week; space++) {
			datePane.add(new JLabel("\t"));
		}
		
		// 날짜 출력
		for (int day = 1; day <= lastDay; day++) {
			//JLabel lbl = new JLabel(String.valueOf(day), JLabel.CENTER);
            JButton lbl = new JButton();
			lbl.addActionListener(this.listener);
			lbl.setActionCommand("DaySelect" + day);
			cal.set(year, month-1, day);
			int Week = cal.get(Calendar.DAY_OF_WEEK);
			if (Week==1) {
				lbl.setForeground(Color.red);				
			} else if (Week==7) {
				lbl.setForeground(Color.BLUE);
			}
            String hasEvent = "ㅤ";
            if (day == 13) {
                hasEvent = "★";
            }
            //lbl.setBorderPainted(false);
            lbl.setText("<HTML><body style='text-align:center;'>" + day + "<br><br>" + hasEvent + "</body></HTML>");
			datePane.add(lbl);
		}
	}

    public JPanel getPanel() {
        return calendarPanel;
    }
}
