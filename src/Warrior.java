import java.awt.*;

public class Warrior extends Character{
    static final public Color color = new Color(0, 0, 255);

    public Warrior(String name, int startingX, int startingY, boolean facingRight) {
        this.name = name;
        this.health = 100;
        this.strength = 10;
        this.x = startingX;
        this.y = startingY;
        this.facingRight = facingRight;
    }
}
