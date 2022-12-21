/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagementsystem;

import java.util.Scanner;

/**
 *
 * @author Kimo Store
 */
public class Contact {

    public int custId;
    public String message, email, name;
    public int pmId;

    public Contact() {
        this(0, "", "", "", 0);
    }

    public Contact(int custId, String name, String email, String message, int pmId) {
        this.message = message;
        this.email = email;
        this.name = name;
        this.custId = custId;
        this.pmId = pmId;
    }

    public String getContactEmail() {
        return this.email;
    }

    public String getContactMessage() {
        return this.message;
    }

    public String getContactName() {
        return this.name;
    }

    public int getContactCustId() {
        return this.custId;
    }

    public int getContactPmId() {
        return this.pmId;
    }

    public void setContactName(String name) {
        this.name = name;
    }

    public void setContactEmail(String email) {
        this.email = email;
    }

    public void setContactMessage(String message) {
        this.message = message;
    }

    private void inputContactDetails() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        this.name = input.nextLine();

        System.out.print("Enter your Email: ");
        this.email = input.nextLine();

        System.out.print("Enter your Detailed message: ");
        this.message = input.nextLine();
    }
//    public void getContactDetails(){
//        this.inputContactDetails();
//        ContactDB contactDB=new ContactDB();
//        contactDB.addContact(custId, name, email, message, Contact_id);
//    }

    public void getContactWithParamater(int id, String name, String email, String message, int Contact_id) {
        ContactDB contactDB = new ContactDB();
        contactDB.addContact(id, name, email, message, Contact_id);
    }

    public void sendContact(Contact contact) {
        ContactDB contactDB = new ContactDB();
        contactDB.addContactDB(contact);
    }
    
}
