import java.sql.*;

public class test {
    public static void main(String[] args) {
        try {
            String dbID = "tt",
                    dbPassword = "tt",
                    dbURL = "jdbc:postgresql://localhost/",
                    dbName = "prac1";
            
            Connection connection = DriverManager.getConnection(dbURL+dbName, dbID, dbPassword);
            System.out.println("Successed to connect.");
        } catch (SQLException e) {
            System.out.println("Failed to connect.");
            System.out.println("Error message : " + e.getMessage());
        }
    }
}