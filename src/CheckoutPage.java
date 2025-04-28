import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CheckoutPage extends JFrame {
    private JTextField nameField;
    private JTextField cardNoField;
    private JTextField cvcField;

    public CheckoutPage() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Checkout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        JLabel nameLabel = new JLabel("Name:");
        JLabel cardNoLabel = new JLabel("Card No:");
        JLabel cvcLabel = new JLabel("CVC:");
        nameField = new JTextField(20);
        cardNoField = new JTextField(16);
        cvcField = new JTextField(3);
        JButton buyNowButton = new JButton("Buy Now");
        JButton goBackButton = new JButton("Go Back");

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(nameLabel, constraints);

        constraints.gridx = 1;
        add(nameField, constraints);

        constraints.gridy = 1;
        add(cardNoLabel, constraints);

        constraints.gridx = 1;
        add(cardNoField, constraints);

        constraints.gridy = 2;
        add(cvcLabel, constraints);

        constraints.gridx = 1;
        add(cvcField, constraints);

        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(buyNowButton, constraints);

        constraints.gridy = 4;
        add(goBackButton, constraints);

        buyNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String cardNo = cardNoField.getText();
                String cvc = cvcField.getText();

                processPayment(name, cardNo, cvc);

                JOptionPane.showMessageDialog(CheckoutPage.this, "Purchase complete!");
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyProperty page = new BuyProperty();
                page.setVisible(true);
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void processPayment(String name, String cardNo, String cvc) {
        System.out.println("Processing payment...");
        System.out.println("Name: " + name);
        System.out.println("Card No: " + cardNo);
        System.out.println("CVC: " + cvc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CheckoutPage().setVisible(true);
            }
        });
    }
}
