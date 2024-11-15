import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {
    private Character player1;
    private Character player2;
    private Timer timer;
    private boolean isGameOver = false;
    private Character winner;

    private boolean[] keys = new boolean[256];

    public Game() {
        new MenuWindow(this);
    }

    public void startGame(String name1, String name2, Characters character1, Characters character2) {
        GameWindow gameWindow = new GameWindow();
        gameWindow.window.add(this);

        this.player1 = createPlayer(name1, character1, Constants.STARTINGX1, Constants.STARTINGY1, true);
        this.player2 = createPlayer(name2, character2, Constants.STARTINGX2, Constants.STARTINGY2, false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }
        });

        setFocusable(true);

        timer = new Timer(16, this); // Approximately 60 FPS
        timer.start();

        repaint();
    }

    private Character createPlayer(String name, Characters character, int startingX, int startingY, boolean facingRight) {
        return switch (character) {
            case Warrior -> new Warrior(name, startingX, startingY, facingRight);
            case Archer -> new Archer(name, startingX, startingY, facingRight);
            case Giant -> new Giant(name, startingX, startingY, facingRight);
            case Assassin -> new Assassin(name, startingX, startingY, facingRight);
        };
    }


    private void jump(Character player) {
        Timer jumpTimer = new Timer(16, new ActionListener() {
            private boolean ascending = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ascending) {
                    if (player.y > (Constants.GAMEHEIGHT - Constants.CHARACTERSIZE) - player.jumpHeight) {
                        player.y -= player.jumpSpeed;
                    } else {
                        ascending = false;
                    }
                } else {
                    if (player.y < Constants.GAMEHEIGHT - Constants.CHARACTERSIZE) {
                        player.y += player.jumpSpeed;
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
                repaint();
            }
        });

        jumpTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (player1 != null) {
            player1.drawCharacter(g);
        }
        if (player2 != null) {
            player2.drawCharacter(g);
        }
        if (isGameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman", Font.BOLD, 50));
            FontMetrics gameTextMetrics = g.getFontMetrics(g.getFont());
            int gameTextWidth = gameTextMetrics.stringWidth(Constants.gameOverText);
            int gameTextHeight = gameTextMetrics.getHeight();
            int gameTextX = (Constants.GAMEWIDTH / 2) - gameTextWidth / 2;
            int gameTextY = ((Constants.GAMEHEIGHT / 6) * 2) - gameTextHeight / 2;
            g.drawString(Constants.gameOverText, gameTextX, gameTextY);

            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            FontMetrics subTextMetrics = g.getFontMetrics(g.getFont());
            String message = winner.name + " won!";
            int subTextWidth = subTextMetrics.stringWidth(message);
            int subTextHeight = subTextMetrics.getHeight();
            int subTextX = (Constants.GAMEWIDTH / 2) - subTextWidth / 2;
            int subTextY = ((Constants.GAMEHEIGHT / 6) * 3) - subTextHeight / 2;
            g.drawString(message, subTextX, subTextY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            if (keys[KeyEvent.VK_A]) {
                player1.facingRight = false;
                if (player1.x - player1.movementSpeed >= 0) {
                    player1.x -= player1.movementSpeed;
                } else {
                    player1.x = 0;
                }
            }
            if (keys[KeyEvent.VK_D]) {
                player1.facingRight = true;
                if (player1.x + Constants.CHARACTERSIZE + player1.movementSpeed <= Constants.GAMEWIDTH) {
                    player1.x += player1.movementSpeed;
                } else {
                    player1.x = Constants.GAMEWIDTH - Constants.CHARACTERSIZE;
                }
            }

            if (keys[KeyEvent.VK_LEFT]) {
                player2.facingRight = false;
                if (player2.x - player2.movementSpeed >= 0) {
                    player2.x -= player2.movementSpeed;
                } else {
                    player2.x = 0;
                }
            }
            if (keys[KeyEvent.VK_RIGHT]) {
                player2.facingRight = true;
                if (player2.x + Constants.CHARACTERSIZE + player2.movementSpeed <= Constants.GAMEWIDTH) {
                    player2.x += player2.movementSpeed;
                } else {
                    player2.x = Constants.GAMEWIDTH - Constants.CHARACTERSIZE;
                }
            }
            if (keys[KeyEvent.VK_W]) {
                if (player1.y + Constants.CHARACTERSIZE == Constants.GAMEHEIGHT) {
                    jump(player1);
                }
            }
            if (keys[KeyEvent.VK_UP]) {
                if (player2.y + Constants.CHARACTERSIZE == Constants.GAMEHEIGHT) {
                    jump(player2);
                }
            }
            if (keys[KeyEvent.VK_SPACE]) {
                if (player1.canAttack) {
                    player1.attack(player2);
                    player1.canAttack = false;
                    player1.attackTimeout();
                }
            }
            if (keys[KeyEvent.VK_NUMPAD0]) {
                if (player2.canAttack) {
                    player2.attack(player1);
                    player2.canAttack = false;
                    player2.attackTimeout();
                }
            }

            repaint();

            if (isGameOver() != null) {
                isGameOver = true;
                winner = isGameOver();
            }
        }
    }

    Character isGameOver(){
        return player1.health <= 0 ? player2 : (player2.health <= 0 ? player1: null);
    }
}
