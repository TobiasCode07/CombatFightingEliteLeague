import java.awt.*;

public class Warrior extends Character{

    public Warrior(String name, int startingX, int startingY, boolean facingRight) {
        this.name = name;
        this.health = 100;
        this.strength = 10;
        this.movementSpeed = 5;
        this.jumpHeight = 100;
        this.jumpSpeed = 5;
        this.attackRange = 40;
        this.attackTimeout = 1000;
        this.x = startingX;
        this.y = startingY;
        this.facingRight = facingRight;
        this.color = new Color(0, 0, 255);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);
    }
}
