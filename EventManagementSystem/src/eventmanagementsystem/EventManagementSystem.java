package eventmanagementsystem;

public class EventManagementSystem {

    public static void main(String[] args) throws ClassNotFoundException {
        setMySQL();
        setEmailDetails();
        LoginForm lf = new LoginForm();
        lf.setVisible(true);
        lf.pack();
        lf.setLocationRelativeTo(null);
    }

    public static void setMySQL() {
        Global.setDatabaseName("EventManagementSystem");
        Global.setDatabaseUsername("root");
        Global.setDatabasePassword("");
    }

    public static void setEmailDetails() {
        EmailDetails.init(System.getProperties(), "eventmanagementsystem8@gmail.com", "twulgdbuvpyrqbbl");
    }
    
}
