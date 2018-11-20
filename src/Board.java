import java.util.ArrayList;

public class Board {
    private int[][] grid = new int[8][8]; //Saves Array
    public Player player1 = new Player(); //Creates player 1
    public Player player2 = new Player(); //Creates player 2
    private int turn; //Stores whos turn it is.
    private int turnsTaken = 0; //the number of turns taken in total
    private boolean gameAcive; //Turns false when the game ends

    /**
     * accessor for gameActive
     *
     * @return if the game is active as boolean
     */
    public boolean isGameAcive() {
        return gameAcive;
    }

    /**
     * Constructor method
     */
    public Board() {
        //sets Player ones initial counters
        grid[3][3] = 1;
        grid[4][4] = 1;
        //sets Player two initial counters
        grid[4][3] = 2;
        grid[3][4] = 2;
        //sets turn to Player one
        turn = 1;
        gameAcive = true;

        calculateScore();
    }

    /**
     * get the turn
     *
     * @return returns who's turn it is
     */
    public int getTurn() {
        return turn;
    }

    /**
     * returns Board 2d array
     *
     * @return 2d array of Board
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * set the grid 2d array
     *
     * @param grid The grid in a 2d 8x8 array
     */
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    /**
     * Set which Player's turn it is
     *
     * @param turn either Player 1 or 2
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Gives a ArrayList of valid moves the selected player can make
     *
     * @param player Select player (Either player 1 or 2)
     * @return An ArrayList of Strings of what moves are valid
     */
    public ArrayList<String> validMoves(int player) {
        ArrayList<String> validMoves = new ArrayList<>();
        int opponent = 0;
        if (player == 1) {
            opponent = 2;
        } else if (player == 2) {
            opponent = 1;
        } else {
            System.out.println("ERROR IN SELECTED PLAYER!");
            System.exit(1);
        }
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                int valid = 0;
                int testRow;
                int testColumn;
                if (grid[row][column] == 0) {
                    //check top left
                    testRow = row - 1;
                    testColumn = column - 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    //Check top right
                    testRow = row - 1;
                    testColumn = column + 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    // Check bottom left
                    testRow = row + 1;
                    testColumn = column - 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    //Check bottom right
                    testRow = row + 1;
                    testColumn = column + 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    //Check top
                    testRow = row - 1;
                    testColumn = column;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    //Check bottom
                    testRow = row + 1;
                    testColumn = column;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    //Check Left
                    testRow = row;
                    testColumn = column - 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    //Check Right
                    testRow = row;
                    testColumn = column + 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
                        if (grid[testRow][testColumn] == opponent) {
                            valid++;
                        }
                    }
                    //If there are valid square found, then increment by one
                    if (valid > 0) {
                        if (ownValid(player, column, row) == true) {
                            validMoves.add(Integer.toString(row) + Integer.toString(column));
                        }
                    }
                }
            }
        }
        return validMoves;
    }

    /**
     * Checks if the player has any valid counters that can be flipped at the location given.
     *
     * @param player The player to be checked
     * @param X      The Column of the spot being checked on the grid
     * @param Y      The Row of the spot being checked on the grid
     * @return True/False of if the the square has any counters to flip
     */
    private boolean ownValid(int player, int X, int Y) {
        int totalOwn = 0;
        int testRow;
        int testColumn;
        int ownPiece;// This is used to check make sure then is one piece of the oppents piece between their piece and their spot being checked
        //check top left
        testRow = Y - 1;
        testColumn = X - 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testRow--;
            testColumn--;
            ownPiece++;
        }
        //check top right
        testRow = Y - 1;
        testColumn = X + 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testRow--;
            testColumn++;
            ownPiece++;
        }
        //check bottom left
        testRow = Y + 1;
        testColumn = X - 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testRow++;
            testColumn--;
            ownPiece++;
        }
        //Check bottom right
        testRow = Y + 1;
        testColumn = X + 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testRow++;
            testColumn++;
            ownPiece++;
        }
        //check top
        testRow = Y + 1;
        testColumn = X;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testRow++;
            ownPiece++;
        }
        //check down
        testRow = Y - 1;
        testColumn = X;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testRow--;
            ownPiece++;
        }
        //check left
        testRow = Y;
        testColumn = X - 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testColumn--;
            ownPiece++;
        }
        //check right
        testRow = Y;
        testColumn = X + 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                totalOwn++;
                break;
            }
            testColumn++;
            ownPiece++;
        }
        if (totalOwn > 0) {
            return true;
        } else if (totalOwn == 0) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * Force add a piece to the board
     *
     * @param move   position to place piece on to
     * @param player either player 1 or 2
     */
    public void forceMove(String move, int player) {
        int column = -1;
        String[] splitMove = move.split("");
        if (splitMove[0].equals("A") || splitMove[0].equals("a")) {
            column = 0;
        } else if (splitMove[0].equals("B") || splitMove[0].equals("b")) {
            column = 1;
        } else if (splitMove[0].equals("C") || splitMove[0].equals("c")) {
            column = 2;
        } else if (splitMove[0].equals("D") || splitMove[0].equals("d")) {
            column = 3;
        } else if (splitMove[0].equals("E") || splitMove[0].equals("e")) {
            column = 4;
        } else if (splitMove[0].equals("F") || splitMove[0].equals("f")) {
            column = 5;
        } else if (splitMove[0].equals("G") || splitMove[0].equals("g")) {
            column = 6;
        } else if (splitMove[0].equals("H") || splitMove[0].equals("h")) {
            column = 7;
        }
        if (column < 0 || player < 0 || player > 2) {
            System.out.println("Unable to add piece");
        } else {
            int row = Integer.parseInt(splitMove[1]);
            grid[row][column] = player;
        }
    }

    /**
     * Calculated and updates the score for each player
     */
    public void calculateScore() {
        int player1Score = 0;
        int player2Score = 0;
        //Get the total counter count/score for each player
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == 1) {
                    player1Score++;
                } else if (grid[row][column] == 2) {
                    player2Score++;
                }
            }
        }
        player1.setScore(player1Score);
        player2.setScore(player2Score);
    }

    /**
     * This method has to be run at the end of a players turn
     * It's jon is to
     * - Calculate the player's score
     * - Switch turn to the next player
     * - check if the next player has a valid move
     */
    public void endTurn() {
        turnsTaken++;
        calculateScore();
        // The following checks if the next player has any valid moves and then switches to the if it's their turn.
        ArrayList<String> player2Moves = validMoves(2);
        ArrayList<String> player1Moves = validMoves(1);
        if (player1Moves.isEmpty() && player2Moves.isEmpty()) {
            //If there is no valid moves then the game ends
            gameAcive = false;
        } else if (turn == 1) {
            player1.incrementMoves();
            if (!player2Moves.isEmpty()) {
                turn = 2;
            } else {
                turn = 1;
            }
        } else if (turn == 2) {
            player2.incrementMoves();
            if (!player1Moves.isEmpty()) {
                turn = 1;
            } else {
                turn = 2;
            }
        }
    }

    /**
     * Allows for moves to be made on the board.
     *
     * @param move the move being made by the player
     * @return if the move was valid returns True, else returns false
     */
    public boolean runTurn(String move) {
        ArrayList<String> validMoves = validMoves(turn);
        int column = -1;
        String[] splitMove = move.split("");
        if (splitMove[0].equals("A") || splitMove[0].equals("a")) {
            column = 0;
        } else if (splitMove[0].equals("B") || splitMove[0].equals("b")) {
            column = 1;
        } else if (splitMove[0].equals("C") || splitMove[0].equals("c")) {
            column = 2;
        } else if (splitMove[0].equals("D") || splitMove[0].equals("d")) {
            column = 3;
        } else if (splitMove[0].equals("E") || splitMove[0].equals("e")) {
            column = 4;
        } else if (splitMove[0].equals("F") || splitMove[0].equals("f")) {
            column = 5;
        } else if (splitMove[0].equals("G") || splitMove[0].equals("g")) {
            column = 6;
        } else if (splitMove[0].equals("H") || splitMove[0].equals("h")) {
            column = 7;
        } else {
            return false; //it returns a false when an invalid option if given
        }
        //Following if statement is to check if the row number given if within range of grid.
        if (Integer.parseInt(splitMove[1]) > 8 || Integer.parseInt(splitMove[1]) < 0) {
            return false;
        }
        String testMove = (splitMove[1] + Integer.toString(column));
        if (validMoves.contains(testMove)) {
            grid[Integer.parseInt(splitMove[1])][column] = turn;
            flipCounters(turn, column, Integer.parseInt(splitMove[1]));
            endTurn();
            return true;
        } else {
            return false;
        }
    }

    public void flipCounters(int player, int X, int Y) {
        int totalOwn = 0;
        int testRow;
        int testColumn;
        int flipRow;
        int flipColumn;
        int ownPiece;// This is used to check make sure then is one piece of the oppents piece between their piece and their spot being checked
        //check top left
        testRow = Y - 1;
        testColumn = X - 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y - 1;
                flipColumn = X - 1;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipRow--;
                    flipColumn--;
                }
                break;
            }
            testRow--;
            testColumn--;
            ownPiece++;
        }
        //check top right
        testRow = Y - 1;
        testColumn = X + 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y - 1;
                flipColumn = X + 1;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipRow--;
                    flipColumn++;
                }
                break;
            }
            testRow--;
            testColumn++;
            ownPiece++;
        }
        //check bottom left
        testRow = Y + 1;
        testColumn = X - 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y + 1;
                flipColumn = X - 1;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipRow++;
                    flipColumn--;
                }
                break;
            }
            testRow++;
            testColumn--;
            ownPiece++;
        }
        //Check bottom right
        testRow = Y + 1;
        testColumn = X + 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y + 1;
                flipColumn = X + 1;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipRow++;
                    flipColumn++;
                }
                break;
            }
            testRow++;
            testColumn++;
            ownPiece++;
        }
        //check top
        testRow = Y + 1;
        testColumn = X;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y + 1;
                flipColumn = X;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipRow++;
                }
                break;
            }
            testRow++;
            ownPiece++;
        }
        //check down
        testRow = Y - 1;
        testColumn = X;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y - 1;
                flipColumn = X;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipRow--;
                }
                break;
            }
            testRow--;
            ownPiece++;
        }
        //check left
        testRow = Y;
        testColumn = X - 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y;
                flipColumn = X - 1;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipColumn--;
                }
                break;
            }
            testColumn--;
            ownPiece++;
        }
        //check right
        testRow = Y;
        testColumn = X + 1;
        ownPiece = 0;
        while (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8) {
            if (grid[testRow][testColumn] == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece == 0) {
                break;
            } else if (grid[testRow][testColumn] == player && ownPiece > 0) {
                flipRow = Y;
                flipColumn = X + 1;
                while (grid[flipRow][flipColumn] != player){
                    grid[flipRow][flipColumn] = player;
                    flipColumn++;
                }
                break;
            }
            testColumn++;
            ownPiece++;
        }
    }
}