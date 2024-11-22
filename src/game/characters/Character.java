package game.characters;

import game.Constants;
import game.Drawing;

import java.awt.*;

public abstract class Character implements Drawing {
    int health;
    int strength;
    String name;
    int x;
    int y;
    boolean facingRight;
    boolean canAttack = true;
    int attackTimeout;
    int movementSpeed;
    int jumpHeight;
    int jumpSpeed;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getAttackTimeout() {
        return attackTimeout;
    }

    public void setAttackTimeout(int attackTimeout) {
        this.attackTimeout = attackTimeout;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public int getJumpSpeed() {
        return jumpSpeed;
    }

    public void setJumpSpeed(int jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public Image getWeaponL() {
        return weaponL;
    }

    public void setWeaponL(Image weaponL) {
        this.weaponL = weaponL;
    }

    public Image getWeaponR() {
        return weaponR;
    }

    public void setWeaponR(Image weaponR) {
        this.weaponR = weaponR;
    }

    int attackRange;
    Image weaponL;
    Image weaponR;
    Color color = new Color(0, 0, 0);

    public void attack(Character enemy) {
        if (facingRight){
            if ((enemy.x - (this.x + Constants.CHARACTERSIZE) <= this.attackRange && enemy.x - (this.x + Constants.CHARACTERSIZE) >= -Constants.CHARACTERSIZE * 0.4)
                    && (Math.abs(enemy.y - this.y) <= Constants.CHARACTERSIZE / 2 || Math.abs(this.y - enemy.y) <= Constants.CHARACTERSIZE / 2 )){
                enemy.health -= this.strength;
            }
        }
        else{
            if ((this.x - (enemy.x + Constants.CHARACTERSIZE) <= this.attackRange && this.x - (enemy.x + Constants.CHARACTERSIZE) >= -Constants.CHARACTERSIZE * 0.4)
                    && (Math.abs(enemy.y - this.y) <= Constants.CHARACTERSIZE / 2 ||
                    Math.abs(this.y - enemy.y) <= Constants.CHARACTERSIZE / 2 )){
                enemy.health -= this.strength;
            }
        }
    }

    @Override
    public void drawCharacter(Graphics g) {
        g.setColor(this.color);
        g.fillRect(x, y, Constants.CHARACTERSIZE, Constants.CHARACTERSIZE);

        g.setColor(Color.BLACK);
        if (facingRight){
            g.fillRect(x + Constants.EYEXRIGHT, y + Constants.EYEYRIGHT, Constants.EYESIZE, Constants.EYESIZE);
        }
        else{
            g.fillRect(x + Constants.EYEXLEFT, y + Constants.EYEYLEFT, Constants.EYESIZE, Constants.EYESIZE);
        }

        if(this.canAttack){
            g.setColor(Constants.attackReadyNickColor);
        }
        String text = name + ": " + health;
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int textX = x + (Constants.CHARACTERSIZE - textWidth) / 2;
        int textY = y - textHeight;

        g.drawString(text, textX, textY);
    }

    public void attackTimeout() {
        Thread attackTimeout = new Thread(() -> {
            try {
                Thread.sleep(this.attackTimeout);
            } catch (InterruptedException error) {
                throw new RuntimeException(error);
            }

            this.canAttack = true;
        });

        attackTimeout.start();
    }
}
