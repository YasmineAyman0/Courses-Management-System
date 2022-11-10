package courses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// connect database by php myadmin with netbeans
//  connection of database actived only one .
// if we make more operations we take constactor from class database.
public class DATABASE1 {
                                                                 
    private static Connection connection;

    public static Connection getconnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            //name of database is course ,by default the user is "root " and the password is " ".
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courses management system", "root", "");
        }
        return connection;
    }

}
