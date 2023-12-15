import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener {
    // 항상 보여줄 패널
    private JPanel currentPanel;

    // 기존 패널 로드
    private CalendarPanel calendarPanel = new CalendarPanel(this);
    private EventPanel eventPanel = new EventPanel(this);
    private LoginPanel loginPanel = new LoginPanel(this);
    private RegisterPanel registerPanel = new RegisterPanel(this);

    public Main() {
        setLayout(new BorderLayout());
        // 시작시 로그인 창 띄우기
        currentPanel = loginPanel.getPanel();
        add(currentPanel);

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
            String loginID = loginPanel.sendID();
            String loginPassword = loginPanel.sendPassword();
            System.out.println(loginID + loginPassword);  // for test
            /*
             * 로그인 정보가 제대로 됐다면
             */
            getContentPane().removeAll();
            currentPanel = new JPanel(new GridLayout(1, 2));
            currentPanel.add(calendarPanel.getPanel());
            currentPanel.add(eventPanel.getPanel());

            getContentPane().add(currentPanel);

            /*
             * 로그인 정보가 틀렸다면
             * 경고 메시지 띄우기
             */
        }

        else if (e.getActionCommand() == "Register") {
            JFrame registerFrame = new JFrame();
            registerFrame.add(registerPanel.getPanel());
            registerFrame.setTitle("Register");
            registerFrame.setVisible(true);
            registerFrame.setSize(300,300);
            registerFrame.setResizable(false);
            registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            registerFrame.setLocationRelativeTo(null);

        }
        else if (e.getActionCommand() == "RegisterButtonClicked") {
            String inputName = registerPanel.getInputName();
            String inputID = registerPanel.getInputID();
            String inputPassword = registerPanel.getInputPassword();

            /*
             * 데이터베이스에 유저 정보 추가
             */
        }

        else if (e.getActionCommand() == "ModifyEvent") {
            currentPanel.remove(1);
            currentPanel.add(editPanel.getPanel());
        }
        else if (e.getActionCommand() == "EventPanel") {
            currentPanel.remove(1);
            currentPanel.add(eventPanel.getPanel());
        }
        else if ((e.getActionCommand().indexOf("DaySelect")) != -1) {
            int monthNow = calendarPanel.month;
            int day = Integer.parseInt(e.getActionCommand().substring(9));
            eventPanel.dayChange(monthNow, day, this);
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