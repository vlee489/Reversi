/**
 * This class stores the field for each player such s score.
 *
 * @author Pui-Hin Vincent Lee
 * @version 1.0
 */

public class Player {
    private int score;
    private int numOfMoves;

    /**
     * Constructor method to set the default to 0
     */
    public Player(){
        score = 0;
        numOfMoves = 0;
    }

    /**
     * get the score
     * @return the score of object
     */
    public int getScore() {
        return score;
    }

    /**
     * sets the score
     * @param score the score to be entered.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Increments the moves by one when ran.
     */
    public void incrementMoves(){
        numOfMoves++;
    }
}

