package auth.model;

public class Users {
    String email;
    char[] password;

    public Users(String email, char[] password){
        this.email = email;
        this.password = password;
    }

    private static Users currentUser;

    public static Users getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(Users user){
        currentUser = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
