# ATM System

## Overview
This is a simple ATM system built in Java that allows users to perform basic banking operations such as checking balance, depositing cash, withdrawing money, and changing the PIN. The system interacts with a database to store user information, including account number, PIN, and balance.

## Features
- **User Authentication**: Users can log in to their account using an account number and PIN.
- **Account Registration**: New users can create an account with a name, account number, PIN, and initial balance.
- **Check Balance**: After logging in, users can check their current balance.
- **Deposit Cash**: Users can deposit money into their account.
- **Withdraw Money**: Users can withdraw money from their account (if sufficient balance is available).
- **Change PIN**: Users can change their account PIN after verifying the old one.

## Requirements
- Java Development Kit (JDK) 8 or higher
- MySQL Database
- JDBC driver for MySQL

 system.

## Classes
- **Main**: The entry point for the application, where the ATM service starts.
- **ATMService**: Handles the ATM logic, including user login, account creation, and transactions.
- **User**: Represents the user entity with methods for depositing, withdrawing, and validating PIN.
- **DatabaseConnection**: Manages the connection to the MySQL database.

## Sample Interaction
```
Welcome to the ATM!

ATM Menu
1. Log in into the app
2. Register as new client
3. Forget Password?
Q. Quit the machine ATM
Choose an option: 1

Enter Account Number: 123456
Enter PIN: 1234
Welcome Mr. John
Login Successful!

ATM Menu
1. Check Balance
2. Deposit Cash
3. Withdraw money
4. Change PIN
Q. Quit the machine ATM
Choose an option: 1

Your Balance is : 1000.0
```

## Future Enhancements
- Implement "Forget Password" functionality.
- Improve security with encrypted PIN storage.
- Add support for multiple currencies.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
