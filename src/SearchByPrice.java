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

class SearchByPrice extends JFrame {
    private JTextField minPriceField;
    private JTextField maxPriceField;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    public SearchByPrice() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Search by Price");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        // Create components
        JLabel minPriceLabel = new JLabel("Minimum Price:");
        JLabel maxPriceLabel = new JLabel("Maximum Price:");
        minPriceField = new JTextField(10);
        maxPriceField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        JButton goBackButton = new JButton("Go Back");

        tableModel = new DefaultTableModel();
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(minPriceLabel, constraints);

        constraints.gridx = 1;
        add(minPriceField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(maxPriceLabel, constraints);

        constraints.gridx = 1;
        add(maxPriceField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(searchButton, constraints);

        constraints.gridx = 1;
        add(goBackButton, constraints);

        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.gridwidth = 2;
        add(scrollPane, constraints);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double minPrice = Double.parseDouble(minPriceField.getText());
                double maxPrice = Double.parseDouble(maxPriceField.getText());

                List<String> searchResults = searchPropertiesByPrice(minPrice, maxPrice);

                populateTable(searchResults);
            }
        });

        goBackButton.addActionListener(new ActionListener() {
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

    private List<String> searchPropertiesByPrice(double minPrice, double maxPrice) {
        List<String> searchResults = new ArrayList<>();

        try {

            File file = new File("Properties.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String property;
            while ((property = reader.readLine()) != null) {
                String[] propertyData = property.split(",");

                double propertyPrice = Double.parseDouble(propertyData[7]);
                boolean isLeased = Boolean.parseBoolean(propertyData[8]);

                if (propertyPrice >= minPrice && propertyPrice <= maxPrice && !isLeased) {
                    searchResults.add(property);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    private void populateTable(List<String> properties) {
        tableModel.setRowCount(0);

        tableModel.setColumnIdentifiers(new String[]{"Username", "ID", "Type", "Description", "Region", "City", "Area", "Price", "Leased"});

        for (String property : properties) {
            String[] rowData = property.split(",");
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SearchByPrice().setVisible(true);
            }
        });
    }
}
