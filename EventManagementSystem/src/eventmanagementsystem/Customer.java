/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagementsystem;

/**
 *
 * @author Ahmed Hany
 */
public class Customer extends Account {

    private boolean booking;
    private static String mangername = "Ahmed";//you can edit it
    private static String mangeremail = "mangername@gmail.com";
    LoginForm l;

    public Customer() {
        this("", "", 0, "", "", "");
    }

    public Customer(String name, String passowrd, LoginForm l) {
        super.setName(name);
        super.setPassword(passowrd);
        this.l = l;
    }
//enable customer to creat account

    public Customer(String name, String password, int age, String phone, String gender, String email) {
        super(name, age, gender, email, phone, password);
    }

    public Customer(String name, String phone, int age) {
        super.setName(name);
        super.setAge(age);
        super.setPhone(phone);
    }
// enable customer to book

    private boolean checkInformationstatic() {
        if (super.getAge() >= 20) {
            return booking = true;
        } else {
            return booking = false;
        }
        //you can edit it
    }

    public void bookEventWithInput(int cust_id) throws ClassNotFoundException {
        Event event = new Event();
        event.bookEventWithInput(cust_id);
    }

    public void bookEvent(int cust_id) throws ClassNotFoundException {
        Event event = new Event();
        event.bookEvent(cust_id);
    }

    public String getCustomerEmailByEventID(int id) throws ClassNotFoundException {
        Event obj = new Event();
        return obj.getCustomerEmailByEventID(id);
    }

    public void logIn(Customer cust) {
        AccountDB acc = new AccountDB(l);
        acc.loginDB(cust);
    }
    
}
