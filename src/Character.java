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
    int attackRange;
    Color color = new Color(0, 0, 0);

    void attack(Character enemy) {
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

    void attackTimeout() {
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
