public class Game {
    private Board board = new Board();

    /**
     * Displays the game board
     */
    public void displayBoard(){
        int[][] arrayTemp = board.getGrid();
        String linePrint;
        System.out.println("   A  B  C  D  E  F  G  H");
        for (int i = 0; i < arrayTemp.length; i++){
            int lineNo = i + 1;
            linePrint = lineNo + "  ";
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
        board.validturn(1);
        board.validturn(2);
    }

}
