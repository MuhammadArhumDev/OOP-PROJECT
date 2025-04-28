import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

class AuthData {
    private String username;
    private String password;
    private static final String BUYER_FILE_PATH = "C:\\Users\\MUHAMMAD ARHUM\\Desktop\\Sem 2\\Project\\Project\\out\\production\\Project\\BuyerAuthData.txt";
    private static final String SELLER_FILE_PATH = "C:\\Users\\MUHAMMAD ARHUM\\Desktop\\Sem 2\\Project\\Project\\out\\production\\Project\\SellerAuthData.txt";

    public AuthData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isSellerRegistered() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SELLER_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String registeredUsername = parts[0];
                String registeredPassword = parts[1];
                if (registeredUsername.equals(username) && registeredPassword.equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isSellerUsernameRegistered() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SELLER_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String registeredUsername = parts[0];
                if (registeredUsername.equals(username)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isBuyerUsernameRegistered() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(BUYER_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String registeredUsername = parts[0];
                if (registeredUsername.equals(username)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isBuyerRegistered() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(BUYER_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String registeredUsername = parts[0];
                String registeredPassword = parts[1];
                if (registeredUsername.equals(username) && registeredPassword.equals(password)) {
                    reader.close();
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerSellerUser(String username, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(SELLER_FILE_PATH, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void registerBuyerUser(String username, String password) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(BUYER_FILE_PATH, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}