import java.awt.*;

public class Giant extends Character{
    public Giant(String name, int startingX, int startingY, boolean facingRight) {
        this.name = name;
        this.health = 120;
        this.strength = 10;
        this.movementSpeed = 4;
        this.jumpHeight = 80;
        this.jumpSpeed = 4;
        this.attackRange = 40;
        this.attackTimeout = 1300;
        this.x = startingX;
        this.y = startingY;
        this.facingRight = facingRight;
        this.color = new Color(102,51,0);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);
    }
}
