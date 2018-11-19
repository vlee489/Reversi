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
}

