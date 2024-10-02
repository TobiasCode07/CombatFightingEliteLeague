import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Window {
    public Window(){
        JFrame window = new JFrame(Constants.TITLE);
        window.setSize(Constants.WIDTH, Constants.HEIGHT);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.ICON))).getImage();
        window.setIconImage(icon);
        window.setVisible(true);
    }
}
