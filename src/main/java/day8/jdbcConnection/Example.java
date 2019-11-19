package day8.jdbcConnection;

import java.sql.*;

public class Example {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/useradmin", "root", "root");
            Statement statement = connection.createStatement();
            String query = "select * from users";
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            System.out.println(rs.getString(3));
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
