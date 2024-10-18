import java.awt.*;

public class Warrior extends Character implements Combat {

    public Warrior(String name, int startingX, int startingY, boolean facingRight) {
        this.name = name;
        this.health = 100;
        this.strength = 10;
        this.movementSpeed = 5;
        this.jumpHeight = 100;
        this.jumpSpeed = 5;
        this.attackRange = 30;
        this.attackTimeout = 1000;
        this.x = startingX;
        this.y = startingY;
        this.facingRight = facingRight;
        color = new Color(0, 0, 255);;
    }

    @Override
    public void drawCharacter(Graphics g) {
        super.drawCharacter(g);
    }

    @Override
    public void attack(Character enemy) {
        if (facingRight){
            if ((enemy.x - (this.x + Constants.CHARACTERSIZE) <= this.attackRange && enemy.x - (this.x + Constants.CHARACTERSIZE) >= 0)
                    && (Math.abs(enemy.y - this.y) <= Constants.CHARACTERSIZE / 2 || Math.abs(this.y - enemy.y) <= Constants.CHARACTERSIZE / 2 )){
                enemy.health -= this.strength;
            }
        }
        else{
            if ((this.x - (enemy.x + Constants.CHARACTERSIZE) <= this.attackRange && this.x - (enemy.x + Constants.CHARACTERSIZE) >= 0)
                    && (Math.abs(enemy.y - this.y) <= Constants.CHARACTERSIZE / 2 ||
                    Math.abs(this.y - enemy.y) <= Constants.CHARACTERSIZE / 2 )){
                enemy.health -= this.strength;
            }
        }
    }

    @Override
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
