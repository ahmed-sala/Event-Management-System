/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eventmanagementsystem;

/**
 *
 * @author pc
 */
public abstract class Account {

    private String name;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String password;
    private String role;

    public Account() {
        this.name = "";
        this.age = 0;
        this.gender = "";
        this.email = "";
        this.phone = "";
        this.password = "";
    }

    public Account(String name, int age, String gender, String email, String phone, String password) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Account(String name, int age, String email, String phone, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
