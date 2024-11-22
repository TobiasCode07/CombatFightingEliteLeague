package game.characters;

import game.Constants;
import game.Drawing;
import game.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Giant extends Character implements Drawing {
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
        this.weaponL = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "MaceL.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.weaponR = (new ImageIcon(Objects.requireNonNull(Main.class.getResource(Constants.WEAPONSPATH + "MaceR.png"))).getImage()).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        this.color = new Color(102,51,0);
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);

        int weaponX = this.x + (facingRight ? 42 : -32);
        int weaponY = this.y - 4;

        g.drawImage(facingRight ? weaponR : weaponL, weaponX, weaponY, null);
    }
}
