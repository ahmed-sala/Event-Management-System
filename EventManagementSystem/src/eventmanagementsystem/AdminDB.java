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
public class AdminDB {

    public void displayAdminEvents() throws SQLException, ClassNotFoundException {

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
            String selectSql = "SELECT name, type, event_date, total_cost, cust_id, approved FROM event";
            ResultSet resultSet = statement.executeQuery(selectSql);
            // Insert the data into the serverprovider table
            String insertSql = "INSERT INTO admin (name, type, event_date, total_cost, cust_id, approved) VALUES (?, ?, ?, ?, ?, ?)";
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

    public void updateField(int id, String field, String new_value) {
        System.out.println("field = " + field);
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - updating a field for customer");

                String query = "update CUSTOMER set " + field + " = '" + new_value + "' where CUST_ID = " + id;

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                //   System.out.println("query = " + query);
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

    public void changeCustomerPassword(int id, String new_pass) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - changing customer password");

                String query = "update customer set password = '" + new_pass + "' where CUST_ID = " + id + "";

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

    public void removeCustomer(int id) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - deleting customer");

                String query = "delete from customer where cust_id = " + id;  // selects all data from database

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

    public void addUsers(String c_name, int c_age, String c_phone, String c_email, String c_pass, String role) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                Statement stmt = (Statement) conn.createStatement();
                String query = "insert into customer(name, age, phone_no, email, password, role) values('" + c_name + "', " + c_age + ", '" + c_phone + "', '" + c_email + "', '" + c_pass + "', '" + role + "');";
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

    public void addUsersWithAdmin(Admin admin) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                Statement stmt = (Statement) conn.createStatement();
                String query = "insert into customer(name, age, phone_no, email, password, role) values('" + admin.getName() + "', " + admin.getAge() + ", '" + admin.getPhone() + "', '" + admin.getEmail() + "', '" + admin.getEmail() + "', '" + admin.getRole() + "');";
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

    public void editUsers(JTable jTable1) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                Statement st;
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                st = conn.createStatement();
                for (int i = 0; i < model.getRowCount(); i++) {
                    int id = Integer.parseInt(model.getValueAt(i, 0).toString());
                    String name = model.getValueAt(i, 1).toString();
                    int age = Integer.parseInt(model.getValueAt(i, 2).toString());
                    String phone = model.getValueAt(i, 3).toString();
                    String email = model.getValueAt(i, 4).toString();
                    String password = model.getValueAt(i, 5).toString();
                    String role = model.getValueAt(i, 6).toString();
                    String updateQuery = "UPDATE `customer` SET `name`='" + name + "',`age`=" + age + ",`phone_no`='" + phone + "',`email`='" + email + "',`password`='" + password + "',`role`='" + role + "' WHERE `cust_id`=" + id + ";";
                    st.addBatch(updateQuery);
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
            ps = connection.prepareStatement("SELECT * FROM admin");
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

    public void AdminToProjectManager(JTable jTable1) throws SQLException, ClassNotFoundException {
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
            // Select rows from the projectmanager table
            String selectSql = "SELECT name, type, event_date, total_cost, cust_id, approved FROM admin where event_id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(selectSql);
            // Insert the data into the serviceprovider table
            String insertSql = "INSERT INTO projectmanager(name, type, event_date, total_cost, cust_id, approved) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            if (resultSet.next()) {
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
    
}
