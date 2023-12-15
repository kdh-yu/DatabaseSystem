import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarMonthPanel {
    ActionListener listener;
    // Calendar Panel
    JPanel calendarPanel = new JPanel(new BorderLayout());

	Calendar cal;
	public int year, month, date;
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
}
