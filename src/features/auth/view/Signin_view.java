package auth.view;
import auth.view.components.Buttons;
import auth.view.themes.DesignsAndFormat;
import auth.view.themes.LoadAndResizeImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import auth.DAO.UsersDAO;
import auth.controller.SigninController;
import auth.model.Users;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
public class Signin_view extends JPanel{
    DesignsAndFormat designsAndFormat = new DesignsAndFormat();
    LoadAndResizeImage loadAndResizeImage = new LoadAndResizeImage();
    UsersDAO userDAO = new UsersDAO();
    SigninController signinController = new SigninController();

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Signin_view(ViewManager viewManager){
        initComponents(viewManager);
    }

    private void initComponents(ViewManager viewManager){

        //image container
        ImageIcon signinImageIcon = loadAndResizeImage.loadAndResize("/auth/view/resources/images/signin-image.png", 256, 256);
        JLabel signinImageContainer = new JLabel(signinImageIcon);
        signinImageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        signinImageContainer.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));  

        JLabel lbCreateAccount = new JLabel("Don't have an account? ");
        lbCreateAccount.setFont(DesignsAndFormat.mainFont().deriveFont(12f));
        lbCreateAccount.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbCreateAccountLink = new JLabel("Sign up");
        lbCreateAccountLink.setFont(DesignsAndFormat.mainFont().deriveFont(12f));
        lbCreateAccountLink.setForeground(Color.BLACK);
        lbCreateAccountLink.addMouseListener(new MouseAdapter(){ // anonymous inner class, more efficient for methods that only performed by one component. In place of creating a class.

            @Override
            public void mouseClicked(MouseEvent evt){
                viewManager.addView("Sign up", new Signup_view(viewManager)); 
                viewManager.show("Sign up");
            }

            @Override
            public void mouseEntered(MouseEvent evt){
                lbCreateAccountLink.setForeground(DesignsAndFormat.BLUE_BG);
            }

            @Override
            public void mouseExited(MouseEvent evt){
                lbCreateAccountLink.setForeground(Color.BLACK);
            }

        });

        JPanel linkContainer = new JPanel();
        linkContainer.setLayout(new BoxLayout(linkContainer, BoxLayout.X_AXIS));
        linkContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        linkContainer.add(lbCreateAccount);
        linkContainer.add(lbCreateAccountLink);

        JPanel verticalContainer1 = new JPanel();
        verticalContainer1.setLayout(new BoxLayout(verticalContainer1, BoxLayout.Y_AXIS));
        verticalContainer1.add(signinImageContainer);
        verticalContainer1.add(linkContainer);

        JPanel panel1 = new JPanel(new BorderLayout());    
        panel1.add(verticalContainer1, BorderLayout.CENTER);

        //signin components
        JLabel formTitle = new JLabel("Signin");
        formTitle.setFont(DesignsAndFormat.mainFont().deriveFont(42f));
        formTitle.setPreferredSize(new Dimension(0, 150));
        formTitle.setHorizontalAlignment(JLabel.LEFT);
        formTitle.setVerticalAlignment(JLabel.BOTTOM);
        formTitle.setBorder(BorderFactory.createEmptyBorder(0, 50, 35, 0));
        

        JLabel lbUsername = new JLabel(new ImageIcon(getClass().getResource("/auth/view/resources/icons/userIcon.png")));
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(200, 40));
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        usernameField.setOpaque(false); 
        
        JPanel horizontalContainer1 = new JPanel();
        horizontalContainer1.setLayout(new BoxLayout(horizontalContainer1, BoxLayout.X_AXIS));
        horizontalContainer1.add(lbUsername);
        horizontalContainer1.add(usernameField);


        JLabel lbPassword = new JLabel(new ImageIcon(getClass().getResource("/auth/view/resources/icons/pwIcon.png")));
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(200, 40));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY)); // bottom line only
        passwordField.setOpaque(false); 

        JPanel horizontalContainer2 = new JPanel();
        horizontalContainer2.setLayout(new BoxLayout(horizontalContainer2, BoxLayout.X_AXIS));
        horizontalContainer2.add(lbPassword);
        horizontalContainer2.add(passwordField);

        JButton btnSignin = Buttons.standardBlueButton("SignIn");
        btnSignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt){

                String email = getEmail();
                char[] password = getPassword();
                Users user = new Users(email, password);  

                String result = signinController.userFieldValidation(user);
                switch(result){
                    case "EMPTY":
                    JOptionPane.showMessageDialog(null, "Email or password cannot be empty.", "Sign in", JOptionPane.WARNING_MESSAGE);
                    break;

                    case "INVALID EMAIL":
                    JOptionPane.showMessageDialog(null, "Invalid email", "Sign in", JOptionPane.WARNING_MESSAGE);
                    break;

                    case "SUCCESS":
                    Arrays.fill(user.getPassword(), '\0'); // clears password from memory for memory hygene.
                    System.out.println("Sign in successful");
                    break;

                    default:
                    JOptionPane.showMessageDialog(null, result, "Sign in", JOptionPane.WARNING_MESSAGE);
                    break;
                }
                //TO-DO: Open next screen. 
            }
        });

        JPanel horizontalContainer3 = new JPanel();
        horizontalContainer3.add(btnSignin);
        horizontalContainer3.setMaximumSize(new Dimension(200, 40));

        JPanel verticalContainer2 = new JPanel();
        verticalContainer2.setLayout(new BoxLayout(verticalContainer2, BoxLayout.Y_AXIS));
        verticalContainer2.add(horizontalContainer1);
        verticalContainer2.add(horizontalContainer2);
        verticalContainer2.add(horizontalContainer3);

        //signin components container
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        panel2.add(formTitle, BorderLayout.NORTH);
        panel2.add(verticalContainer2, BorderLayout.CENTER);

        //main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2));
        mainPanel.add(panel1, BorderLayout.CENTER); // panel1 contains image
        mainPanel.add(panel2); // panel2 contains set of multiple panels with different layouts and contains multiple components

        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public String getEmail(){
        return usernameField.getText(); // change this to email because it's confusing.
    }

    public char[] getPassword(){
        return passwordField.getPassword();
    }

}