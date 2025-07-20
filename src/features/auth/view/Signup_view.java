package auth.view;
import auth.view.components.Buttons;
import auth.view.themes.DesignsAndFormat;
import auth.view.themes.LoadAndResizeImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import auth.DAO.UsersDAO;
import auth.controller.SignupController;
import auth.model.Users;

public class Signup_view extends JPanel{
        DesignsAndFormat designsAndFormat = new DesignsAndFormat();
        LoadAndResizeImage loadAndResizeImage = new LoadAndResizeImage();
        UsersDAO userDAO = new UsersDAO();
        SignupController signupController = new SignupController();

        private JTextField jtxtUserEmail;
        private JPasswordField jtxtPrefPassword, jtxtConfirmUserPassword;

        public Signup_view(ViewManager viewManager){
            initComponents(viewManager);
        }

        private void initComponents(ViewManager viewManager){

            JLabel lbTitle = new JLabel("SignUp");
            lbTitle.setFont(DesignsAndFormat.mainFont().deriveFont(42f));
            lbTitle.setPreferredSize(new Dimension(0, 120));
            lbTitle.setHorizontalAlignment(JLabel.LEFT);
            lbTitle.setVerticalAlignment(JLabel.BOTTOM);
            lbTitle.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

            JLabel lbUserEmail = new JLabel(new ImageIcon(getClass().getResource("/auth/view/resources/icons/email.png")));
            jtxtUserEmail = new JTextField();
            jtxtUserEmail.setMaximumSize(new Dimension(200, 40));
            jtxtUserEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
            jtxtUserEmail.setOpaque(false); 

            JPanel horizontalPanel1 = new JPanel();
            horizontalPanel1.setLayout(new BoxLayout(horizontalPanel1, BoxLayout.X_AXIS));
            horizontalPanel1.setBackground(DesignsAndFormat.whiteBackgroundColor());
            horizontalPanel1.add(lbUserEmail);
            horizontalPanel1.add(jtxtUserEmail);
            horizontalPanel1.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            
            JLabel lbPrefPassword = new JLabel(new ImageIcon(getClass().getResource("/auth/view/resources/icons/pwIcon.png")));
            jtxtPrefPassword = new JPasswordField();
            jtxtPrefPassword.setMaximumSize(new Dimension(200, 40));
            jtxtPrefPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
            jtxtPrefPassword.setOpaque(false); 
            
            JPanel horizontalPanel2 = new JPanel();
            horizontalPanel2.setLayout(new BoxLayout(horizontalPanel2, BoxLayout.X_AXIS));
            horizontalPanel2.setBackground(DesignsAndFormat.whiteBackgroundColor());
            horizontalPanel2.add(lbPrefPassword);
            horizontalPanel2.add(jtxtPrefPassword);
            horizontalPanel2.setAlignmentX(JPanel.CENTER_ALIGNMENT);


            JLabel lbConfirmPassword = new JLabel(new ImageIcon(getClass().getResource("/auth/view/resources/icons/checkCircle.png")));
            jtxtConfirmUserPassword = new JPasswordField();
            jtxtConfirmUserPassword.setMaximumSize(new Dimension(200, 40));
            jtxtConfirmUserPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
            jtxtConfirmUserPassword.setOpaque(false);

            JPanel horizontalPanel3 = new JPanel();
            horizontalPanel3.setLayout(new BoxLayout(horizontalPanel3, BoxLayout.X_AXIS));
            horizontalPanel3.setBackground(DesignsAndFormat.whiteBackgroundColor());
            horizontalPanel3.add(lbConfirmPassword);
            horizontalPanel3.add(jtxtConfirmUserPassword);
            horizontalPanel3.setAlignmentX(JPanel.CENTER_ALIGNMENT);

            JButton btnSignup = Buttons.standardBlueButton("SignUp");

            btnSignup.addActionListener(new ActionListener() {  

                @Override
                public void actionPerformed(ActionEvent evt){
                    // form validation
                    Users user = new Users(getEmail(), getPassword());
                    String result = signupController.userFieldValidation(user, getConfirmUserPassword());

                    switch(result){
                        case "EMPTY":
                        JOptionPane.showMessageDialog(null, "Empty fields", "Dialog Box", JOptionPane.INFORMATION_MESSAGE);
                        break;

                        case "MISMATCH":
                        JOptionPane.showMessageDialog(null, "Password mismatch", "Dialog Box", JOptionPane.ERROR_MESSAGE);
                        break;

                        case "WEAK":
                        JOptionPane.showMessageDialog(null, "Password length must be greater than 8 characters", "Dialog Box", JOptionPane.ERROR_MESSAGE);
                        break;

                        case "EXISTS":
                        JOptionPane.showMessageDialog(null, "Account already exist!", "Sign up", JOptionPane.INFORMATION_MESSAGE);
                        break;

                        case "INVALID EMAIL":
                        JOptionPane.showMessageDialog(null, "Invalid email address", "Sign up", JOptionPane.INFORMATION_MESSAGE);
                        break;

                        case "SUCCESS":
                        Users.setCurrentUser(user);
                        viewManager.addView("Confirm email", new ConfirmEmail(viewManager));
                        viewManager.show("Confirm email");
                        break;

                        //first table(user_accounts): save user to the db => user_accounts which accepts user's email and password. I forgot if the created_at row is auto generated by the db or the program need to send it. 
                        
                        // TO-DO: proceed to profile setup (view)
                        //second table(user_profiles): which then proceeds to save the user_profile to the db 
                        
                    }
                }
            });

            JPanel horizontalPanel5 = new JPanel();
            horizontalPanel5.setMaximumSize(new Dimension(200, 40));
            horizontalPanel5.setBackground(DesignsAndFormat.whiteBackgroundColor());
            horizontalPanel5.add(btnSignup);


            JPanel verticalPanel = new JPanel();
            verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
            verticalPanel.setBackground(DesignsAndFormat.whiteBackgroundColor());
            verticalPanel.add(horizontalPanel1);
            verticalPanel.add(horizontalPanel2);
            verticalPanel.add(horizontalPanel3);
            //verticalPanel.add(horizontalPanel4);
            verticalPanel.add(horizontalPanel5);

            JPanel panel1 = new JPanel();
            panel1.setLayout(new BorderLayout());
            panel1.setBackground(DesignsAndFormat.whiteBackgroundColor());
            panel1.add(lbTitle, BorderLayout.NORTH);
            panel1.add(verticalPanel, BorderLayout.CENTER);


            // image container
            ImageIcon signupImage = loadAndResizeImage.loadAndResize("/auth/view/resources/images/signup-image.png", 256, 256);
            JLabel signUpImageContainer = new JLabel(signupImage);
            signUpImageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
            signUpImageContainer.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

            JLabel lbAlreadyHaveAnAccount = new JLabel("Already have an account? ");
            JLabel lbAlreadyHaveAnAccountLink = new JLabel("SignIn");


            JPanel linkContainer = new JPanel();
            linkContainer.setLayout(new BoxLayout(linkContainer, BoxLayout.X_AXIS));
            linkContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
            linkContainer.setBackground(DesignsAndFormat.whiteBackgroundColor());
            linkContainer.add(lbAlreadyHaveAnAccount);
            linkContainer.add(lbAlreadyHaveAnAccountLink);


            JPanel verticalContainer = new JPanel();
            verticalContainer.setLayout(new BoxLayout(verticalContainer, BoxLayout.Y_AXIS));
            verticalContainer.setBackground(DesignsAndFormat.whiteBackgroundColor());
            verticalContainer.add(signUpImageContainer);
            verticalContainer.add(linkContainer);

            lbAlreadyHaveAnAccountLink.addMouseListener(new MouseAdapter() {
                @Override

                public void mouseClicked(MouseEvent evt){
                    viewManager.show("Sign in");
                }

                @Override
                public void mouseEntered(MouseEvent evt){
                    lbAlreadyHaveAnAccountLink.setForeground(DesignsAndFormat.BLUE_BG);
                }

                @Override
                public void mouseExited(MouseEvent evt){
                    lbAlreadyHaveAnAccountLink.setForeground(Color.BLACK);
                }
            });

            JPanel panel2 = new JPanel(new BorderLayout());
            panel2.setBackground(DesignsAndFormat.whiteBackgroundColor());
            panel2.add(verticalContainer, BorderLayout.CENTER);


            JPanel mainPanel = new JPanel(new GridLayout(0,2));
            mainPanel.add(panel1);
            mainPanel.add(panel2, BorderLayout.CENTER);

            setLayout(new BorderLayout());
            add(mainPanel);
        }

        public String getEmail(){
            return jtxtUserEmail.getText();
        }

        public char[] getPassword(){
            return jtxtPrefPassword.getPassword();
        }
        
        public char[] getConfirmUserPassword(){
            return jtxtConfirmUserPassword.getPassword();
        }

        public boolean isEmailEmpty(){
        return getEmail().isEmpty();
        }

        public boolean isPasswordEmpty(){
        return getPassword().length == 0;
        }
}
