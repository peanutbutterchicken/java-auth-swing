package auth.controller;

import auth.org.mindrot.jbcrypt.BCrypt;

import auth.DAO.UsersDAO;
import auth.model.Users;

public class SigninController {
    UsersDAO usersDAO = new UsersDAO();
    
    public String loginUser(Users user){

        if(!usersDAO.isExistingEmail(user.getEmail())){
            return "No Registered Email";
        }
        // Check plaintext input password from user if it matches with retrieved hashed password from DB.
        boolean result = BCrypt.checkpw(String.valueOf(user.getPassword()), usersDAO.getDBPasswordHash(user.getEmail()));

        if(!result){
            return "Incorrect username or password";
        }
        Users.setCurrentUser(user);
        return "SUCCESS";
    }

    public String userFieldValidation(Users user){
        if(user.getEmail().isEmpty() || user.getPassword().length == 0){    // Verify input on fields before database query
            return "EMPTY";
        }
        if(!EmailService.validateEmail(user.getEmail())){   // checks email validity before using database.
            return "INVALID EMAIL";
        }
        String result = loginUser(user);    // Check input fields validity on the database
        if(result == "SUCCESS"){
            return "SUCCESS";
        }
        return result;
    }
}
