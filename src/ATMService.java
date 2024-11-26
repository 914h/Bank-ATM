import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ATMService {
    Scanner scanner = new Scanner(System.in);
    String choice;
    int oldPIN;
    double amount;

    public void start() {
        boolean isRunning = true;
        User loggedInUser = null;

        System.out.println("Welcome to the ATM!");

        while (isRunning) {
            if (loggedInUser == null) {
                // Authentication menu
                ShowAuthMenu();
                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        loggedInUser = login();
                        break;
                    case "2":
                        SignUP();
                        break;
                    case "3":
                        System.out.println("Forget Password functionality not implemented yet!");
                        break;
                    case "q":
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } else {
                // Logged-in menu
                ShowMenu();
                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        checkBalance(loggedInUser);
                        break;
                    case "2":
                        depositCash(loggedInUser);
                        break;
                    case "3":
                        withdrawCash(loggedInUser);
                        break;
                    case "4":
                        changePIN(loggedInUser);
                        break;
                    case "q":
                        System.out.println("Logging out...");
                        loggedInUser = null;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    public void ShowAuthMenu() {
        System.out.println("\nATM Menu");
        System.out.println("1. Log in into the app");
        System.out.println("2. Register as new client");
        System.out.println("3. Forget Password ? ");
        System.out.println("Q. Quit the machine ATM ");
        System.out.println("Choose an option");
    }

    public void ShowMenu() {
        System.out.println("\nATM Menu");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw money");
        System.out.println("4. Change PIN");
        System.out.println("Q. Quit the machine ATM ");
        System.out.println("Choose an option");
    }

    public void SignUP() {
        System.out.print("Enter Your Name : ");
        String name = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        int AccountNumber = scanner.nextInt();
        System.out.print("Enter Account PIN: ");
        int pin = scanner.nextInt();
        System.out.print("Enter Account balance: ");
        int balance = scanner.nextInt();
        scanner.nextLine();

        User user = new User(name, AccountNumber, pin, balance);
        CreateAccount(user);
        System.out.println("The Account has Successfully Created");
        System.out.println("Now try to log in");
    }

    public User login() {
        System.out.print("Enter Account Number: ");
        int AccountNumber = scanner.nextInt();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        User user = User.getUserFromDB(AccountNumber);
        if (user != null && user.validatePin(pin)) {
            System.out.println("Welcome Mr. " + user.getName());
            System.out.println("Login Successful!");
            return user;
        } else {
            System.out.println("Invalid username or PIN.");
            return null;
        }
    }

    private void checkBalance(User user) {
        System.out.println("Checking Balance....");
        System.out.println("Your Balance is : " + user.getBalance());
    }

    private void depositCash(User user) {
        System.out.println("Deposit Balance....");
        System.out.println("How much cash will you deposit? ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        user.Deposit(amount);
        UpdateBalance(user);
        System.out.println("Your Balance is : " + user.getBalance());
    }

    private void withdrawCash(User user) {
        System.out.println("Withdraw Balance....");
        System.out.println("How much cash will you withdraw? ");
        amount = scanner.nextDouble();
        scanner.nextLine();

        if (user.getBalance() >= amount) {
            user.Withdraw(amount);
            UpdateBalance(user);
            System.out.println("Your Balance is : " + user.getBalance());
        } else {
            System.out.println("Not enough Cash!");
        }
    }

    private void changePIN(User user) {
        System.out.println("Changing PIN....");
        System.out.println("Enter old PIN: ");
        oldPIN = scanner.nextInt();
        scanner.nextLine();

        if (user.getPin() == oldPIN) {
            System.out.println("Enter a new PIN: ");
            int newPIN = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Re-enter new PIN: ");
            int reNewPIN = scanner.nextInt();
            scanner.nextLine();

            if (newPIN == reNewPIN) {
                user.setPin(newPIN);
                UpdatePin(user);
                System.out.println("Your PIN has been successfully changed!");
            } else {
                System.out.println("PINs do not match. PIN change failed.");
            }
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    public void CreateAccount(User user) {
        try (Connection connextion = DatabaseConnection.getConnection()) {
            String updateQuery = "insert into Users (name, account_number, pin, balance) VALUES (?, ?, ?, ?);";
            try (PreparedStatement ps = connextion.prepareStatement(updateQuery)) {
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAccount_number());
                ps.setInt(3, user.getPin());
                ps.setDouble(4, user.getBalance());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateBalance(User user) {
        try (Connection connextion = DatabaseConnection.getConnection()) {
            String updateQuery = "Update Users SET Balance = ? Where id = ?";
            try (PreparedStatement ps = connextion.prepareStatement(updateQuery)) {
                ps.setDouble(1, user.getBalance());
                ps.setInt(2, user.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdatePin(User user) {
        try (Connection connextion = DatabaseConnection.getConnection()) {
            String updateQuery = "Update Users SET pin = ? Where id = ?";
            try (PreparedStatement ps = connextion.prepareStatement(updateQuery)) {
                ps.setInt(1, user.getPin());
                ps.setInt(2, user.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}