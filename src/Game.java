import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.google.common.base.Charsets;
import com.google.gson.*;
import com.google.common.io.*;

/**
 * This class takes in user inputs and displays the score as well
 * as creating saves of the board class and loading in saves into the board class
 *
 * @author Pui-Hin Vincent Lee
 * @version 1.0
 */

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
            File input = new File(file); //loads file
            String json = Files.toString(input, Charsets.UTF_8); //in file into one long sting
            Gson gson = new Gson();
            board = gson.fromJson(json, Board.class); //loads in the board class from the json provided
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
     *
     * @param file The name of the file
     */
    private void save(String file){
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
    private void displayBoard(){
        int[][] arrayTemp = board.getGrid();//gets array
        String linePrint;
        System.out.println("   A  B  C  D  E  F  G  H");
        //print array out
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
     * Displays the score of each player
     */
    private void displayScore(){
        System.out.println("Player 1 (#) score:  " + board.score(1));
        System.out.println("Player 2 (0) score:  " + board.score(2));
    }

    /**
     * Runs the CMD version of the game
     */
    public void play(){
        //checks if the board exists
        if (board == null){
            System.out.println("No board object made!");
            System.out.println("You must either use newGame() or loadGame(file) first!");
            System.exit(3);
        }
        while (board.isGameActive()){
            System.out.println("=======================================");
            displayScore();
            displayBoard();
            // takes in user's input
            Scanner ss = new Scanner(System.in);
            System.out.println("May player " + board.getTurn() + " enter their move (E.G A7): ");
            String move = ss.nextLine();
            // if save or exit command is given, we we runs the appropriate functions
            if (move.equals("SAVE") || move.equals("save")){
                Scanner s = new Scanner(System.in);
                System.out.println("Enter the name of the save game");
                String file = s.nextLine();
                file = (file + ".json");
                save(file);
            }else if (move.equals("EXIT") || move.equals("exit")){
                System.out.println("Exiting Game");
                break;
            }
            else{
                boolean valid = board.runTurn(move);//runs move
                while(!valid){//if the moves comes back as invalid it askes for the another input
                    Scanner s = new Scanner(System.in);
                    System.out.println("Player " + board.getTurn() + " Invalid Move, please enter valid move:");
                    move = s.nextLine();
                    valid = board.runTurn(move);
                }
            }
        }
        if (!board.isGameActive()){ //when game ends
            System.out.println("=======================================");
            displayBoard();
            System.out.println("Game is over!");
            System.out.println("Final Score is:");
            displayScore();
        }
    }

    /**
     * Runs a game against the AI
     */
    public void playAI(){
        board.setAI(true);
        if (board == null){
            System.out.println("No board object made!");
            System.out.println("You must either use newGame() or loadGame(file) first!");
            System.exit(3);
        }
        while (board.isGameActive()){
            System.out.println("=======================================");
            displayScore();
            displayBoard();
            boolean validmove;
            //if player 2 AKA AI is playing
            if (board.getTurn() == 2){
                System.out.println("AI (player 2) making move!");
                validmove = false;
                while (!validmove){
                    ArrayList<String> AIMoves = board.validMoves(2); //get the list of move the AI can make
                    Random random = new Random();// makes a random object
                    int pick = random.nextInt(AIMoves.size()); //picks an item from list
                    String move = AIMoves.get(pick); //actually retreaves
                    //The following gives the board.runMove the correct format
                    String[] moveSplit = move.split("");
                    String moveToEnter = (moveSplit[1] + moveSplit[0]);
                    validmove = board.runTurn(moveToEnter);
                }
                //when it's player 1's tune
            }else if (board.getTurn() == 1){
                //take in user input
                Scanner ss = new Scanner(System.in);
                System.out.println("May player " + board.getTurn() + " enter their move (E.G A7): ");
                String move = ss.nextLine();
                // if save or exit command is given, we we runs the appropriate functions
                if (move.equals("SAVE") || move.equals("save")){
                    Scanner s = new Scanner(System.in);
                    System.out.println("Enter the name of the save game");
                    String file = s.nextLine();
                    file = (file + ".json");
                    save(file);
                }else if (move.equals("EXIT") || move.equals("exit")){
                    System.out.println("Exiting Game");
                    break;
                }
                else{
                    boolean valid;
                    valid = board.runTurn(move);//runs move
                    while(!valid){//if the moves comes back as invalid it askes for the another input
                        Scanner s = new Scanner(System.in);
                        System.out.println("Player " + board.getTurn() + "Invalid Move, please enter valid move:");
                        move = s.nextLine();
                        valid = board.runTurn(move);
                    }
                }
            }
        }
        if (!board.isGameActive()){
            System.out.println("=======================================");
            displayBoard();
            System.out.println("Game is over!");
            System.out.println("Final Score is:");
            displayScore();
        }
    }

    /**
     * Determines if the game is against a Human or AI and load thr right play function
     */
    public void playAuto(){
        if (board.isAI() == true){
            playAI();
        }else if (board.isAI() == false){
            play();
        }
    }

}
