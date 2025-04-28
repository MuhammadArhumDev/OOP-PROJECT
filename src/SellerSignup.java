import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SellerSignup extends JFrame {

    public SellerSignup() {
        initializeUI();
    }

    private void initializeUI() {
        JLabel label1;
        JTextField textField1;
        JTextField textField2;
        JButton button;
        JButton loginButton;
        JButton homepageButton;

        setTitle("Seller Signup Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        label1 = new JLabel("Username:");
        textField1 = new JTextField(30);
        JLabel label2 = new JLabel("Password:");
        textField2 = new JTextField(30);
        button = new JButton("Signup");
        loginButton = new JButton("Login page");
        homepageButton = new JButton("Home page");

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

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

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = textField2.getText();

                AuthData authData = new AuthData(username, password);
                if (!authData.isSellerUsernameRegistered()) {
                    authData.registerSellerUser(username, password);
                    JOptionPane.showMessageDialog(SellerSignup.this, "Successfully registered!");
                } else {
                    JOptionPane.showMessageDialog(SellerSignup.this, "Username Already registered!");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SellerSignupPage
                SellerLogin loginPage = new SellerLogin();
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