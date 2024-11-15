import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MenuWindow extends JFrame {
    private JTextField player1NameField;
    private JTextField player2NameField;
    private JComboBox<String> player1CharacterBox;
    private JComboBox<String> player2CharacterBox;
    private JButton startButton;

    private String[] characters = Arrays.stream(Characters.values())
            .map(Enum::name)
            .toArray(String[]::new);

    private Characters[] charactersEnum = Characters.values();

    public MenuWindow(Game game) {
        setTitle("Game Setup");
        setSize(Constants.MENUWIDTH, Constants.MENUHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(Constants.ICON);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Player 1's name:"));
        player1NameField = new JTextField();
        panel.add(player1NameField);

        panel.add(new JLabel("Player 1's character:"));
        player1CharacterBox = new JComboBox<>(characters);
        panel.add(player1CharacterBox);

        panel.add(new JLabel("Player 2's name:"));
        player2NameField = new JTextField();
        panel.add(player2NameField);

        panel.add(new JLabel("Player 2's character:"));
        player2CharacterBox = new JComboBox<>(characters);
        panel.add(player2CharacterBox);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(game);
            }
        });

        add(panel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startGame(Game game) {
        String player1Name = player1NameField.getText();
        String player2Name = player2NameField.getText();
        Characters player1Character = charactersEnum[player1CharacterBox.getSelectedIndex()];
        Characters player2Character = charactersEnum[player2CharacterBox.getSelectedIndex()];

        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter both players names", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            if (player1Name.length() > 10 || player2Name.length() > 10) {
                JOptionPane.showMessageDialog(this, "Names must be up to 10 characters", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                dispose();
                game.startGame(player1Name, player2Name, player1Character, player2Character);
            }
        }
    }
}
