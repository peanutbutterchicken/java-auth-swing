package auth.view;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ViewManager viewManager = new ViewManager("Auth Sample", 650, 450);

        viewManager.addView("Sign in", new Signin_view(viewManager));
        viewManager.start("Sign in");
    }
}