/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagementsystem;

import java.sql.SQLException;

/**
 *
 * @author Kimo Store
 */
public class Admin extends Account {

    private String role;

    public Admin() {
        this("", "", 0, "", "", "");
    }

    public void displayProjectManagerEvents() throws SQLException, ClassNotFoundException {
        AdminDB obj = new AdminDB();
        obj.displayAdminEvents();
    }
//enable customer to creat account

    public Admin(String name, String password, int age, String phone, String gender, String email) {
        super(name, age, gender, email, phone, password);
    }

    public Admin(String role, String name, String password, int age, String phone, String email) {
        super(name, age, email, phone, password);
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void updateField(int id, String field, String new_value) {
        AdminDB admin = new AdminDB();
        admin.updateField(id, field, new_value);
    }

    public void changeCustomerPassword(int id, String new_pass) {
        AdminDB admin = new AdminDB();
        admin.changeCustomerPassword(id, new_pass);
    }

    public void removeCustomer(int id) {
        AdminDB admin = new AdminDB();
        admin.removeCustomer(id);
    }

    public void addUser(Admin admin) {
        AdminDB adminDB = new AdminDB();
        adminDB.addUsersWithAdmin(admin);
    }
    
}
