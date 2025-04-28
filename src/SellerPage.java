import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerPage extends JFrame {

    public SellerPage() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Seller Page");
        setSize(1000, 600);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);

        panel.add(createButton("List New Property", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ListNewProperty page = new ListNewProperty();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("View Listed Properties", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListedProperties page = new ListedProperties();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("View Leased Properties", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code for handling "View Leased Properties" button click event
                LeasedProperties page = new LeasedProperties();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("Edit Listed Properties", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditListedProperties page = new EditListedProperties();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("Update Property", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditListedProperties page = new EditListedProperties();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("Delete Property", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EditListedProperties page = new EditListedProperties();
                page.setVisible(true);
                dispose();
            }
        }));
    }

    private JButton createButton(String name, ActionListener actionListener) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(150, 150));
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false); // Remove focus border
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


        button.addActionListener(actionListener);

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SellerPage().setVisible(true);
            }
        });
    }
}
