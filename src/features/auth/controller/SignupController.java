package auth.controller;

import auth.org.mindrot.jbcrypt.BCrypt;

import auth.DAO.UsersDAO;
import auth.model.Users;

import java.util.Random;

public class SignupController {

    UsersDAO usersDAO = new UsersDAO();
    Random random = new Random();

    public String userFieldValidation(Users user, char[] confirmPassword) {
        if(user.getEmail().isEmpty() || user.getPassword().length==0 || confirmPassword.length==0){
            return "EMPTY";
        }
        if(!String.valueOf(user.getPassword()).equals(String.valueOf(confirmPassword))){ // change this to array for better password handling.
            return "MISMATCH";
        }
        if(user.getPassword().length < 8){
            return "WEAK";
        }
        if(usersDAO.isExistingEmail(user.getEmail())){
            return "EXISTS";
        }
        if(!EmailService.validateEmail(user.getEmail())){
            return "INVALID EMAIL";
        }
        return "SUCCESS"; // this serves as a way for UI to know if it is a success.
    }

    public void registerUser(Users user){
        String hashed = BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt(12)); // password hash using jBCrypt
        usersDAO.registerUser(user.getEmail(), hashed);
    }

    private String generatedOTP; // Temporary OTP storage during Sign up session.

    public String generateOTP(){
        int OTP = random.nextInt(100000);
        return String.valueOf(OTP);
    }

    public boolean verifyInputOTP(String inputOTP){ // Register user upon OTP verification
        if(inputOTP.equals(generatedOTP)){
            registerUser(Users.getCurrentUser());
            return true;
        }
        return false;
    }

    public void initiateEmailVerification(){
        generatedOTP = generateOTP();
        EmailService emailService = new EmailService();
        emailService.sendEmail(generatedOTP);
    }
}