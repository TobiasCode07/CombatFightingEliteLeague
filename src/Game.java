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
        Window window = new Window();
        window.window.add(this);
        Character player1 = new Archer("Leon", Constants.STARTINGX1, Constants.STARTINGY1, true);
        Character player2 = new Assassin("Tobiasz", Constants.STARTINGX2, Constants.STARTINGY2, false);
        startGame(player1, player2);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMousePressed(e);
            }
        });

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
    }

    private void handleMousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("x: " + x + " y: " + y);
    }

    public void startGame(Character player1, Character player2) {
        this.player1 = player1;
        this.player2 = player2;
        repaint();
    }

    private void jump(Character player) {
        Timer jumpTimer = new Timer(16, new ActionListener() {
            private boolean ascending = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ascending) {
                    if (player.y > (Constants.HEIGHT - Constants.CHARACTERSIZE) - player.jumpHeight) {
                        player.y -= player.jumpSpeed;
                    } else {
                        ascending = false;
                    }
                } else {
                    if (player.y < Constants.HEIGHT - Constants.CHARACTERSIZE) {
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
            int gameTextX = (Constants.WIDTH / 2) - gameTextWidth / 2;
            int gameTextY = ((Constants.HEIGHT / 6) * 2) - gameTextHeight / 2;
            g.drawString(Constants.gameOverText, gameTextX, gameTextY);

            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            FontMetrics subTextMetrics = g.getFontMetrics(g.getFont());
            String message = winner.name + " won!";
            int subTextWidth = subTextMetrics.stringWidth(message);
            int subTextHeight = subTextMetrics.getHeight();
            int subTextX = (Constants.WIDTH / 2) - subTextWidth / 2;
            int subTextY = ((Constants.HEIGHT / 6) * 3) - subTextHeight / 2;
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
                if (player1.x + Constants.CHARACTERSIZE + player1.movementSpeed <= Constants.WIDTH) {
                    player1.x += player1.movementSpeed;
                } else {
                    player1.x = Constants.WIDTH - Constants.CHARACTERSIZE;
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
                if (player2.x + Constants.CHARACTERSIZE + player2.movementSpeed <= Constants.WIDTH) {
                    player2.x += player2.movementSpeed;
                } else {
                    player2.x = Constants.WIDTH - Constants.CHARACTERSIZE;
                }
            }
            if (keys[KeyEvent.VK_W]) {
                if (player1.y + Constants.CHARACTERSIZE == Constants.HEIGHT) {
                    jump(player1);
                }
            }
            if (keys[KeyEvent.VK_UP]) {
                if (player2.y + Constants.CHARACTERSIZE == Constants.HEIGHT) {
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
