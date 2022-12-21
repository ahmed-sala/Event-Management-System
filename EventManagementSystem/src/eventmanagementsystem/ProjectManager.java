package eventmanagementsystem;

import java.sql.*;

public class ProjectManager extends Account {

    public ProjectManager() {
        super();
    }

    /////////////// CALLING Project Manager DATABASE METHODS ///////////////
    public void displayProjectManagerEvents() throws SQLException, ClassNotFoundException {
        ProjectManagerDB obj = new ProjectManagerDB();
        obj.displayProjectManagerEvents();
    }
    
}
