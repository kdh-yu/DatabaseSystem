import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.sql.*;

public class App extends JFrame implements ActionListener {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    ImageIcon iconImage = new ImageIcon("icon.png");
    public boolean login = false;
    String masterID = "tt",
            masterPassword = "tt",
            dbURL = "jdbc:postgresql://localhost/",
            dbName = "calendar";
    Connection connection;
    private String loginInfo;
    private JPanel currentPanel;
    private CalendarPanel calendarPanel;
    private EventPanel eventPanel;
    private LoginPanel loginPanel = new LoginPanel(this);
    private RegisterPanel registerPanel = new RegisterPanel(this);
    private EditPanel editPanel = new EditPanel(this);
    private AddPanel addPanel;
    private int selectedDay;

    public App() {  // 시작시 로그인 화면 띄움
        try {
            // 관리 계정
            this.connection = DriverManager.getConnection(dbURL+dbName, masterID, masterPassword);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        eventPanel = new EventPanel(this, connection, loginInfo);
        
        setLayout(new BorderLayout());
        // 로그인 창 띄우기
        currentPanel = loginPanel.getPanel();
        getContentPane().add(currentPanel);

        //각종 명령어
        setTitle("데이터베이스시스템 달력");
        setIconImage(iconImage.getImage());
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
            String loginQuery = "SELECT * FROM calendar.user WHERE userid = ? AND PASSWORD = ?";
            // 아이디 비번 확인
            try {
                PreparedStatement preparedStatement = this.connection.prepareStatement(loginQuery);
                preparedStatement.setString(1, loginID);
                preparedStatement.setString(2, loginPassword);
                ResultSet rs = preparedStatement.executeQuery();
                System.out.println(rs);
                if (rs.next()) {
                    loginInfo = loginID;
                    calendarPanel = new CalendarPanel(this, loginInfo, connection);
                    getContentPane().removeAll();
                    currentPanel = new JPanel(new GridLayout(1, 2));
                    currentPanel.add(calendarPanel.getPanel());
                    currentPanel.add(eventPanel.getPanel());
                    getContentPane().add(currentPanel);
                } else {
                    loginPanel.changeErrorMessage("아이디 또는 비밀번호를 확인해주세요.");
                }

            } catch (Exception ex) {
                loginPanel.changeErrorMessage("아이디 또는 비밀번호를 확인해주세요.");
                System.out.println(ex.getMessage());
            }
        }

        else if (e.getActionCommand() == "Register") {
            JFrame registerFrame = new JFrame();
            registerFrame.add(registerPanel.getPanel());
            registerFrame.setTitle("Register");
            registerFrame.setVisible(true);
            registerFrame.setSize(300,300);
            registerFrame.setResizable(false);
            //registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            registerFrame.setLocationRelativeTo(null);
        }
        else if (e.getActionCommand() == "RegisterButtonClicked") {
            String inputID = registerPanel.getInputID();
            String inputName = registerPanel.getInputName();
            String inputPassword = registerPanel.getInputPassword();

            // 계정 생성
            String userAddQuery = "INSERT INTO calendar.user (userid, name, password) VALUES (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = this.connection.prepareStatement(userAddQuery);
                preparedStatement.setString(1, inputID);
                preparedStatement.setString(2, inputName);
                preparedStatement.setString(3, inputPassword);
                preparedStatement.executeUpdate();
                registerPanel.registerSuccess();
            } catch (Exception ex) {
                registerPanel.registerFailed();
                System.out.println(ex.getMessage());
            }
        }

        else if ((e.getActionCommand().indexOf("DaySelect")) != -1) {
            int monthNow = (int)calendarPanel.monthCombo.getSelectedItem();
            int day = Integer.parseInt(e.getActionCommand().substring(9));
            this.selectedDay = day;
            currentPanel.remove(1);
            eventPanel.dayChange(monthNow, day, this);
            currentPanel.add(eventPanel.getPanel());
        }

        else if (e.getActionCommand().indexOf("ModifyEvent") != -1) {
            int eventidx = Integer.parseInt(e.getActionCommand().substring(11));
            JPanel selectedEvent = eventPanel.getEventButtonPanel(eventidx);
            String getEventInfoQuery = "SELECT * FROM calendar.event WHERE EXTRACT(MONTH FROM start_date) = ? AND EXTRACT(DAY FROM start_date) = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(getEventInfoQuery);
                preparedStatement.setInt(1, calendarPanel.month);
                preparedStatement.setInt(2, this.selectedDay);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next())
                    editPanel = new EditPanel(this, calendarPanel.month, this.selectedDay, rs.getString("name"));
                currentPanel.remove(1);
                currentPanel.add(editPanel.getPanel());
            } catch (Exception ex) {
            }
            //System.out.println(selectedEvent + " " + calendarPanel.year + " " + calendarPanel.month + " " + calendarPanel.date + "");
            //editPanel.setEditInfo(selectedEvent, calendarPanel.year, calendarPanel.month, calendarPanel.date, connection);
        }
        else if (e.getActionCommand() == "ChangeEvent") {
            String nameToChange = editPanel.nowEditingName;
            String getEventInfoQuery = "SELECT eventid FROM calendar.event WHERE EXTRACT(MONTH FROM start_date) = ? AND EXTRACT(DAY FROM start_date) = ? AND name = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(getEventInfoQuery);
                preparedStatement.setInt(1, calendarPanel.month);
                preparedStatement.setInt(2, this.selectedDay);
                preparedStatement.setString(3, nameToChange);

                Calendar tmpcal = Calendar.getInstance();
                tmpcal.set(Calendar.YEAR, (int)editPanel.yearCombo.getSelectedItem());
                tmpcal.set(Calendar.MONTH, (int)editPanel.monthCombo.getSelectedItem());
                tmpcal.set(Calendar.DATE, (int)editPanel.dayCombo.getSelectedItem());
                java.util.Date tdate = tmpcal.getTime();
                java.sql.Date tmpdate = new java.sql.Date(tdate.getTime());

                String getEditedEventInfoQuery = "UPDATE calendar.event SET name = ?, start_date = ?";
                preparedStatement = connection.prepareStatement(getEditedEventInfoQuery);
                preparedStatement.setString(1, editPanel.nameInput.getSelectedText());
                preparedStatement.setDate(2, tmpdate);
                int rs = preparedStatement.executeUpdate();
                currentPanel.remove(1);
                eventPanel.dayChange(calendarPanel.month, selectedDay, this);
                currentPanel.add(eventPanel.getPanel());
                //editPanel = new EditPanel(this, calendarPanel.month, this.selectedDay, rs.getString("name"));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (e.getActionCommand() == "DeleteEvent") {
            String nameToChange = editPanel.nowEditingName;
            String getEventInfoQuery = "SELECT eventid FROM calendar.event WHERE EXTRACT(MONTH FROM start_date) = ? AND EXTRACT(DAY FROM start_date) = ? AND name = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(getEventInfoQuery);
                preparedStatement.setInt(1, calendarPanel.month);
                preparedStatement.setInt(2, this.selectedDay);
                preparedStatement.setString(3, nameToChange);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                int dropEventIdx = rs.getInt(1);
                System.out.println(dropEventIdx);
                String DeleteQuery = "DELETE FROM calendar.event WHERE eventid = ?";
                preparedStatement = connection.prepareStatement(DeleteQuery);
                preparedStatement.setInt(1, dropEventIdx);
                int rss = preparedStatement.executeUpdate();
                currentPanel.remove(0);
                currentPanel.remove(0);
                calendarPanel = new CalendarPanel(this, loginInfo, connection);
                currentPanel.add(calendarPanel.getPanel());
                eventPanel.dayChange(calendarPanel.month, selectedDay, this);
                currentPanel.add(eventPanel.getPanel());
                

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (e.getActionCommand() == "AddEvent") {
            currentPanel.remove(1);
            addPanel = new AddPanel(this, calendarPanel.month, selectedDay);
            currentPanel.add(addPanel.getPanel());
        }
        else if (e.getActionCommand() == "AddEventClicked") {
            try {
                String eventAddQuery = "INSERT INTO calendar.event (userid, name, description, start_date) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(eventAddQuery);
                preparedStatement.setString(1, loginInfo);
                preparedStatement.setString(2, addPanel.nameInput.getText());
                preparedStatement.setString(3, addPanel.descriptionInput.getText());
                Calendar tmpcal = Calendar.getInstance();
                tmpcal.set(Calendar.YEAR, (Integer)addPanel.yearCombo.getSelectedItem());
                tmpcal.set(Calendar.MONTH, (Integer)addPanel.monthCombo.getSelectedItem()-1);
                tmpcal.set(Calendar.DATE, (Integer)addPanel.dayCombo.getSelectedItem());
                java.util.Date tdate = tmpcal.getTime();
                System.out.println(tdate);
                java.sql.Date tmpdate = new java.sql.Date(tdate.getTime());
                preparedStatement.setDate(4, tmpdate);
                int ii = preparedStatement.executeUpdate();
                currentPanel.remove(0);
                currentPanel.remove(0);
                calendarPanel = new CalendarPanel(this, loginInfo, connection);
                eventPanel = new EventPanel(this, connection, loginInfo);
                currentPanel.add(calendarPanel.getPanel());
                currentPanel.add(eventPanel.getPanel());
                System.out.println("Inserted"+ii);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        else if (e.getActionCommand() == "EventPanel") {
            currentPanel.remove(1);
            currentPanel.add(eventPanel.getPanel());
        }
        else if (e.getActionCommand() == "EventManagement") {
            new NotificationFrame(this, connection, loginInfo);
        }

        revalidate();
        repaint();
    }


	//실헹메소드
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App();
            }
        });
	}
}