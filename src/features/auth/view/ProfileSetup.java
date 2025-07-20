package auth.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import auth.view.components.Buttons;
import auth.view.themes.DesignsAndFormat;

public class ProfileSetup extends JPanel {
    JTextField lastnameField, firstnameField, middlenameField, contactNumberField, institutionField, yearField, courseField;

    public ProfileSetup(ViewManager viewManager) {
        initComponents(viewManager);
    }

    private void initComponents(ViewManager viewManager) {
        JLabel header = new JLabel("Edit Profile");
        header.setFont(DesignsAndFormat.mainFont().deriveFont(24f));
        header.setForeground(DesignsAndFormat.whiteBackgroundColor());
        header.setHorizontalAlignment((int) CENTER_ALIGNMENT);

        JButton btnCompleteProfile = Buttons.standardBlueButton("Finish");
        JPanel btnContainer = new JPanel();
        btnContainer.setBackground(new Color(59, 75, 140));
        btnContainer.add(btnCompleteProfile);

        JPanel panel1 = new JPanel(new BorderLayout()); // select profile picture section
        panel1.setPreferredSize(new Dimension(200, 0)); // width only
        panel1.setBackground(new Color(59, 75, 140));
        panel1.setOpaque(true);
        panel1.add(header, BorderLayout.NORTH);
        panel1.add(btnContainer, BorderLayout.SOUTH); 

        JPanel componentContainer1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(5, 20, 5, 50);
        gbc1.weightx = 1;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.anchor = GridBagConstraints.NORTHWEST;

        JLabel txtFname = new JLabel("First name: ");
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        componentContainer1.add(txtFname, gbc1);

        JTextField fieldFname = new JTextField();
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        componentContainer1.add(fieldFname, gbc1);

        JLabel txtLname = new JLabel("Last name: ");
        gbc1.gridx = 1;
        gbc1.gridy = 0;
        componentContainer1.add(txtLname, gbc1);

        JTextField fieldLname = new JTextField();
        gbc1.gridx = 1;
        gbc1.gridy = 1;
        componentContainer1.add(fieldLname, gbc1);

        JLabel txtMname = new JLabel("Middle name: ");
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        componentContainer1.add(txtMname, gbc1);

        JTextField fieldMname = new JTextField();
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        componentContainer1.add(fieldMname, gbc1);

        JPanel componentContainer2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(0, 20, 5, 20);
        gbc2.weightx = 1;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.anchor = GridBagConstraints.NORTHWEST;

        JLabel txtInstitution = new JLabel("Institution: ");
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        componentContainer2.add(txtInstitution, gbc2);

        JTextField fieldInstitution = new JTextField();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        componentContainer2.add(fieldInstitution, gbc2);

        JLabel txtContactNum = new JLabel("Contact #: ");
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        componentContainer2.add(txtContactNum, gbc2);

        JTextField fieldContactNum = new JTextField();
        gbc2.gridx = 1;
        gbc2.gridy = 1;
        componentContainer2.add(fieldContactNum, gbc2);

        GridBagConstraints spacer = new GridBagConstraints();
        spacer.gridx = 0;
        spacer.gridy = 99;
        spacer.weighty = 1;
        spacer.fill = GridBagConstraints.VERTICAL;
        componentContainer2.add(Box.createVerticalGlue(), spacer);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(componentContainer1);
        panel2.add(componentContainer2);   

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panel1, BorderLayout.WEST);
        mainPanel.add(panel2, BorderLayout.CENTER);
        
        setLayout(new BorderLayout());
        add(mainPanel);
    }

    public String getLastname() { return lastnameField.getText(); }
    public String getFirstname() { return firstnameField.getText(); }
    public String getMiddlename() { return middlenameField.getText(); }
    public String getContact() { return contactNumberField.getText(); }
    public String getInstitution() { return institutionField.getText(); }
    public String getYear() { return yearField.getText(); }
    public String getCourse() { return courseField.getText(); }
}
