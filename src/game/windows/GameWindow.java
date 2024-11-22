package game.windows;

import game.Constants;
import javax.swing.*;
import java.awt.*;

public class GameWindow {
    public JFrame window;

    public GameWindow(){
        window = new JFrame(Constants.GAMETITLE);
        window.setSize(Constants.GAMEWIDTH, Constants.GAMEHEIGHT);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIconImage(Constants.ICON);
        window.setVisible(true);
        adjustWindowSize();
    }
    private void adjustWindowSize(){
        Insets insets = window.getInsets();
        int adjustedWidth = Constants.GAMEWIDTH + insets.left + insets.right;
        int adjustedHeight = Constants.GAMEHEIGHT + insets.top + insets.bottom;
        window.setSize(adjustedWidth, adjustedHeight);
    }
}