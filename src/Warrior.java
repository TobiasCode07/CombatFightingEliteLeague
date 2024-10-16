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
            if (enemy.x - this.x <= this.attackRange && (enemy.y - this.y <= Constants.CHARACTERSIZE / 2 ||
                    this.y - enemy.y <= Constants.CHARACTERSIZE / 2 )){
                enemy.health -= this.strength;
            }
        }
        else{
            if (this.x - enemy.x <= this.attackRange && (enemy.y - this.y <= Constants.CHARACTERSIZE / 2 ||
                    this.y - enemy.y <= Constants.CHARACTERSIZE / 2 )){
                enemy.health -= this.strength;
            }
        }

        System.out.println(enemy.name + "'s health: " + enemy.health);
    }

    @Override
    public void block() {

    }
}
