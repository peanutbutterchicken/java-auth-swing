package auth.view.themes;

import java.awt.Color;
import java.awt.Font;

public class DesignsAndFormat {

    public static final Color BLUE_BG = new Color(59,107,140,255);
    public static final Color MOD_WHITE_BG = new Color(248, 242, 238);
    

    public static Font mainFont(){
        Font font = new Font("Sansserif", Font.BOLD, 18);
        return font;
    }

    
    public static Color blueBackgroundColor(){
        return BLUE_BG;
    }
    public static Color whiteBackgroundColor(){
        return MOD_WHITE_BG;
    }
}
