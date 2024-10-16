import java.awt.*;

public abstract class Character implements Combat{
    public int health;
    public int strength;
    public String name;
    public int x;
    public int y;
    public boolean facingRight;
    public int movementSpeed;
    public int jumpHeight;
    public int jumpSpeed;
    public int attackRange;
    static public Color color = new Color(0, 0, 0);

    public void drawCharacter(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, Constants.CHARACTERSIZE, Constants.CHARACTERSIZE);
    }
}
