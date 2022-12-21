package eventmanagementsystem;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProjectManagerDB {

    public void fillStudentJTable(JTable table) throws ClassNotFoundException, SQLException {

        // Replace "username" and "password" with your MySQL username and password
        String url = "jdbc:mysql://localhost:3306/" + Global.getDatabaseName();
        String username = Global.getDatabaseUsername();
        String password = Global.getDatabasePassword();

        // Load the MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create a statement
        try ( // Connect to the database
                 Connection connection = DriverManager.getConnection(url, username, password)) {
            // Create a statement
            PreparedStatement ps;
            ps = connection.prepareStatement("SELECT * FROM projectmanager");
            ResultSet resultSet = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Insert the data into the customer table
            Object[] row;
            while (resultSet.next()) {
                row = new Object[7];
                row[0] = resultSet.getInt(1);
                row[1] = resultSet.getString(2);
                row[2] = resultSet.getString(3);
                row[3] = resultSet.getDate(4);
                row[4] = resultSet.getInt(5);
                row[5] = resultSet.getInt(6);
                row[6] = resultSet.getInt(7);
                model.addRow(row);
            }
            connection.close();
        }
    }

    public void displayProjectManagerEvents() throws SQLException, ClassNotFoundException {

        // Replace "username" and "password" with your MySQL username and password
        String url = "jdbc:mysql://localhost:3306/" + Global.getDatabaseName();
        String username = Global.getDatabaseUsername();
        String password = Global.getDatabasePassword();

        // Load the MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create a statement
        try ( // Connect to the database
                 Connection connection = DriverManager.getConnection(url, username, password)) {
            // Create a statement
            Statement statement = connection.createStatement();
            // Select rows from the projectmanager table
            String selectSql = "SELECT name, type, event_date, total_cost, cust_id, approved FROM admin";
            ResultSet resultSet = statement.executeQuery(selectSql);
            // Insert the data into the serviceprovider table
            String insertSql = "INSERT INTO projectmanager (name, type, event_date, total_cost, cust_id, approved) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            while (resultSet.next()) {
//                insertStatement.setInt(1, resultSet.getInt("event_id"));
                insertStatement.setString(1, resultSet.getString("name"));
                insertStatement.setString(2, resultSet.getString("type"));
                insertStatement.setDate(3, resultSet.getDate("event_date"));
                insertStatement.setInt(4, resultSet.getInt("total_cost"));
                insertStatement.setInt(5, resultSet.getInt("cust_id"));
                insertStatement.setInt(6, resultSet.getInt("approved"));
                insertStatement.executeUpdate();
            }
            connection.close();
        }
    }

    public Event receiveRequest(int id) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - getting an Customer Request");
                String query = " SELECT * FROM event where cust_id = " + id;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {                                            // reads a row
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String date = rs.getString("event_date");
                    int cost = rs.getInt("total_cost");
                    int approved = rs.getInt("approved");
                    System.out.println("Event ID: " + id);
                    System.out.println("name = " + name);
                    System.out.println("type = " + type);
                    System.out.println("date = " + date);
                    System.out.println("cost = " + cost);
                    System.out.println("approved = " + approved);
                    Event obj = new Event(name, type, date, cost);
                    return obj;
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Event empty = new Event();
        return empty;
    }

    public void ProjectManagerToServiceProvider(JTable jTable1) throws SQLException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int id = Integer.parseInt(model.getValueAt(model.getRowCount() - 1, 0).toString());
        // Replace "username" and "password" with your MySQL username and password
        String url = "jdbc:mysql://localhost:3306/" + Global.getDatabaseName();
        String username = Global.getDatabaseUsername();
        String password = Global.getDatabasePassword();

        // Load the MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create a statement
        try ( // Connect to the database
                 Connection connection = DriverManager.getConnection(url, username, password)) {
            // Create a statement
            Statement statement = connection.createStatement();
            // Select rows from the customer table
            String selectSql = "SELECT name, type, event_date, total_cost, cust_id, approved FROM projectmanager where event_id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(selectSql);
            // Insert the data into the projectmanager1 table
            String insertSql = "INSERT INTO serviceprovider(name, type, event_date, total_cost, cust_id, approved) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            while (resultSet.next()) {
//                insertStatement.setInt(1, resultSet.getInt("eventId"));
                insertStatement.setString(1, resultSet.getString("name"));
                insertStatement.setString(2, resultSet.getString("type"));
                insertStatement.setDate(3, resultSet.getDate("event_date"));
                insertStatement.setInt(4, resultSet.getInt("total_cost"));
                insertStatement.setInt(5, resultSet.getInt("cust_id"));
                insertStatement.setInt(6, resultSet.getInt("approved"));
                insertStatement.executeUpdate();
            }
            connection.close();
        }
    }

    public void ProjectManagerToCustomer(JTable jTable1) throws SQLException, ClassNotFoundException {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int id = Integer.parseInt(model.getValueAt(model.getRowCount() - 1, 0).toString());
        // Replace "username" and "password" with your MySQL username and password
        String url = "jdbc:mysql://localhost:3306/" + Global.getDatabaseName();
        String username = Global.getDatabaseUsername();
        String password = Global.getDatabasePassword();

        // Load the MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create a statement
        try ( // Connect to the database
                 Connection connection = DriverManager.getConnection(url, username, password)) {
            // Create a statement
            Statement statement = connection.createStatement();
            // Select rows from the customer table
            String selectSql = "SELECT name, type, event_date, total_cost, cust_id, approved FROM projectmanager where event_id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(selectSql);
            // Insert the data into the projectmanager1 table
            String insertSql = "INSERT INTO event(name, type, event_date, total_cost, cust_id, approved) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            while (resultSet.next()) {
//                insertStatement.setInt(1, resultSet.getInt("eventId"));
                insertStatement.setString(1, resultSet.getString("name"));
                insertStatement.setString(2, resultSet.getString("type"));
                insertStatement.setDate(3, resultSet.getDate("event_date"));
                insertStatement.setInt(4, resultSet.getInt("total_cost"));
                insertStatement.setInt(5, resultSet.getInt("cust_id"));
                insertStatement.setInt(6, resultSet.getInt("approved"));
                insertStatement.executeUpdate();
            }
            connection.close();
        }
    }
    
}
