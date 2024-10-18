import java.awt.*;

public abstract class Character implements Combat{
    public int health;
    public int strength;
    public String name;
    public int x;
    public int y;
    public boolean facingRight;
    public boolean canAttack = true;
    public int attackTimeout;
    public int movementSpeed;
    public int jumpHeight;
    public int jumpSpeed;
    public int attackRange;
    static public Color color = new Color(0, 0, 0);

    public void drawCharacter(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, Constants.CHARACTERSIZE, Constants.CHARACTERSIZE);

        g.setColor(Color.BLACK);
        if (facingRight){
            g.fillRect(x + Constants.EYEXRIGHT, y + Constants.EYEYRIGHT, Constants.EYESIZE, Constants.EYESIZE);
        }
        else{
            g.fillRect(x + Constants.EYEXLEFT, y + Constants.EYEYLEFT, Constants.EYESIZE, Constants.EYESIZE);
        }

        String text = name + ": " + health;
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int textX = x + (Constants.CHARACTERSIZE - textWidth) / 2;
        int textY = y - textHeight;

        g.drawString(text, textX, textY);
    }
}
