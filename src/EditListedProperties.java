import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditListedProperties extends JFrame {

    private JTable propertyTable;
    private DefaultTableModel tableModel;
    private JButton saveButton;

    public EditListedProperties() {
        initializeUI();
        loadProperties();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Edit Listed Properties");
        setSize(1000, 600);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(
                new Object[]{"Username", "Property ID", "Property Type", "Property Description", "Region", "City", "Total Area", "Lease Price", "Leased"}, 0);
        propertyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(propertyTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        panel.add(saveButton, BorderLayout.SOUTH);

        JButton homeButton = new JButton("Home Menu");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellerPage page = new SellerPage();
                page.setVisible(true);
                dispose();
            }
        });
        panel.add(homeButton, BorderLayout.NORTH);
    }

    private void loadProperties() {
        String loggedInUsername = SellerLogin.getLoggedInUsername();

        try (BufferedReader reader = new BufferedReader(new FileReader("Properties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 9 && data[0].equals(loggedInUsername) && !Boolean.parseBoolean(data[8])) {
                    tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while loading properties.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveChanges() {
        List<String[]> updatedProperties = new ArrayList<>();
        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String seller = getStringValueFromTable(i, 0);
            String username = getStringValueFromTable(i, 1);
            String id = getStringValueFromTable(i, 2);
            String type = getStringValueFromTable(i, 3);
            String description = getStringValueFromTable(i, 4);
            String region = getStringValueFromTable(i, 5);
            String city = getStringValueFromTable(i, 6);
            String area = getStringValueFromTable(i, 7);
            String price = getStringValueFromTable(i, 8);

            if (seller != null && username != null && id != null && type != null && description != null &&
                    region != null && city != null && area != null && price != null) {
                String[] updatedData = {seller, username, id, type, description, region, city, area, price};
                updatedProperties.add(updatedData);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Properties.txt"))) {
            for (String[] propertyData : updatedProperties) {
                writer.write(String.join(",", propertyData));
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(this, "Changes saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "An error occurred while saving changes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    private String getStringValueFromTable(int row, int column) {
        Object value = tableModel.getValueAt(row, column);
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EditListedProperties().setVisible(true);
            }
        });
    }
}
