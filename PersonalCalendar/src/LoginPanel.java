import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class LoginPanel {
    JPanel loginPanel = new JPanel(new BorderLayout(50, 30));
    JTextField idInputField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel errorMessageText = new JLabel();

    public LoginPanel(ActionListener listener) {
        JPanel idPanel = new JPanel(new GridLayout(1, 2));
        idPanel.add(new JLabel("ID : "));
        idPanel.add(idInputField);
        
        JPanel passwordPanel = new JPanel(new GridLayout(1, 2));
        passwordPanel.add(new JLabel("Password : "));
        passwordPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(listener);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(listener);
        
        JPanel LoginAndRegister = new JPanel(new GridLayout(1, 2));
        LoginAndRegister.add(loginButton);
        LoginAndRegister.add(registerButton);

        JPanel errorPanel = new JPanel();
        errorPanel.add(errorMessageText);
        
        JPanel totalPanel = new JPanel(new GridLayout(4, 1));
        totalPanel.add(idPanel);
        totalPanel.add(passwordPanel);
        totalPanel.add(errorPanel);
        totalPanel.add(LoginAndRegister);

        totalPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300));
        loginPanel.add(totalPanel, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return loginPanel;
    }

    public String sendID() {
        return idInputField.getText();
    }

    public String sendPassword() {
        return new String(passwordField.getPassword());
    }

    public void changeErrorMessage(String message) {
        errorMessageText.setText(message);
    }
}
