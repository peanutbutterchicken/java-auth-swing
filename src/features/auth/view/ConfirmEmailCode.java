package auth.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import auth.controller.EmailService;
import auth.controller.SignupController;
import auth.view.components.Buttons;
import auth.view.themes.DesignsAndFormat;
import auth.view.themes.LoadAndResizeImage;

public class ConfirmEmailCode extends JPanel {
    LoadAndResizeImage loadAndResizeImage = new LoadAndResizeImage();
    JTextField OTPField;

    EmailService emailService = new EmailService();
    SignupController signupController = new SignupController();

    public ConfirmEmailCode(ViewManager viewManager){
        initComponents(viewManager);
    }

    private void initComponents(ViewManager viewManager){
        ImageIcon confirmEmailCodeImage = loadAndResizeImage.loadAndResize("/resources/images/email-verification-code-removebg-preview.png", 240, 240);
        JLabel confirmEmailCodeImageContainer = new JLabel(confirmEmailCodeImage);
        confirmEmailCodeImageContainer.setAlignmentX(CENTER_ALIGNMENT);
        confirmEmailCodeImageContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));

        // image container
        JPanel gridPanel1 = new JPanel();
        gridPanel1.setLayout(new BorderLayout());
        gridPanel1.setBackground(DesignsAndFormat.whiteBackgroundColor());
        gridPanel1.add(confirmEmailCodeImageContainer);
        
        OTPField = new JTextField();
        OTPField.setMaximumSize(new Dimension(100, 29));
        OTPField.setBorder(null);

        signupController.initiateEmailVerification(); // generates and sends OTP in users email.
        JButton btnSubmitUserCode = Buttons.standardBlueButton("Submit");
        btnSubmitUserCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean result = signupController.verifyInputOTP(getInputOTP());
                if(!result){
                    JOptionPane.showMessageDialog(null, "Wrong code!", "Verify Email", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                viewManager.addView("Confirm email success", new ConfirmEmailSuccess(viewManager));
                viewManager.show("Confirm email success");
            }
        });
        
        JPanel jtxtCodeVerifyContainer = new JPanel();
        jtxtCodeVerifyContainer.setLayout(new BoxLayout(jtxtCodeVerifyContainer, BoxLayout.X_AXIS));
        jtxtCodeVerifyContainer.setBorder(BorderFactory.createEmptyBorder(0, 65, 0, 0));
        jtxtCodeVerifyContainer.add(OTPField);
        jtxtCodeVerifyContainer.add(btnSubmitUserCode);

        // text field container
        JPanel gridPanel2 = new JPanel();
        gridPanel2.setLayout(new BorderLayout());
        gridPanel2.add(jtxtCodeVerifyContainer);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2));
        mainPanel.setBackground(DesignsAndFormat.whiteBackgroundColor());
        mainPanel.add(gridPanel1, BorderLayout.CENTER);
        mainPanel.add(gridPanel2, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public String getInputOTP(){
        return OTPField.getText();
    }
}
