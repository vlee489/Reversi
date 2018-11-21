import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.common.base.Charsets;
import com.google.gson.*;
import com.google.common.io.*;

public class Game {
    private Board board;

    /**
     * used to create a brand new game
     */
    public void newGame(){
         board = new Board();
    }

    /**
     * Used to load in an existing game
     * @param file the game file
     */
    public void loadGame(String file){
        try{
            File input = new File(file);
            String json = Files.toString(input, Charsets.UTF_8);
            Gson gson = new Gson();
            board = gson.fromJson(json, Board.class);
            System.out.println("Load Successful");
            System.out.println("Beginning Game");
        }
        catch (IOException e){
            System.out.println("Unable to load game!");
            System.exit(1);
        }
    }

    /**
     * This saves the game to "save.json", to where the Java binary is.
     */
    public void save(String file){
        Gson gson = new Gson();
        String json = gson.toJson(board);
        try{
            File save = new File(file);
            //Used to clear file
            PrintWriter writer = new PrintWriter(save);
            writer.print("");
            writer.close();
            //Write game Save
            Files.write(json, save, Charsets.UTF_8);
            System.out.println("Game Saved!");
            System.out.println("Quiting Program");
            System.exit(0);
        } catch (IOException e){
            System.out.println("Unable to save game!");
        }
    }

    /**
     * Displays the game board
     */
    public void displayBoard(){
        int[][] arrayTemp = board.getGrid();
        String linePrint;
        //System.out.println("   0  1  2  3  4  5  6  7");
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

    /**
     * Show moves available to the players
     */
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

    /**
     * Runs the CMD version of the game
     */
    public void play(){
        if (board == null){
            System.out.println("No board object made!");
            System.out.println("You must either use newGame() or loadGame(file) first!");
            System.exit(3);
        }
        while (board.isGameAcive()){
            displayScore();
            displayBoard();
            checkMoves();
            Scanner ss = new Scanner(System.in);
            System.out.println("May player " + board.getTurn() + " enter their move: ");
            String move = ss.nextLine();
            if (move.equals("SAVE")){
                Scanner s = new Scanner(System.in);
                System.out.println("Enter the name of the save game");
                String file = s.nextLine();
                file = (file + ".json");
                save(file);
            }else{
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
        if (!board.isGameAcive()){
            displayBoard();
            System.out.println("Game is over!");
            System.out.println("Final Score is:");
            displayScore();
        }
    }
}
