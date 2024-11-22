package game.characters;

import game.Constants;
import game.Drawing;
import game.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Archer extends Character implements Drawing {
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
        this.weaponL = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "BowL.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.weaponR = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "BowR.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.color = new Color(0,102,0);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);

        int weaponX = this.x + (facingRight ? 36 : -26);
        int weaponY = this.y + 6;

        g.drawImage(facingRight ? weaponR : weaponL, weaponX, weaponY, null);
    }
}
