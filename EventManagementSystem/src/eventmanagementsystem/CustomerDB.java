package eventmanagementsystem;

import java.sql.*;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class CustomerDB {

    public Customer getCustomer(String id) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - getting a customer");

                String query = "select * from customer where CUST_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {                                            // reads a row
                    String name = rs.getString("name");             // finds a column in the row
                    String phone = rs.getString("phone_no");
                    int age = rs.getInt("age");

                    Customer cust = new Customer(name, phone, age);
                    return cust;
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer emptyObj = new Customer();
        return emptyObj;
    }

    public Customer getCustomerByEmail(String email) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - getting a customer by email");

                String query = "select * from customer where email = '" + email + "'";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {                                            // reads a row
                    String name = rs.getString("name");             // finds a column in the row
                    String phone = rs.getString("phone_no");
                    int age = rs.getInt("age");

                    Customer cust = new Customer(name, phone, age);
                    return cust;
                }
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Customer emptyObj = new Customer();
        return emptyObj;
    }

    public void insertCustomer() {
        Customer cust = new Customer();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter name: ");
        cust.setName(input.nextLine());

        System.out.print("Enter email: ");
        cust.setEmail(input.nextLine());

        System.out.print("Enter age: ");
        cust.setAge(input.nextInt());

        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - inserting a customer");

                String query = "select * from customer";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";
                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("cust_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into customer(CUST_ID, name, cnic, age, phone_no, email, ACCOUNT_NUMBER, PRIORITY_STATUS)"
                        + "values('" + new_id + "','" + cust.getName() + "','" + "'," + Integer.toString(cust.getAge()) + ",'"
                        + "','" + cust.getEmail() + "','" + "'," + ")";

                //System.out.println(query);
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
    }

    public String insertCustomerWithPassword(Customer cust, String pass) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - inserting a customer + password");

                String query = "select * from customer";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("cust_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into customer(CUST_ID, name, cnic, age, phone_no, email, ACCOUNT_NUMBER, PRIORITY_STATUS)"
                        + "values('" + new_id + "','" + cust.getName() + "','" + "'," + Integer.toString(cust.getAge()) + ",'"
                        + "','" + cust.getEmail() + "','" + ")";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                return new_id;
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

    public void insertCustomer(Customer cust, String id) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - inserting customer + id!");

                Statement stmt = conn.createStatement();

                String query = "insert into customer(CUST_ID, name, cnic, age, phone_no, email, ACCOUNT_NUMBER, PRIORITY_STATUS)"
                        + "values('" + id + "','" + cust.getName() + "','" + "'," + Integer.toString(cust.getAge()) + ",'"
                        + "','" + cust.getEmail() + "','" + "'," + ")";

                //System.out.println(query);
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
    }

    public String getCustomerIDbyEmail(String email) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - returning customer ID against an email");
                String query;

                query = "SELECT cust_id FROM customer WHERE email = '" + email + "'";
                System.out.println("query = " + query);

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    return rs.getString("cust_id");
                }
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

    public String getCustomerEmailByID(String ID) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - returning customer email against an ID");
                String query;

                query = "SELECT email FROM customer WHERE cust_id = '" + ID + "'";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    return rs.getString("email");
                }
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

    public void fillCustomerJTable(JTable table) throws ClassNotFoundException, SQLException {

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
            ps = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Insert the data into the customer table
            Object[] row;
            while (resultSet.next()) {
                row = new Object[7];
                row[0] = resultSet.getInt(1);
                row[1] = resultSet.getString(2);
                row[2] = resultSet.getInt(3);
                row[3] = resultSet.getString(4);
                row[4] = resultSet.getString(5);
                row[5] = resultSet.getString(6);
                row[6] = resultSet.getString(7);
                model.addRow(row);
            }
            connection.close();
        }
    }

    public void fillProjectManagerJTable(JTable table) throws ClassNotFoundException, SQLException {
        String role = "Project Manager";
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
            ps = connection.prepareStatement("SELECT * FROM customer where role = '" + role + "';");
            ResultSet resultSet = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Insert the data into the projectmanager table
            Object[] row;
            while (resultSet.next()) {
                row = new Object[2];
                row[0] = resultSet.getInt(1);
                row[1] = resultSet.getString(2);
                model.addRow(row);
            }
            connection.close();
        }
    }

    public void fillCustomerRoleJTable(JTable table) throws ClassNotFoundException, SQLException {
        String role = "Customer";
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
            ps = connection.prepareStatement("SELECT * FROM customer where role = '" + role + "';");
            ResultSet resultSet = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            // Insert the data into the projectmanager table
            Object[] row;
            while (resultSet.next()) {
                row = new Object[2];
                row[0] = resultSet.getInt(1);
                row[1] = resultSet.getString(2);
                model.addRow(row);
            }
            connection.close();
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
            ps = connection.prepareStatement("SELECT * FROM event");
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

    public void displayEvent(JTable table) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                System.out.println("Database - displaying event details");
                String query = "select * from event";
                PreparedStatement pst = conn.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
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
