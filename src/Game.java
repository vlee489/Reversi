import java.util.Scanner;

public class Game {
    private Board board = new Board();

    /**
     * Displays the game board
     */
    public void displayBoard(){
        int[][] arrayTemp = board.getGrid();
        String linePrint;
        System.out.println("   0  1  2  3  4  5  6  7");
        System.out.println("   A  B  C  D  E  F  G  H");
        for (int i = 0; i < arrayTemp.length; i++){
            linePrint = i + "  ";
            for (int z = 0; z < arrayTemp[i].length; z++){
                if (arrayTemp[i][z] == 0){
                    linePrint = (linePrint + "▢  ");
                }else if (arrayTemp[i][z] == 1){
                    linePrint = (linePrint + "#  ");
                }else if (arrayTemp[i][z] == 2) {
                    linePrint = (linePrint + "0  ");
                }
            }
            System.out.println(linePrint);
        }
    }

    public void checkMoves(){
        System.out.println("Player 1 valid moves: " + board.validMoves(1));
        System.out.println("Player 2 valid moves: " + board.validMoves(2));
    }

    /**
     * Displays the score of each player
     */
    public void displayScore(){
        System.out.println("Player 1 (#) score:  " + board.player1.getScore());
        System.out.println("Player 2 (0) score:  " + board.player2.getScore());
    }

    public void play(){
        while (board.isGameAcive()){
            displayScore();
            displayBoard();
            Scanner ss = new Scanner(System.in);
            System.out.println("May player " + board.getTurn() + " enter their move: ");
            String move = ss.nextLine();
            boolean valid;
            valid = board.runTurn(move);
            while(!valid){
                Scanner s = new Scanner(System.in);
                System.out.println("Player " + board.getTurn() + "Invalid Move, please enter valid move:");
                move = s.nextLine();
                valid = board.runTurn(move);
            }
        }
    }
}
