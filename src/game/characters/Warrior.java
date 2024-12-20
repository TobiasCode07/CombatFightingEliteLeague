package game.characters;

import game.Constants;
import game.Drawing;
import game.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Warrior extends Character implements Drawing {

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
        this.weaponL = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "SwordL.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.weaponR = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "SwordR.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.color = new Color(0, 0, 255);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);

        int weaponX = this.x + (facingRight ? 42 : -32);
        int weaponY = this.y - 8;

        g.drawImage(facingRight ? weaponR : weaponL, weaponX, weaponY, null);
    }
}
