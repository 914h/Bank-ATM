import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bankatm";
    private static final String USER = "root"; // Default XAMPP MySQL username
    private static final String PASSWORD = ""; // Default XAMPP MySQL password (empty)
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER , PASSWORD);
    }
}
