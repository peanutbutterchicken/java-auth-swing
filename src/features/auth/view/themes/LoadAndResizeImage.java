package auth.view.themes;

import java.awt.Image;
import javax.swing.ImageIcon;

public class LoadAndResizeImage {
    
    public ImageIcon loadAndResize(String path, int width, int height){
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
