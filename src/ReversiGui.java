
/**
 * ReversiGui: 
 *
 * @author Iain Martin , Vincent Lee
 * @version 2.0
 *
 * Notes to use ReversiGui
 *  ReversiGuu is intended as a replacement for a Menu class for Reversi.
 *  Comments that start with ReversiGUI mark where you might 
 *  add your own code. Please do not attempt to use this GUI until
 *  you have already met the minimum requirements of the project.
 *
 * Notes:
 *  Event handlers have been set up for Menu Options
 *  NewGame, LoadGame and Save Game.
 *
 *  An Event handler has also been set up for a Mouse Click on
 *  the grid which calls clickSquare(row, col).
 *
 *  To add functionality to this GUI add you code to these functions
 *  which are at the end of this file. 
 *
 *  Potential additions: FileChoosers could be implemented and the grid characters
 *  could be replaced with graphics by loading gifs or jpgs into the grid which is
 *  created from JButtons.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

import com.google.common.base.Charsets;
import com.google.gson.*;
import com.google.common.io.*;

public class ReversiGui implements ActionListener
{
    private Board board; //Makes the Board object for loading in of board
    // Default filename to use for saving and loading files
    // Possible improvement: replace with a FileChooser
    private final static String DEFAULT_FILENAME = "Reversigui.txt";
    private int GRID_SIZE = 8;
    private JButton [] buttonArray;

    public JMenuBar createMenu()
    {
        JMenuBar menuBar  = new JMenuBar();;
        JMenu menu = new JMenu("Reversi Menu");
        JMenuItem menuItem;

        menuBar.add(menu);

        // A group of JMenuItems. You can create other menu items here if desired
        menuItem = new JMenuItem("New Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Save Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //a submenu
        menu.addSeparator();
        return menuBar;
    }

    public Container createContentPane()
    {
        int numButtons = GRID_SIZE * GRID_SIZE;
        JPanel grid = new JPanel(new GridLayout(GRID_SIZE,GRID_SIZE));
        buttonArray = new JButton[numButtons];

        for (int i=0; i<numButtons; i++)
        {
            buttonArray[i] = new JButton(" ");

            // This label is used to identify which button was clicked in the action listener
            buttonArray[i].setActionCommand("" + i); // String "0", "1" etc.
            buttonArray[i].addActionListener(this);
            grid.add(buttonArray[i]);
        }
        return grid;
    }

    /**
     * This method handles events from the Menu and the board.
     *
     */
    public void actionPerformed(ActionEvent e)
    {
        String classname = getClassName(e.getSource());
        JComponent component = (JComponent)(e.getSource());

        if (classname.equals("JMenuItem"))
        {
            JMenuItem menusource = (JMenuItem)(e.getSource());
            String menutext  = menusource.getText();

            // Determine which menu option was chosen
            if (menutext.equals("Load Game"))
            {
                /* ReversiGUI    Add your code here to handle Load Game **********/
                LoadGame();
            }
            else if (menutext.equals("Save Game"))
            {
                /* ReversiGUI    Add your code here to handle Save Game **********/
                SaveGame();
            }
            else if (menutext.equals("New Game"))
            {
                /* ReversiGUI    Add your code here to handle Save Game **********/
                NewGame();
            }
        }
        // Handle the event from the user clicking on a command button
        else if (classname.equals("JButton"))
        {
            JButton button = (JButton)(e.getSource());
            int bnum = Integer.parseInt(button.getActionCommand());
            int row = bnum / GRID_SIZE;
            int col = bnum % GRID_SIZE;

            /* ReversiGUI    Add your code here to handle user clicking on the grid ***********/
            clickSquare(row, col);
        }
    }

    /**
     *  Returns the class name
     */
    protected String getClassName(Object o)
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /**
     * Create the GUI and show it.
     * For thread safety, this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI()
    {
        // Create and set up the window.
        JFrame frame = new JFrame("ReversiGui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        ReversiGui Reversigui = new ReversiGui();
        frame.setJMenuBar(Reversigui.createMenu());
        frame.setContentPane(Reversigui.createContentPane());

        // Display the window, setting the size
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    /**
     * Sets a Gui grid square at row, col to display a character
     */
    public boolean setGuiSquare(int row, int col, char c)
    {
        int bnum = row * GRID_SIZE + col;
        if (bnum >= (GRID_SIZE*GRID_SIZE))
        {
            return false;
        }
        else
        {
            buttonArray[bnum].setText(Character.toString(c));
        }
        return true;
    }

    /**
     * This is a standard main function for a Java GUI
     */
    public static void main(String[] args)
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    //************************************************************************
    //*** ReversiGUI: Modify the methods below to respond to Menu and Mouse click events

    /**
     * This method is called from the Menu event: New Game.
     * ReversiGUI
     */
    public void NewGame()
    {
        board = new Board();
        display();
        System.out.println("It's now " + board.getTurn() + " turn now!");
    }


    /**
     * This method is called from the Menu event: Load Game.
     * ReversiGUI
     */
    public void LoadGame()
    {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            try{
                File input = new File(selectedFile.getAbsolutePath());
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
            display();
            System.out.println("It's now " + board.getTurn() + " turn now!");
        }
    }


    /**
     * This method is called from the Menu event: Save Game.
     * ReversiGUI
     */
    public void SaveGame()
    {
        JFileChooser jfc = new JFileChooser();
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String savePath = selectedFile.getAbsolutePath();
            Gson gson = new Gson();
            String json = gson.toJson(board);
            try{
                File save = new File(savePath + ".json");
                //Used to clear file
                PrintWriter writer = new PrintWriter(save);
                writer.print("");
                writer.close();
                //Write game Save
                Files.write(json, save, Charsets.UTF_8);
                System.out.println("Game Saved to: " + savePath + ".json");
                System.out.println("Quiting Program");
                System.exit(0);
            } catch (IOException e){
                System.out.println("Unable to save game!");
            }
        }

    }

    /**
     * This method is called from the Mouse Click event.
     * ReversiGUI
     */
    public void clickSquare(int row, int col)
    {
        if (board == null){
            System.out.println("Game not started!");
        }else{
            if (board.isGameActive()){
                String turn = (Integer.toString(col) + Integer.toString(row));
                 boolean valid = board.runTurn(turn);
                 if (valid){
                     display();
                     System.out.println("It's now " + board.getTurn() + " turn now!");
                 }else if(!valid){
                     display();
                     System.out.println("Invalid move!");
                     System.out.println(board.getTurn() + " Please choose again!");
                 }

            }
        }
    }

    /**
     * used to display board
     */
    public void display(){
        int[][] grid = board.getGrid();
        for (int y = 0; y < grid.length; y++){
            for(int x = 0; x  < grid[y].length; x++){
                setGuiSquare(y, x, ' ');
                if (grid[y][x] == 1){
                    setGuiSquare(y, x, '#');
                }else if (grid[y][x] == 2){
                    setGuiSquare(y, x, '0');
                }
            }
        }
    }
}




