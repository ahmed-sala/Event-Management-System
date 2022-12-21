package eventmanagementsystem;
import java.sql.*;
import java.util.*;

public class EventDB {

    public String addEvent(String name, String type, String date, int total_cost, int cust_id, int approved) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - adding an event");
                String query = "select * from event";  // selects all data from database
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                query = "INSERT into EVENT(name, type, event_date, total_cost," +
                        " cust_id, approved)" + "values('" + name + "', '" + type + "', " +
                        "STR_TO_DATE('" + date + "','%d/%m/%Y')"  + ", " + total_cost + ", '" +
                        cust_id  + "', " + approved + ")";
                //     System.out.println("query = " + query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }
            else
            {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
    
    public String addEventWithEvent(Event event) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - adding an event");
                String query = "select * from admin";  // selects all data from database
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                query = "INSERT into admin(name, type, event_date, total_cost," +
                        " cust_id, approved)" + "values('" + event.getName() + "', '" + event.getType() + "', " +
                        "STR_TO_DATE('" + event.getDate() + "','%d/%m/%Y')"  + ", " + event.getPrice() + ", '" +
                        event.getCustId()  + "', " + event.getApproved() + ")";
                //     System.out.println("query = " + query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }
            else
            {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
    
    public void displayEventDetails(int id) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying event details");
                String query = "select * from EVENT where EVENT_ID = " + id;  // query to be sent
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String date = rs.getString("event_date");
                    int cost = rs.getInt("total_cost");
                    int cust_id = rs.getInt("cust_id");
                    int approved = rs.getInt("approved");
                    System.out.print("Event ID: ");
                    System.out.println(id);
                    System.out.println("name = " + name);
                    System.out.println("type = " + type);
                    System.out.println("date = " + date);
                    System.out.println("cost = " + cost);
                    System.out.println("approved = " + approved);
                }
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public Event getEvent(int id) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting an event");
                String query;
                query = " SELECT * FROM event where event_id = " + id;  // query to be sent
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {                                            // reads a row
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String date = rs.getString("event_date");
                    int cost = rs.getInt("total_cost");
                    int approved = rs.getInt("approved");
                    Event obj = new Event(name, type, date, cost);
                    return obj;
                }
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Event empty = new Event();
        return empty;
    }
    public ArrayList<Event> getListOfEvents() throws ClassNotFoundException {
        ArrayList<Event> eventList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of all events");
                String query = " SELECT * FROM event";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {                                            // reads a row
                    int id = rs.getInt("event_id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String date = rs.getString("event_date");
                    int cost = rs.getInt("total_cost");
                    int approved = rs.getInt("approved");
                    Event obj = new Event(name,type,date,cost);
                    eventList.add(obj);
                }
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return eventList;
    }
    public HashMap<Integer, Integer> getEventIDs(int id, int IDtype) throws ClassNotFoundException {
        HashMap<Integer, Integer> ids = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting all IDs associated with an event");
                String query = new String();
                if (IDtype == 0)
                    query = " SELECT * FROM event where event_id = " + id;  // query to be sent
                else if (IDtype == 1)
                    query = " SELECT * FROM event where cust_id = " + id;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {
                    int event_id = rs.getInt("event_id");
                    int cust_id = rs.getInt("cust_id");
                    ids = new HashMap<Integer, Integer>();
                    ids.put(event_id, event_id);
                    ids.put(cust_id, cust_id);
                    return ids;
                }
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }
    public void removeEvent(int id) throws ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null) {
                System.out.println("Database - removing an event");
                String query = "delete from event where event_id = " + id;  // selects all data from database
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                query = "commit";
                stmt.executeUpdate(query);
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isDateBooked(String date) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - comparing dates");
                String query = " SELECT event_date FROM event";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {
                    String e_date = rs.getString("event_date");
                    if (e_date.equals(date))
                        return true;
                }
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getCustomerEmailByEventID(int event_id) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Global.getDatabaseName(), Global.getDatabaseUsername(), Global.getDatabasePassword());
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting customer email by event ID");
                String query = "select c.email from customer c, event e where c.cust_id in (select cust_id from event where event_id = " + event_id + ")";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {
                    return rs.getString("email");
                }
            }
            else {
                System.out.println("Failed to make connection!");
            }
        }
        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
