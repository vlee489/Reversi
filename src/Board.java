public class Board {
    private int[][] grid = new int[8][8]; //Saves Array
    private Player player1 = new Player(); //Creates player 1
    private Player player2 = new Player(); //Creates player 2
    private int turn; //Stores whos turn it is.
    private int turnsTaken; //

    /**
     * Constructor method
     */
    public Board(){
        //sets Player ones initial counters
        grid[3][3] = 1;
        grid[4][4] = 1;
        //sets Player two initial counters
        grid[4][3] = 2;
        grid[3][4] = 2;
        //sets turn to Player one
        turn = 1;
    }

    /**
     * get the turn
     * @return returns who's turn it is
     */
    public int getTurn() {
        return turn;
    }

    /**
     * returns Board 2d array
     * @return 2d array of Board
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * set the grid 2d array
     * @param grid The grid in a 2d 8x8 array
     */
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    /**
     * Set which Player's turn it is
     * @param turn either Player 1 or 2
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     *
     * @param player either player 1 or 2
     * @return if the player's turn is valid or not
     */
    public boolean validturn(int player){
        int validMoves = 0;
        for (int row = 0; row < grid.length; row++){
            for (int column = 0; column < grid[row].length; column++){
                int invalid = 0;
                int testRow;
                int testColumn;
                if (grid[row][column] == 0){
                    //check top left
                    testRow = row - 1;
                    testColumn = column - 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    //Check top right
                    testRow = row - 1;
                    testColumn = column + 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    // Check bottom left
                    testRow = row + 1;
                    testColumn = column - 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    //Check bottom right
                    testRow = row + 1;
                    testColumn = column + 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    //Check top
                    testRow = row - 1;
                    testColumn = column;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    //Check bottom
                    testRow = row + 1;
                    testColumn = column;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    //Check Left
                    testRow = row;
                    testColumn = column - 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    //Check Right
                    testRow = row;
                    testColumn = column + 1;
                    if (testColumn >= 0 && testRow >= 0 && testColumn < 8 && testRow < 8){
                        if (grid[testRow][testColumn] == player){
                            invalid++;
                        }
                    }
                    //If there are no invalid square found, then increment by one
                    if (invalid == 0){
                        validMoves++;
                    }
                }
            }
        }
        if (validMoves == 0){
            return false;
        }else if (validMoves > 0){
            System.out.println("No. of valid moves: "+ validMoves);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Force add a piece to the board
     * @param move position to place piece on to
     * @param player either player 1 or 2
     */
    public void forceMove(String move, int player){
        int column = -1;
        String[] splitMove = move.split("");
        if (splitMove[0].equals("A")){
            column = 0;
        } else if (splitMove[0].equals("B")){
            column = 1;
        } else if (splitMove[0].equals("C")){
            column = 2;
        } else if (splitMove[0].equals("D")){
            column = 3;
        } else if (splitMove[0].equals("E")){
            column = 4;
        } else if (splitMove[0].equals("F")){
            column = 5;
        } else if (splitMove[0].equals("G")){
            column = 6;
        } else if (splitMove[0].equals("H")){
            column = 7;
        }
        if (player != 1 || player != 2 || column < 0){
            System.out.println("Unable to add piece");
        }else{
            int row = Integer.parseInt(splitMove[1]);
            grid[column][row] = player;
        }
    }

    public void tests(){
    }

}
