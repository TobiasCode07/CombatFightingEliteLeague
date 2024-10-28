import java.awt.*;

public class Archer extends Character{
    public Archer(String name, int startingX, int startingY, boolean facingRight) {
        this.name = name;
        this.health = 50;
        this.strength = 10;
        this.movementSpeed = 8;
        this.jumpHeight = 90;
        this.jumpSpeed = 5;
        this.attackRange = 80;
        this.attackTimeout = 800;
        this.x = startingX;
        this.y = startingY;
        this.facingRight = facingRight;
        this.color = new Color(0,102,0);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);
    }
}
