package hsenid.webapp;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by hsenid on 3/3/16.
 */
public class DBCon {

    private static Connection connection;

    public static void CreateConnection(String host, String database, String dbuser, String dbpass) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            /**
             * Create and initialize the database connection.
             */
            connection = (Connection) DriverManager.getConnection(host + database, dbuser, dbpass);

        } catch (Exception ex) {            
        }
    }
    
    public static Connection getConnection(){
        return connection;
    }
}
