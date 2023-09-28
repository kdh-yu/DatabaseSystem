package Assignment_1;
import java.sql.*;
import java.util.Date;

public class Insert {
    public static void main(String[] args) {
        String dbID = "tt",
                dbPassword = "xbxhdbxbqm1-",
                dbURL = "jdbc:postgresql://localhost/",
                dbName = "postgres";
        String SQL_INSERT = "INSERT INTO STUDENT (ROLL, NAME, SECTION, CREATED_DATE) VALUES (?, ?, ?, ?)";
        try {
            // Connection
            Connection conn = DriverManager.getConnection(dbURL+dbName, dbID, dbPassword);

            // INSERT QUERY
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, 06);
            preparedStatement.setString(2, "Arnold");
            preparedStatement.setString(3, "A");
            preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
            preparedStatement.executeUpdate();
            System.out.println("record inserted successfully");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}