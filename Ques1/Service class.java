ServiceClass:import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceClass extends DBConnection {

    public boolean insertDB(String deptName, String numStudents) {
        this.getConnection();
        // MYSTUDENT এর পরিবর্তে DEPARTMENT টেবিল ব্যবহার করা হয়েছে
        String sql = "INSERT INTO DEPARTMENT(DEPT_NAME, NUM_STUDENTS) VALUES(?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptName);
            ps.setString(2, numStudents);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    public List<String> viewDB() {
        List<String> result = new ArrayList<>();
        this.getConnection();
        String sql = "SELECT DEPT_NAME, NUM_STUDENTS FROM DEPARTMENT";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                // TestServlet.java এর স্প্লিট লজিকের সাথে মিলিয়ে স্ট্রিং বানানো হয়েছে
                result.add("Department: " + rs.getString("DEPT_NAME") + ", Number of Students: " + rs.getString("NUM_STUDENTS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }

    public boolean updateDB(String deptName, String numStudents) {
        this.getConnection();
        String sql = "UPDATE DEPARTMENT SET NUM_STUDENTS = ? WHERE DEPT_NAME = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, numStudents);
            ps.setString(2, deptName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }

    public boolean deleteDB(String deptName) {
        this.getConnection();
        String sql = "DELETE FROM DEPARTMENT WHERE DEPT_NAME = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return false;
    }
}
