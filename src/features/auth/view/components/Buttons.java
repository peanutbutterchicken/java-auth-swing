package auth.view.components;

import java.awt.Color;

import javax.swing.JButton;

import auth.view.themes.DesignsAndFormat;

public class Buttons {

    public static final JButton standardBlueButton(String name){
        JButton btn = new JButton(name);
        btn.setBackground(DesignsAndFormat.blueBackgroundColor());
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusable(false);

        return btn;
    }
}
