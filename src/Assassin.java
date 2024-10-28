import java.awt.*;

public class Assassin extends Character{
    public Assassin(String name, int startingX, int startingY, boolean facingRight) {
        this.name = name;
        this.health = 70;
        this.strength = 12;
        this.movementSpeed = 7;
        this.jumpHeight = 120;
        this.jumpSpeed = 6;
        this.attackRange = 40;
        this.attackTimeout = 650;
        this.x = startingX;
        this.y = startingY;
        this.facingRight = facingRight;
        this.color = new Color(51,51,51);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);
    }
}
