/**
 * This class stores the field for each player such s score.
 *
 * @author Pui-Hin Vincent Lee
 * @version 1.0
 */

public class Player {
    private int score;
    private int numOfMoves;

    public Player(){
        score = 0;
        numOfMoves = 0;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public int getScore() {
        return score;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementMoves(){
        numOfMoves++;
    }
}

