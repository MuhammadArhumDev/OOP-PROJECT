import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SellerLogin extends JFrame {

    private static String loggedInUsername;

    public SellerLogin() {
        initializeUI();
    }

    public static String getLoggedInUsername() {
        return loggedInUsername;
    }

    private void initializeUI() {
        JLabel label1;
        JTextField textField1;
        JTextField textField2;
        JButton button;
        JButton signupButton;
        JButton homepageButton;

        setTitle("Seller Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));


        label1 = new JLabel("Username:");
        textField1 = new JTextField(30);
        JLabel label2 = new JLabel("Password:");
        textField2 = new JTextField(30);
        button = new JButton("Login");
        signupButton = new JButton("Signup page");
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
                if (authData.isSellerRegistered()) {
                    loggedInUsername = username;
                    JOptionPane.showMessageDialog(SellerLogin.this, "Successfully Logged in!");

                    SellerPage sellerPage = new SellerPage();
                    sellerPage.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(SellerLogin.this, "Wrong Credentials!");
                }
            }
        });


        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SellerSignupPage
                SellerSignup signupPage = new SellerSignup();
                signupPage.setVisible(true);
                dispose(); // Close the current login page
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
