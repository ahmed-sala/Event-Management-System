package eventmanagementsystem;

import java.util.*;

public class Event {

    private int event_id;
    private String name;
    private String type;
    private String date;
    private int price;
    private int approved;
    private int cust_id;

    public Event() {
        name = type = date = "";
    }

    public Event(String name, String type, String date, int price, int cust_id) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.price = price;
        this.cust_id = cust_id;
    }

    public Event(String name, String type, String date, int cust_id) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.cust_id = cust_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public int getCustId() {
        return cust_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int app) {
        this.approved = app;
    }

    private void inputInitialEventDetails() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter event name: ");
        this.name = input.nextLine();
        System.out.print("Enter event type: ");
        this.type = input.nextLine();
        System.out.print("Enter event date: ");
        this.date = input.nextLine();
    }

    public void bookEventWithInput(int cust_id) throws ClassNotFoundException {
        this.inputInitialEventDetails();
        EventDB eventDB = new EventDB();
        eventDB.addEvent(name, type, date, price, cust_id, 0);
    }

    public void bookEvent(int cust_id) throws ClassNotFoundException {
        EventDB eventDB = new EventDB();
        eventDB.addEvent(name, type, date, price, cust_id, 0);
    }

    public Event getEvent(int id) throws ClassNotFoundException {
        EventDB obj = new EventDB();
        return obj.getEvent(id);
    }

    public ArrayList<Event> getListOfEvents() throws ClassNotFoundException {
        EventDB obj = new EventDB();
        return obj.getListOfEvents();
    }

    public void deleteEvent(int id) throws ClassNotFoundException {
        EventDB obj = new EventDB();
        obj.removeEvent(id);
    }

    public boolean isDateBooked() throws ClassNotFoundException {
        EventDB obj = new EventDB();
        return obj.isDateBooked(this.getDate());
    }

    public String getCustomerEmailByEventID(int id) throws ClassNotFoundException {
        EventDB obj = new EventDB();
        return obj.getCustomerEmailByEventID(id);
    }

    public void addEvent(Event event) throws ClassNotFoundException {
        EventDB eventDB = new EventDB();
        eventDB.addEventWithEvent(event);
    }
    
}
