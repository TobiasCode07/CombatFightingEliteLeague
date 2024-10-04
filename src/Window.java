import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Window {
    public JFrame window;

    public Window(){
        window = new JFrame(Constants.TITLE);
        window.setSize(Constants.WIDTH, Constants.HEIGHT);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.ICON))).getImage();
        window.setIconImage(icon);
        window.setVisible(true);
        adjustWindowSize();
    }
    private void adjustWindowSize(){
        Insets insets = window.getInsets();
        int adjustedWidth = Constants.WIDTH + insets.left + insets.right;
        int adjustedHeight = Constants.HEIGHT + insets.top + insets.bottom;
        window.setSize(adjustedWidth, adjustedHeight);
    }
}
