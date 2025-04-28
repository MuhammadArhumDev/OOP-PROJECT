import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewListedProperties extends JFrame {

    private JTable table;

    public ViewListedProperties() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("All Listed Properties");
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadListedProperties();

        table.setFillsViewportHeight(true);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton goBackButton = new JButton("Go Back");
        panel.add(goBackButton, BorderLayout.SOUTH);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyerHomePage page = new BuyerHomePage();
                page.setVisible(true);
                dispose();
            }
        });
    }

    private void loadListedProperties() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");
        model.addColumn("Property ID");
        model.addColumn("Property Type");
        model.addColumn("Region");
        model.addColumn("City");
        model.addColumn("Area SqFeet");
        model.addColumn("Price");

        List<String[]> listedProperties = searchListedProperties();
        String loggedInUsername = SellerLogin.getLoggedInUsername();

        for (String[] property : listedProperties) {

            if (!Boolean.parseBoolean(property[8])) {
                String[] rowData = {property[0], property[1], property[2], property[4], property[5], property[6], property[7]};
                model.addRow(rowData);
            }
        }

        table.setModel(model);
    }

    private List<String[]> searchListedProperties() {
        List<String[]> leasedProperties = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Properties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] property = line.split(",");
                if (property.length >= 7) {
                    leasedProperties.add(property);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return leasedProperties;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewListedProperties().setVisible(true);
            }
        });
    }
}
