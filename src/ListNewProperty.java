import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListNewProperty extends JFrame {

    private JComboBox<String> nameComboBox;
    private JTextField descriptionField;
    private JComboBox<String> regionComboBox;
    private JComboBox<String> cityComboBox;
    private JTextField areaField;
    private JTextField priceField;

    public ListNewProperty() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("List New Property");
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);

        panel.add(new JLabel("Property Type:"));
        nameComboBox = new JComboBox<>(new String[]{"Residential", "Commercial"});
        panel.add(nameComboBox);

        panel.add(new JLabel("Property Description:"));
        descriptionField = new JTextField();
        panel.add(descriptionField);

        panel.add(new JLabel("Region:"));
        regionComboBox = new JComboBox<>(new String[]{"Punjab", "Islamabad", "Khyber Pakhtunkhwa", "Sindh", "Balochistan"});
        regionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateCityComboBox();
            }
        });
        panel.add(regionComboBox);

        panel.add(new JLabel("City:"));
        cityComboBox = new JComboBox<>();
        panel.add(cityComboBox);

        panel.add(new JLabel("Total Area:"));
        areaField = new JTextField();
        panel.add(areaField);

        panel.add(new JLabel("Lease Price per Day:"));
        priceField = new JTextField();
        panel.add(priceField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProperty();
            }
        });
        panel.add(saveButton);

        JButton homeButton = new JButton("Home Menu");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellerPage page = new SellerPage();
                page.setVisible(true);
                dispose();
            }
        });
        panel.add(homeButton);
    }

    private void populateCityComboBox() {
        String selectedRegion = (String) regionComboBox.getSelectedItem();
        cityComboBox.removeAllItems();
        switch (selectedRegion) {
            case "Punjab":
                cityComboBox.addItem("Chakwal");
                cityComboBox.addItem("Lahore");
                cityComboBox.addItem("Okara");
                cityComboBox.addItem("Rawalpindi");
                cityComboBox.addItem("Renala Khurd");
                // Can add more cities
                break;
            case "Islamabad":
                cityComboBox.addItem("Islamabad");
                break;
            case "Khyber Pakhtunkhwa":
                cityComboBox.addItem("Peshawar");
                cityComboBox.addItem("Abbottabad");
                // Can add more cities
                break;
            case "Sindh":
                cityComboBox.addItem("Karachi");
                cityComboBox.addItem("Hyderabad");
                // Can add more cities
                break;
            case "Balochistan":
                cityComboBox.addItem("Quetta");
                cityComboBox.addItem("Gwadar");
                // Can add more cities
                break;
        }
    }

    private void saveProperty() {
        String name = (String) nameComboBox.getSelectedItem();
        String description = descriptionField.getText();
        String region = (String) regionComboBox.getSelectedItem();
        String city = (String) cityComboBox.getSelectedItem();
        double area = Double.parseDouble(areaField.getText());
        double price = Double.parseDouble(priceField.getText());
        boolean leased = false;

        if (name.isEmpty() || description.isEmpty() || region.isEmpty() || city.isEmpty() || areaField.getText().isEmpty() || priceField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String propertyID = dateFormat.format(new Date());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Properties.txt", true))) {
            writer.write(SellerLogin.getLoggedInUsername() + ",");
            writer.write(propertyID + ",");
            writer.write(name + ",");
            writer.write(description + ",");
            writer.write(region + ",");
            writer.write(city + ",");
            writer.write(String.valueOf(area) + ",");
            writer.write(String.valueOf(price) + ",");
            writer.write(String.valueOf(leased));
            writer.newLine();
            writer.close();
            JOptionPane.showMessageDialog(this, "Property saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while saving the property.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameComboBox.setSelectedIndex(0);
        descriptionField.setText("");
        regionComboBox.setSelectedIndex(0);
        cityComboBox.removeAllItems();
        areaField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ListNewProperty().setVisible(true);
            }
        });
    }
}
