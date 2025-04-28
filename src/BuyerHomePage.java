import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyerHomePage extends JFrame {

    public BuyerHomePage() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Buyer Home Page");
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);

        panel.add(createButton("Search by Location", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchByLocation page = new SearchByLocation();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("Search by Price Range", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchByPrice page = new SearchByPrice();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("View All Listed Properties", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewListedProperties page = new ViewListedProperties();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("View All Leased Properties", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewLeasedProperties page = new ViewLeasedProperties();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("Buy Property", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyProperty page = new BuyProperty();
                page.setVisible(true);
                dispose();
            }
        }));
        panel.add(createButton("Renew Property", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyProperty page = new BuyProperty();
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
                new BuyerHomePage().setVisible(true);
            }
        });
    }
}
