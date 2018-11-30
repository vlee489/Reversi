import java.util.Scanner;

/**
 * Displays the menu and options available as well as the help options.
 *
 * @author Pui-Hin Vincent Lee
 * @version 1.0
 */

public class Menu {
    private Game game = new Game();

    /**
     * Display available menu options
     */
    private void options(){
        System.out.println("1: Play 2 Player New Game");
        System.out.println("2: Play 1 Player New Game");
        System.out.println("3: Load existing game and play");
        System.out.println("4: View Instructions");
        System.out.println("5: Exit Application");
    }

    /**
     * Displays instructions
     */
    private void instructions(){
        System.out.println("Loading existing file Game");
        System.out.println("---------------------------------");
        System.out.println("- chose option 2");
        System.out.println("- enter the name of the save file in the same directory of jar");
        System.out.println("- do NOT enter the name with \".json\"");
        System.out.println("=================================");
        System.out.println(" ");
        System.out.println("Playing Game");
        System.out.println("---------------------------------");
        System.out.println("when entering a move:");
        System.out.println("select the X axis (A-H)");
        System.out.println("select the Y axis (0-7)");
        System.out.println("enter it like so: A7");
        System.out.println("=================================");
        System.out.println(" ");
        System.out.println("saving mid-game");
        System.out.println("---------------------------------");
        System.out.println("when asked to enter a move type in \"SAVE\"");
        System.out.println("Follow instructions on screen");
        System.out.println("saves file in the same directory of jar");
        System.out.println("=================================");
        System.out.println(" ");
        System.out.println("exiting mid-game");
        System.out.println("---------------------------------");
        System.out.println("when asked to enter a move type in \"EXIT\"");
        System.out.println("=================================");
    }

    /**
     * Launch Menu
     */
    private void menu() {
        System.out.println("Please choose an option!");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        switch (option) {
            case "1":
                game.newGame();
                game.play();
                break;
            case "2":
                game.newGame();
                game.playAI();
                break;
            case "3":
                System.out.println("Choose the name of your save file");
                Scanner file = new Scanner(System.in);
                String fileLoad = file.nextLine();
                game.loadGame(fileLoad + ".json");
                game.playAuto();
                break;
            case "4":
                instructions();
                break;
            case "5":
                System.exit(0);
        }
    }

    /**
     * default main method
     * @param args main string
     */
    public static void main(String[] args){
        Menu menu = new Menu();
        while (true){
            menu.options();
            menu.menu();
        }
    }
}