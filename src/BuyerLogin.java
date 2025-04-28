import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BuyerLogin extends JFrame {
    private static String loggedInUsername;
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    public BuyerLogin() {
        initializeUI();
    }

    private void initializeUI() {
        JLabel label1;
        JTextField textField1;
        JTextField textField2;
        JButton button;
        JButton signupButton;
        JButton homepageButton;

        setTitle("Buyer Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        label1 = new JLabel("Username:");
        textField1 = new JTextField(30);
        JLabel label2 = new JLabel("Password:");
        textField2 = new JTextField(30);
        button = new JButton("Login");
        signupButton = new JButton("Signup Page");
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
        add(signupButton, constraints);

        constraints.gridy = 6;
        add(homepageButton, constraints);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField1.getText();
                String password = textField2.getText();

                AuthData authData = new AuthData(username, password);
                if (authData.isBuyerRegistered()) {
                    loggedInUsername = username;
                    JOptionPane.showMessageDialog(BuyerLogin.this, "Successfully Loggedin!!!");

                    BuyerHomePage Page = new BuyerHomePage();
                    Page.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(BuyerLogin.this, "Wrong Credentials!!!");
                }
            }
        });


        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BuyerSignup signupPage = new BuyerSignup();
                signupPage.setVisible(true);
                dispose();
            }
        });


        homepageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Main homepage
                Main homePage = new Main();
                homePage.setVisible(true);
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BuyerLogin().setVisible(true);
            }
        });
    }
}
