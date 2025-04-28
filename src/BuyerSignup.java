import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BuyerSignup extends JFrame {

    public BuyerSignup() {
        initializeUI();
    }

    private void initializeUI() {
        JLabel label1;
        JTextField textField1;
        JTextField textField2;
        JButton button;
        JButton loginButton;
        JButton homepageButton;

        setTitle("Buyer Signup Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        // Create components
        label1 = new JLabel("Username:");
        textField1 = new JTextField(30);
        JLabel label2 = new JLabel("Password:");
        textField2 = new JTextField(30);
        button = new JButton("Signup");
        loginButton = new JButton("Login Page");
        homepageButton = new JButton("Home page");

        // Create layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Add components to the frame
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label1, constraints);

        constraints.gridy = 1;
        add(textField1, constraints);

        constraints.gridy = 2;
        add(label2, constraints);

        constraints.gridy = 3;
        add(textField2, constraints);

        constraints.gridy = 4;
        add(button, constraints);

        constraints.gridy = 5;
        add(loginButton, constraints);

        constraints.gridy = 6;
        add(homepageButton, constraints);

        // Action listener for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = textField2.getText();

                AuthData authData = new AuthData(username, password);
                if (!authData.isBuyerUsernameRegistered()) {
                    authData.registerBuyerUser(username, password);
                    JOptionPane.showMessageDialog(BuyerSignup.this, "Successfully registered!");
                } else {
                    JOptionPane.showMessageDialog(BuyerSignup.this, "Already registered!");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyerLogin loginPage = new BuyerLogin();
                loginPage.setVisible(true);
                dispose();
            }
        });

        homepageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main homePage = new Main();
                homePage.setVisible(true);
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
