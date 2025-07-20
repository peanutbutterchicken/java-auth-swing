package auth.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import auth.view.components.Buttons;
import auth.view.themes.DesignsAndFormat;
import auth.view.themes.LoadAndResizeImage;

public class ConfirmEmail extends JPanel {
    
    LoadAndResizeImage loadAndResizeImage = new LoadAndResizeImage();
    DesignsAndFormat designsAndFormat = new DesignsAndFormat();
    ConfirmEmail(ViewManager viewManager){
        initComponents(viewManager);
    }

    private void initComponents(ViewManager viewManager){
        ImageIcon confirmEmailImage = loadAndResizeImage.loadAndResize("/resources/images/email-verification.png", 180, 180);
        JLabel lbConfirmEmailImageContainer = new JLabel(confirmEmailImage);

        JPanel horizontalPanel1 = new JPanel();
        horizontalPanel1.setLayout(new BoxLayout(horizontalPanel1, BoxLayout.X_AXIS));
        horizontalPanel1.setBackground(DesignsAndFormat.whiteBackgroundColor());
        horizontalPanel1.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        horizontalPanel1.setAlignmentX(CENTER_ALIGNMENT);
        horizontalPanel1.add(lbConfirmEmailImageContainer);


        JLabel lbConfirmationEmailTextHeader = new JLabel("Email Confirmation");
        lbConfirmationEmailTextHeader.setFont(DesignsAndFormat.mainFont().deriveFont(24f));
        lbConfirmationEmailTextHeader.setAlignmentX(CENTER_ALIGNMENT);

        JPanel horizontalPanel2 = new JPanel();
        horizontalPanel2.setLayout(new BoxLayout(horizontalPanel2, BoxLayout.X_AXIS));
        horizontalPanel2.setBackground(DesignsAndFormat.whiteBackgroundColor());
        horizontalPanel2.setAlignmentX(CENTER_ALIGNMENT);
        horizontalPanel2.add(lbConfirmationEmailTextHeader);


        JLabel lbConfirmationEmailText1 = new JLabel("We have sent your verification code to placeholder@gmail.com to confirm");
        lbConfirmationEmailText1.setFont(DesignsAndFormat.mainFont().deriveFont(14f));
        lbConfirmationEmailText1.setForeground(Color.GRAY);
        lbConfirmationEmailText1.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel lbConfirmationEmailText2 = new JLabel("the validity of your email address. After receiving the code");
        lbConfirmationEmailText2.setFont(DesignsAndFormat.mainFont().deriveFont(14f));
        lbConfirmationEmailText2.setForeground(Color.GRAY);
        lbConfirmationEmailText2.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbConfirmationEmailText3 = new JLabel("verify to complete your registration.");
        lbConfirmationEmailText3.setFont(DesignsAndFormat.mainFont().deriveFont(14f));
        lbConfirmationEmailText3.setForeground(Color.GRAY);
        lbConfirmationEmailText3.setAlignmentX(CENTER_ALIGNMENT);

        JPanel horizontalPanel3 = new JPanel();
        horizontalPanel3.setLayout(new BoxLayout(horizontalPanel3, BoxLayout.Y_AXIS));
        horizontalPanel3.setAlignmentX(CENTER_ALIGNMENT);
        horizontalPanel3.setBackground(DesignsAndFormat.whiteBackgroundColor());
        horizontalPanel3.add(lbConfirmationEmailText1);
        horizontalPanel3.add(lbConfirmationEmailText2);
        horizontalPanel3.add(lbConfirmationEmailText3);
        
        JButton btnNext = Buttons.standardBlueButton("Next >");
        btnNext.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt){
                viewManager.addView("Confirm email code", new ConfirmEmailCode(viewManager));
                viewManager.show("Confirm email code");
            }
        });

        JPanel horizontalPanel4 = new JPanel();
        horizontalPanel4.setLayout(new BoxLayout(horizontalPanel4, BoxLayout.X_AXIS));
        horizontalPanel4.setAlignmentX(CENTER_ALIGNMENT);
        horizontalPanel4.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        horizontalPanel4.setBackground(DesignsAndFormat.whiteBackgroundColor());
        horizontalPanel4.add(btnNext);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(DesignsAndFormat.whiteBackgroundColor());
        mainPanel.add(horizontalPanel1);
        mainPanel.add(horizontalPanel2);
        mainPanel.add(horizontalPanel3);
        mainPanel.add(horizontalPanel4);

        setLayout(new BorderLayout());
        add(mainPanel);
    }
}
