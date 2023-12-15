import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPanel {
    private JPanel registerPanel = new JPanel(new GridLayout(4, 1));
    private JPanel namePanel;
    private JPanel idPanel;
    private JPanel passwordPanel;
    private JTextField nameInputField, idInputField;
    private JPasswordField passwordInputField;
    ActionListener listener;
    
    public RegisterPanel(ActionListener listener) {
        this.listener = listener;
        namePanel = new JPanel(new GridLayout(1, 2));
        namePanel.add(new JLabel("Name : "));
        nameInputField = new JTextField();
        namePanel.add(nameInputField);

        idPanel = new JPanel(new GridLayout(1, 2));
        idPanel.add(new JLabel("ID : "));
        idInputField = new JTextField();
        idPanel.add(idInputField);

        passwordPanel = new JPanel(new GridLayout(1, 2));
        passwordPanel.add(new JLabel("Password : "));
        passwordInputField = new JPasswordField();
        passwordPanel.add(passwordInputField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(listener);
        registerButton.setActionCommand("RegisterButtonClicked");

        registerPanel.add(namePanel);
        registerPanel.add(idPanel);
        registerPanel.add(passwordPanel);
        registerPanel.add(registerButton);


        registerPanel.setVisible(true);
        registerPanel.setSize(500,200);
        //registerPanel.add(registerPanel);
    }

    public void actionPerformed(ActionEvent e) {
        
    }
    public String getInputID() {
        return idInputField.getText();
    }
    public String getInputName() {
        return nameInputField.getText();
    }
    public String getInputPassword() {
        return new String(passwordInputField.getPassword());
    }
    public JPanel getPanel() {
        return registerPanel;
    }
}
