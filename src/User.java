import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String name;
    private int account_number;
    private double balance;
    private int pin;



    public static User getUserFromDB(int accountNumber) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE account_number = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, accountNumber);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("account_number"),
                            rs.getDouble("balance"),
                            rs.getInt("pin")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }


    public User() {
    }
    public User(String name, int accountNumber, int pin, int balance) {
        this.name = name;
        this.account_number = accountNumber;
        this.balance = balance;
        this.pin = pin;
    }
    public User(int id, String name, int account_number, double balance, int pin) {
        this.id = id;
        this.name = name;
        this.account_number = account_number;
        this.balance = balance;
        this.pin = pin;
    }



    public void Deposit(double amount){
        this.balance += amount;
    }

    public boolean Withdraw(double amount){
        if (balance > amount){
            this.balance -= amount;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", account_number='" + account_number + '\'' +
                ", balance=" + balance +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
