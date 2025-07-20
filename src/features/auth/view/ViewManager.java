package auth.view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ViewManager {
    private final JFrame frame;
    private final JPanel container;
    private final CardLayout cardLayout;
    private final HashMap<String, JPanel> views;

    ViewManager(String title, int width, int height){
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        frame.setContentPane(container);

        views = new HashMap<>();
    }

    public void addView(String name, JPanel view){
        views.put(name, view);
        container.add(view, name);
    }

    public void show(String name){
        if(!views.containsKey(name)){
            System.err.println("View not found: " + name);
            return;
        }
        cardLayout.show(container, name);
    }

    public void start(String initialViewName){
        show(initialViewName);
        frame.setVisible(true);
    }
}
