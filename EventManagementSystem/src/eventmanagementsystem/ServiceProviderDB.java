/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagementsystem;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Ahmad
 */
public class ServiceProviderDB {

    public void displayServiceProviderEvents() throws SQLException, ClassNotFoundException {

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
            String selectSql = "SELECT name, type, event_date, total_cost, cust_id, approved FROM projectmanager";
            ResultSet resultSet = statement.executeQuery(selectSql);
            // Insert the data into the serviceprovider table
            String insertSql = "INSERT INTO serviceprovider (name, type, event_date, total_cost, cust_id, approved) VALUES (?, ?, ?, ?, ?, ?)";
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

            // Close the connection
//        try {
//            // Execute a SELECT query to retrieve the data
//            try ( // Open a connection to the database
//                    Connection connection = DriverManager.getConnection(
//                            "jdbc:mysql://localhost:3306/serverproviderdb",
//                            "root",
//                            "9639"
//                    )) {
//                // Execute a SELECT query to retrieve the data
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(
//                        "SELECT event_sp_id, event_name, type, event_date, total_cost, cus_id, approved " +
//                                "FROM projectmangerevents"
//                );      // Iterate through the result set and print the data
//                while (resultSet.next()) {    
//                    System.out.println(
//                            "event_sp_id: " + resultSet.getInt("event_sp_id") + ", " +
//                                    "event_name: " + resultSet.getString("event_name") + ", " +
//                                            "type: " + resultSet.getString("type") + ", " +
//                                                    "event_date: " + resultSet.getDate("event_date") + ", " +
//                                                            "total_cost: " + resultSet.getDouble("total_cost") + ", " +
//                                                                    "cus_id: " + resultSet.getInt("cus_id") + ", " +
//                                                                            "approved: " + resultSet.getBoolean("approved")
//                    ); }
//                // Close the connection to the database
//            }
//            } catch (SQLException ex) {
//            // Handle any SQL errors
//             ex.printStackTrace();
//            }
        }
    }

    public void updateEventPrice(int event_id, int total_cost) {
        try {
            // Open a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());

            // Execute an UPDATE query to update the data
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "UPDATE event SET total_cost = " + total_cost + " WHERE event_id = " + event_id
            );

            // Close the connection to the database
            connection.close();
        } catch (SQLException ex) {
            // Handle any SQL errors
            ex.printStackTrace();
        }
    }

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
            ps = connection.prepareStatement("SELECT * FROM serviceprovider");
            ResultSet resultSet = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Insert the data into the serviceprovider table
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

    public void approveEvent(int event_id) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database: Manager approving event");

                String query = "UPDATE event SET approved = 1 WHERE event_id = " + event_id + "";

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCostAndApprovement(JTable jTable1) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                Statement st;
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                st = conn.createStatement();
                for (int i = 0; i < model.getRowCount(); i++) {
                    int id = Integer.parseInt(model.getValueAt(i, 0).toString());
                    String name = model.getValueAt(i, 1).toString();
                    String type = model.getValueAt(i, 2).toString();
                    String date = model.getValueAt(i, 3).toString();
                    int total_cost = Integer.parseInt(model.getValueAt(i, 4).toString());
                    int cust_id = Integer.parseInt(model.getValueAt(i, 5).toString());
                    int approved = Integer.parseInt(model.getValueAt(i, 6).toString());
                    String updateQuery = "UPDATE `serviceprovider` SET `name`='" + name + "',`type`='" + type + "',`event_date`='" + date + "',`total_cost`=" + total_cost + ",`cust_id`=" + cust_id + ",`approved`=" + approved + " WHERE `event_id`=" + id + ";";
                    st.addBatch(updateQuery);
                    String updateQuery1 = "UPDATE `projectmanager` SET `name`='" + name + "',`type`='" + type + "',`event_date`='" + date + "',`total_cost`=" + total_cost + ",`cust_id`=" + cust_id + ",`approved`=" + approved + " WHERE `event_id`=" + id + ";";
                    st.addBatch(updateQuery1);
                    String updateQuery2 = "UPDATE `admin` SET `name`='" + name + "',`type`='" + type + "',`event_date`='" + date + "',`total_cost`=" + total_cost + ",`cust_id`=" + cust_id + ",`approved`=" + approved + " WHERE `event_id`=" + id + ";";
                    st.addBatch(updateQuery2);
                }
                int[] updatedRow = st.executeBatch();
                System.out.println(updatedRow.length);
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
