package auth.DAO;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class ConnectionProvider {
    private static final String USER = "root"; // mysql username (default)
    private static final String  PASSWORD = ""; // your mysql password

    public static Connection createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_lms", USER, PASSWORD); // modify url as needed
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex){
            Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }   
}
