import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame extends JFrame implements ActionListener {
    private JPanel registerPanel = new JPanel(new GridLayout(4, 1));
    
    public RegisterFrame() {
        JPanel namePanel = new JPanel(new GridLayout(1, 2));
        namePanel.add(new JLabel("Name : "));
        namePanel.add(new JTextField());

        JPanel idPanel = new JPanel(new GridLayout(1, 2));
        idPanel.add(new JLabel("ID : "));
        idPanel.add(new JTextField());

        JPanel passwordPanel = new JPanel(new GridLayout(1, 2));
        passwordPanel.add(new JLabel("Password : "));
        passwordPanel.add(new JPasswordField());

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        registerPanel.add(namePanel);
        registerPanel.add(idPanel);
        registerPanel.add(passwordPanel);
        registerPanel.add(registerButton);


        setTitle("회원가입");
        setVisible(true);
        setSize(500,200);
        setResizable(false);
        setLocationRelativeTo(null);
        add(registerPanel);
    }

    public void actionPerformed(ActionEvent e) {
            
    }
}
