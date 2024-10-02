public class Main{
    public static void main(String[] args) {
        Window window = new Window();
        Game game = new Game();
        Warrior player1 = new Warrior("Tobiasz", Constants.STARTINGX1, Constants.STARTINGY1, true);
        Warrior player2 = new Warrior("Leon", Constants.STARTINGX2, Constants.STARTINGY2, false);
        game.startGame(player1, player2);
    }
}