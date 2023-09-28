package Assignment_1;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Select {
    public static void main(String[] args) {
        String dbID = "tt",
                dbPassword = "tt",
                dbURL = "jdbc:postgresql://localhost/",
                dbName = "postgres";
        List<Student> studentList = new ArrayList<> ();
        String SQL_SELECT = "SELECT * FROM STUDENT";
        try {
            // Connection
            Connection conn = DriverManager.getConnection(dbURL+dbName, dbID, dbPassword);

            // SELECT QUERY
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int rollID = resultSet.getInt("ROLL");
                String name = resultSet.getString("NAME");
                String section = resultSet.getString("SECTION");
                Timestamp createdDate = resultSet.getTimestamp("CREATED_DATE");
                Student student = new Student();
                student.setRoll(rollID);
                student.setName(name);
                student.setSection(section);
                student.setCreatedDate(createdDate.toLocalDateTime());
                studentList.add(student);
            }
            for (Student stu: studentList) {
                System.out.println("Roll No : " + stu.getRoll());
                System.out.println("Name : " + stu.getName());
                System.out.println("Section : " + stu.getSection());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Student {
    int rollID;
    String name, section;
    LocalDateTime createdDate;

    public Student() {
        rollID = -1;
        name = "";
        section = "";
    }

    public void setRoll(int rollID) {
        this.rollID = rollID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public int getRoll() {
        return this.rollID;
    }
    public String getName() {
        return this.name;
    }
    public String getSection() {
        return this.section;
    }
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }
}
