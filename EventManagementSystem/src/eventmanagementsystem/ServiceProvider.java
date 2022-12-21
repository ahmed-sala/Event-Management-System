/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eventmanagementsystem;

import java.sql.SQLException;

/**
 *
 * @author Magdy
 */
public class ServiceProvider extends Account {

    public void displayServiceProviderEvents() throws SQLException, ClassNotFoundException {
        ServiceProviderDB obj = new ServiceProviderDB();
        obj.displayServiceProviderEvents();
    }

    public void updateEventPrice(int eventid, int total_cost) {
        ServiceProviderDB obj = new ServiceProviderDB();
        obj.updateEventPrice(eventid, total_cost);
    }

    public void approveEvent(int event_id) {
        ServiceProviderDB obj = new ServiceProviderDB();
        obj.approveEvent(event_id);
    }

}
