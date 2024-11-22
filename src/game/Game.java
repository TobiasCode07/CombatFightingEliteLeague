package game;

import game.characters.*;
import game.characters.Character;
import game.windows.GameWindow;
import game.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {
    private game.characters.Character player1;
    private game.characters.Character player2;
    private Timer timer;
    private boolean isGameOver = false;
    private game.characters.Character winner;

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

    private game.characters.Character createPlayer(String name, Characters character, int startingX, int startingY, boolean facingRight) {
        return switch (character) {
            case Warrior -> new Warrior(name, startingX, startingY, facingRight);
            case Archer -> new Archer(name, startingX, startingY, facingRight);
            case Giant -> new Giant(name, startingX, startingY, facingRight);
            case Assassin -> new Assassin(name, startingX, startingY, facingRight);
        };
    }


    private void jump(game.characters.Character player) {
        Timer jumpTimer = new Timer(16, new ActionListener() {
            private boolean ascending = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ascending) {
                    if (player.getY() > (Constants.GAMEHEIGHT - Constants.CHARACTERSIZE) - player.getJumpHeight()) {
                        player.setY(player.getY() - player.getJumpSpeed());
                    } else {
                        ascending = false;
                    }
                } else {
                    if (player.getY() < Constants.GAMEHEIGHT - Constants.CHARACTERSIZE) {
                        player.setY(player.getY() + player.getJumpSpeed());
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
            String message = winner.getName() + " won!";
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
                player1.setFacingRight(false);
                if (player1.getX() - player1.getMovementSpeed() >= 0) {
                    player1.setX(player1.getX() - player1.getMovementSpeed());
                } else {
                    player1.setX(0);
                }
            }
            if (keys[KeyEvent.VK_D]) {
                player1.setFacingRight(true);
                if (player1.getX() + Constants.CHARACTERSIZE + player1.getMovementSpeed() <= Constants.GAMEWIDTH) {
                    player1.setX(player1.getX() + player1.getMovementSpeed());
                } else {
                    player1.setX(Constants.GAMEWIDTH - Constants.CHARACTERSIZE);
                }
            }

            if (keys[KeyEvent.VK_LEFT]) {
                player2.setFacingRight(false);
                if (player2.getX() - player2.getMovementSpeed() >= 0) {
                    player2.setX(player2.getX() - player2.getMovementSpeed());
                } else {
                    player2.setX(0);
                }
            }
            if (keys[KeyEvent.VK_RIGHT]) {
                player2.setFacingRight(true);
                if (player2.getX() + Constants.CHARACTERSIZE + player2.getMovementSpeed() <= Constants.GAMEWIDTH) {
                    player2.setX(player2.getX() + player2.getMovementSpeed());
                } else {
                    player2.setX(Constants.GAMEWIDTH - Constants.CHARACTERSIZE);
                }
            }
            if (keys[KeyEvent.VK_W]) {
                if (player1.getY() + Constants.CHARACTERSIZE == Constants.GAMEHEIGHT) {
                    jump(player1);
                }
            }
            if (keys[KeyEvent.VK_UP]) {
                if (player2.getY() + Constants.CHARACTERSIZE == Constants.GAMEHEIGHT) {
                    jump(player2);
                }
            }
            if (keys[KeyEvent.VK_SPACE]) {
                if (player1.isCanAttack()) {
                    player1.attack(player2);
                    player1.setCanAttack(false);
                    player1.attackTimeout();
                }
            }
            if (keys[KeyEvent.VK_NUMPAD0]) {
                if (player2.isCanAttack()) {
                    player2.attack(player1);
                    player2.setCanAttack(false);
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
        return player1.getHealth() <= 0 ? player2 : (player2.getHealth() <= 0 ? player1: null);
    }
}
