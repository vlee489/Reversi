public class Menu {

    public static void main(String[] args){
        Game game = new Game();
        //game.newGame();
        game.loadGame("save.json");
        game.play();
    }
}
