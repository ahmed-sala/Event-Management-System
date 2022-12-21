package eventmanagementsystem;

public class Global {

    private static String databaseName;
    private static String databaseUsername;
    private static String databasePassword;

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getDatabaseUsername() {
        return databaseUsername;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }

    public static void setDatabaseName(String databaseName) {
        Global.databaseName = databaseName;
    }

    public static void setDatabaseUsername(String databaseUsername) {
        Global.databaseUsername = databaseUsername;
    }

    public static void setDatabasePassword(String databasePassword) {
        Global.databasePassword = databasePassword;
    }
    
}
