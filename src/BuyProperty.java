import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class BuyProperty extends JFrame {
    private JTextField propertyIdField;
    private JTable propertyTable;
    private DefaultTableModel tableModel;

    public BuyProperty() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Buy Property");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        JLabel propertyIdLabel = new JLabel("Property ID:");
        propertyIdField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        JButton checkoutButton = new JButton("Checkout");
        JButton homeButton = new JButton("Go Bck");

        tableModel = new DefaultTableModel();
        propertyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(propertyTable);


        setLayout(new BorderLayout());


        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(propertyIdLabel);
        searchPanel.add(propertyIdField);
        searchPanel.add(searchButton);


        add(searchPanel, BorderLayout.NORTH);


        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(checkoutButton);
        buttonPanel.add(homeButton);


        add(buttonPanel, BorderLayout.SOUTH);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String propertyId = propertyIdField.getText();
                List<String> propertyData = getPropertyDataById(propertyId);
                populateTable(propertyData);
            }
        });


        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutPage page = new CheckoutPage();
                page.setVisible(true);
                dispose();
            }
        });


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyerHomePage page = new BuyerHomePage();
                page.setVisible(true);
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private List<String> getPropertyDataById(String propertyId) {
        List<String> propertyData = new ArrayList<>();

        try {

            File file = new File("Properties.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String property;
            while ((property = reader.readLine()) != null) {
                String[] propertyInfo = property.split(",");
                if (propertyInfo[1].equals(propertyId)) {
                    propertyData.add(property);
                    break;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return propertyData;
    }

    private void populateTable(List<String> propertyData) {
        tableModel.setRowCount(0);

        tableModel.setColumnIdentifiers(new String[]{"Username", "ID", "Type", "Description", "Region", "City", "Area", "Price", "Leased"});

        for (String property : propertyData) {
            String[] rowData = property.split(",");
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BuyProperty().setVisible(true);
            }
        });
    }
}