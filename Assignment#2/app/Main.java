import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener {
    public boolean login = false;
    private JPanel currentPanel;
    private JPanel calendarPanel = new CalendarPanel(this).getPanel();
    private JPanel eventPanel = new EventPanel(this).getPanel();
    private JPanel loginPanel = new LoginPanel(this).getPanel();
    private JPanel editPanel = new EditPanel(this).getPanel();


    public Main() {  // 시작시 로그인 화면 띄움
        setLayout(new BorderLayout());
        // 로그인 창 띄우기
        currentPanel = loginPanel;
        getContentPane().add(currentPanel);

        //각종 명령어
        setTitle("데이터베이스시스템 달력");
        setVisible(true);
        setSize(1000,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Login") {
            getContentPane().removeAll();
            currentPanel = new JPanel(new GridLayout(1, 2));
            currentPanel.add(calendarPanel);
            currentPanel.add(eventPanel);

            getContentPane().add(currentPanel);
        }

        else if (e.getActionCommand() == "Register") {
            new RegisterFrame();
        }
        else if (e.getActionCommand() == "ModifyEvent") {
            currentPanel.remove(1);
            currentPanel.add(editPanel);
        }
        else if (e.getActionCommand() == "EventPanel") {
            currentPanel.remove(1);
            currentPanel.add(eventPanel);
        }
        else if ((e.getActionCommand().indexOf("DaySelect")) != -1) {
            String day = e.getActionCommand().substring(9);
            System.out.println(day);
        }
        else if (e.getActionCommand() == "UserManagement") {
            new InviteFrame();
        }
        else if (e.getActionCommand() == "EventManagement") {
            new NotificationFrame();
        }

        revalidate();
        repaint();
    }


	//실헹메소드
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
	}
}