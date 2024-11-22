package game.characters;

import game.Constants;
import game.Drawing;
import game.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Assassin extends Character implements Drawing {
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
        this.weaponL = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "DaggerL.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.weaponR = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "DaggerR.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.color = new Color(51,51,51);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);

        int weaponX = this.x + (facingRight ? 42 : -32);
        int weaponY = this.y - 8;

        g.drawImage(facingRight ? weaponR : weaponL, weaponX, weaponY, null);
    }
}
