import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {
    private Character player1;
    private Character player2;
    private Timer timer;

    private boolean[] keys = new boolean[256];

    public Game() {
        Window window = new Window();
        window.window.add(this);
        Warrior player1 = new Warrior("Tobiasz", Constants.STARTINGX1, Constants.STARTINGY1, true);
        Warrior player2 = new Warrior("Leon", Constants.STARTINGX2, Constants.STARTINGY2, false);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (player1 != null) {
            player1.drawCharacter(g);
        }
        if (player2 != null) {
            player2.drawCharacter(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (keys[KeyEvent.VK_A]) {
            if (player1.x - player1.movementSpeed >= 0) {
                player1.x -= player1.movementSpeed;
            } else {
                player1.x = 0;
            }
        }
        if (keys[KeyEvent.VK_D]) {
            if (player1.x + Constants.CHARACTERSIZE + player1.movementSpeed <= Constants.WIDTH) {
                player1.x += player1.movementSpeed;
            } else {
                player1.x = Constants.WIDTH - Constants.CHARACTERSIZE;
            }
        }

        if (keys[KeyEvent.VK_LEFT]) {
            if (player2.x - player2.movementSpeed >= 0) {
                player2.x -= player2.movementSpeed;
            } else {
                player2.x = 0;
            }
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            if (player2.x + Constants.CHARACTERSIZE + player2.movementSpeed <= Constants.WIDTH) {
                player2.x += player2.movementSpeed;
            } else {
                player2.x = Constants.WIDTH - Constants.CHARACTERSIZE;
            }
        }

        repaint();
    }
}
