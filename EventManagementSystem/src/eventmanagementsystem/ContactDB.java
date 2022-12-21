/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagementsystem;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kimo Store
 */
public class ContactDB {

    public String addContact(int cid, String name, String email, String message, int pmid) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - adding a contact");

                String query = "select * from contact";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                query = "insert into contact(id, name, email, message, contact_id) values(" + cid + ",'" + name + "','" + email + "','" + message + "', " + pmid + ");";

                //     System.out.println("query = " + query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String addContactDB(Contact contact) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - adding a contact");

                String query = "select * from contact";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                query = "insert into contact(id, name, email, message, contact_id) values(" + contact.getContactCustId() + ",'" + contact.getContactName() + "','" + contact.getContactEmail() + "','" + contact.getContactMessage() + "', " + contact.getContactPmId() + ");";

                //     System.out.println("query = " + query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
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
            ps = connection.prepareStatement("SELECT * FROM contact");
            ResultSet resultSet = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Insert the data into the customer table
            Object[] row;
            while (resultSet.next()) {
                row = new Object[5];
                row[0] = resultSet.getInt(1);
                row[1] = resultSet.getString(2);
                row[2] = resultSet.getString(3);
                row[3] = resultSet.getString(4);
                row[4] = resultSet.getInt(5);
                model.addRow(row);
            }
            connection.close();
        }
    }

    public void displayContactDetails() {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - displaying Contact details");

                String query = "select * from contact ";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String message = rs.getString("message");

                    System.out.println("name = " + name);
                    System.out.println("type = " + email);
                    System.out.println("date = " + message);

                    System.out.println("Displaying details of chosen menu:\n");

                }
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
