package auth.DAO;

import java.sql.*;

public class UsersDAO {

    public boolean isExistingEmail(String email){ // verifier
        // database query checks if email existed for user
        try(
            Connection conn = ConnectionProvider.createConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from user_accounts where email = ?")
        ){
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // returns true if email is found in the database.   
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    };

    public String getDBPasswordHash(String email){
        try(
            Connection conn = ConnectionProvider.createConnection();
            PreparedStatement stmt = conn.prepareStatement("select password_hash from user_accounts where email = ?")
        ){
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            // returns true if email and password match in the database.
            while(rs.next()){
                String dbHashedPassword = rs.getString("password_hash");
                return dbHashedPassword;
            } 
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerUser(String email, String password){ // sign up
        if(!isExistingEmail(email)){
            // sends user info to the db registering the user. Succesfully registering the user
            try(
                Connection conn = ConnectionProvider.createConnection();
                PreparedStatement stmt = conn.prepareStatement("insert into user_accounts (email, password_hash) values(?, ?)")
            ) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.executeUpdate();

                return true;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
