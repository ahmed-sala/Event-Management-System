package eventmanagementsystem;

import java.sql.*;

public class AccountDB {

    private int CustomerID;
    LoginForm lf;

    public AccountDB() {
    }

    public AccountDB(LoginForm lf) {
        this.lf = lf;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void loginDB(Customer cust) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                String query = "SELECT * from customer\n"
                        + "where name = ? and password = ?;";  // query to be sent
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, cust.getName());
                pst.setString(2, cust.getPassword());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    if (role.equals("Customer")) {
                        CustomerForm mf = new CustomerForm();

                        mf.setVisible(true);

                        mf.pack();
                        mf.setLocationRelativeTo(null);
                        CustomerForm.jLabel_Username.setText("Welcome " + cust.getName() + "");
                        lf.setVisible(false);
                    } else if (role.equals("Project Manager")) {
                        ProjectManagerForm pmf = new ProjectManagerForm();
                        pmf.setVisible(true);
                        pmf.pack();
                        pmf.setLocationRelativeTo(null);
                        ProjectManagerForm.jLabel_Username.setText("Welcome " + cust.getName() + "");
                        lf.dispose();
                    } else if (role.equals("Service Provider")) {
                        ServiceProviderForm spf = new ServiceProviderForm();
                        spf.setVisible(true);
                        spf.pack();
                        spf.setLocationRelativeTo(null);
                        ServiceProviderForm.jLabel_Username.setText("Welcome " + cust.getName() + "");
                        lf.dispose();
                    } else if (role.equals("Admin")) {
                        AdminForm af = new AdminForm();
                        af.setVisible(true);
                        af.pack();
                        af.setLocationRelativeTo(null);
                        AdminForm.jLabel_Username.setText("Welcome " + cust.getName() + "");
                        lf.dispose();
                    }
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

    public void signup(String c_name, int c_age, String c_phone, String c_email, String c_pass) {
        try (
                 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());) {
            if (conn != null) {
                String query = "insert into customer(name, age, phone_no, email, password) values(?, ?, ?, ?, ?);";  // query to be sent
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, c_name);
                pst.setInt(2, c_age);
                pst.setString(3, c_phone);
                pst.setString(4, c_email);
                pst.setString(5, c_pass);
                pst.executeUpdate();
                String query1 = "SELECT * from customer\n"
                        + "where name = ? and password = ?";  // query to be sent
                PreparedStatement pst1 = conn.prepareStatement(query1);
                pst1.setString(1, c_name);
                pst1.setString(2, c_pass);
                ResultSet rs = pst1.executeQuery();
                if (rs.next()) {
                    this.CustomerID = rs.getInt("cust_id");
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
