package model;

public class GridSquare {
    private static final int EMPTY_SQUARE = 100;
    private static final int MISSED_SQUARE = 110;
    private static final int BOAT_ON_SQUARE = 101;
    private static final int HIT_BOAT_ON_SQUARE = 111;
    private static final int SUNK_BOAT_ON_SQUARE = 222;

    private int squareState;

    public GridSquare() {
        squareState = EMPTY_SQUARE;
    }

    public int getSquareState() {
        return squareState;
    }

    public void addBoat() {
        squareState = BOAT_ON_SQUARE;
    }

    public void setBoatAsSunk() {
        squareState = SUNK_BOAT_ON_SQUARE;
    }

    //MODIFIES: this
    //EFFECTS: shoots at the square, returns the result which depends on if there was a boat on the square
    public void fireAtSquare() {
        if (squareState == BOAT_ON_SQUARE) {
            squareState = HIT_BOAT_ON_SQUARE;
        } else {
            squareState = MISSED_SQUARE;
        }
    }
}
