import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {
    public boolean login = false;
    private JPanel currentPanel;

    public Main(int a) {  // 로그인
        setLayout(new BorderLayout());
        // 로그인 창 띄우기
        LoginPanel loginPanel = new LoginPanel();
        currentPanel = loginPanel.getPanel();
        getContentPane().add(currentPanel);

        //각종 명령어
        setTitle("데이터베이스시스템 달력");
        setVisible(true);
        setSize(1000,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Main() {
        setLayout(new BorderLayout());
        // 초대 화면
        //InvitePanel invitePanel = new InvitePanel();
        //currentPanel = invitePanel.getPanel();
        //getContentPane().add(currentPanel);

        // 알림
        NotificationPanel notificationPanel = new NotificationPanel();
        currentPanel = notificationPanel.getPanel();
        getContentPane().add(currentPanel);

        //각종 명령어
        setTitle("데이터베이스시스템 달력");
        setVisible(true);
        setSize(1000,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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