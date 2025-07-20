package auth.view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import auth.view.components.Buttons;
import auth.view.themes.LoadAndResizeImage;

public class ConfirmEmailSuccess extends JPanel{
    LoadAndResizeImage loadAndResizeImage = new LoadAndResizeImage();
    public ConfirmEmailSuccess(ViewManager viewManager){
        initComponents(viewManager);
    }

    private void initComponents(ViewManager viewManager){
        ImageIcon image = loadAndResizeImage.loadAndResize("/resources/images/email-verification-success.png", 280, 280);
        JLabel imageContainer = new JLabel(image);
        imageContainer.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        imageContainer.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnProceed = Buttons.standardBlueButton("Proceed >");
        JPanel buttonContainer = new JPanel();
        buttonContainer.add(btnProceed);

        JPanel verticalPanel1 = new JPanel();
        verticalPanel1.setLayout(new BoxLayout(verticalPanel1, BoxLayout.Y_AXIS));
        verticalPanel1.add(imageContainer);
        verticalPanel1.add(buttonContainer);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(verticalPanel1, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(mainPanel);
    }
}
