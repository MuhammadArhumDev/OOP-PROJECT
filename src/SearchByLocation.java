import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

class SearchByLocation extends JFrame {
    private JComboBox<String> regionDropdown;
    private JComboBox<String> cityDropdown;
    private JTable resultTable;
    private DefaultTableModel tableModel;

    private Map<String, List<String>> citiesByRegion;

    public SearchByLocation() {
        initializeUI();
        initializeCityData();
    }

    private void initializeUI() {
        setTitle("Search by Location");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        JLabel regionLabel = new JLabel("Region:");
        JLabel cityLabel = new JLabel("City:");
        regionDropdown = new JComboBox<>();
        cityDropdown = new JComboBox<>();
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
        add(regionLabel, constraints);

        constraints.gridx = 1;
        add(regionDropdown, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(cityLabel, constraints);

        constraints.gridx = 1;
        add(cityDropdown, constraints);

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

        regionDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateCityDropdown();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) cityDropdown.getSelectedItem();
                String selectedRegion = (String) regionDropdown.getSelectedItem();

                List<String> searchResults = searchPropertiesByLocation(selectedCity, selectedRegion);

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

    private void initializeCityData() {
        citiesByRegion = new HashMap<>();

        List<String> citiesInPunjab = new ArrayList<>();
        citiesInPunjab.add("Chakwal");
        citiesInPunjab.add("Lahore");
        citiesInPunjab.add("Okara");
        citiesInPunjab.add("Rawalpindi");
        citiesInPunjab.add("Renala Khurd");
        citiesByRegion.put("Punjab", citiesInPunjab);

        List<String> citiesInIslamabad = new ArrayList<>();
        citiesInIslamabad.add("Islamabad");
        citiesByRegion.put("Islamabad", citiesInIslamabad);

        List<String> citiesInKPK = new ArrayList<>();
        citiesInKPK.add("Peshawar");
        citiesInKPK.add("Abbottabad");
        citiesByRegion.put("Khyber Pakhtunkhwa", citiesInKPK);

        List<String> citiesInSindh = new ArrayList<>();
        citiesInSindh.add("Karachi");
        citiesInSindh.add("Hyderabad");
        citiesByRegion.put("Sindh", citiesInSindh);

        List<String> citiesInBalochistan = new ArrayList<>();
        citiesInBalochistan.add("Quetta");
        citiesInBalochistan.add("Gwadar");
        citiesByRegion.put("Balochistan", citiesInBalochistan);

        for (String region : citiesByRegion.keySet()) {
            regionDropdown.addItem(region);
        }
    }

    private void populateCityDropdown() {
        String selectedRegion = (String) regionDropdown.getSelectedItem();
        List<String> cities = citiesByRegion.get(selectedRegion);

        cityDropdown.removeAllItems();
        for (String city : cities) {
            cityDropdown.addItem(city);
        }
    }

    private List<String> searchPropertiesByLocation(String selectedCity, String selectedRegion) {
        List<String> searchResults = new ArrayList<>();

        try {
            // Reading properties from the Properties.txt
            File file = new File("Properties.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String property = scanner.nextLine();
                String[] propertyData = property.split(",");

                String propertyRegion = propertyData[4];
                String propertyCity = propertyData[5];
                boolean leased = Boolean.parseBoolean(propertyData[8]);

                if (propertyRegion.equalsIgnoreCase(selectedRegion)
                        && propertyCity.equalsIgnoreCase(selectedCity)
                        && !leased) {
                    searchResults.add(property);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    private void populateTable(List<String> searchResults) {
        tableModel.setRowCount(0);

        tableModel.setColumnIdentifiers(new String[]{"Username", "ID", "Type", "Description", "Region", "City", "Area", "Price", "Leased"});

        for (String property : searchResults) {
            String[] propertyData = property.split(",");
            tableModel.addRow(propertyData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SearchByLocation().setVisible(true);
            }
        });
    }
}
