package model;

/*
* Each GridSquare has a state and a reference to a boat. A boat ID of 0 means there isn't a boat on it, and the boat ID
* is meant to correspond with that of the Boat object placed on the grid at that point. There are 5 states, and each
* represents one of the 5 possible states for a square to be in. a square can be empty or have a boat, and can be
* shot at or not shot at, and if there is a boat and it's sunk, it records that as well.
* */

public class GridSquare {
    private static final int EMPTY_SQUARE = 100;
    private static final int MISSED_SQUARE = 110;
    private static final int BOAT_ON_SQUARE = 101;
    private static final int HIT_BOAT_ON_SQUARE = 111;
    private static final int SUNK_BOAT_ON_SQUARE = 222;

    private int squareState;
    private int boatRef = 0;

    public GridSquare() {
        squareState = EMPTY_SQUARE;
    }

    public int getSquareState() {
        return squareState;
    }

    public int getBoatRef() {
        return boatRef;
    }

    //MODIFIES: this
    //EFFECTS: sets square as having a boat on it, gives square ID of that particular boat
    public void addBoat(int boatID) {
        squareState = BOAT_ON_SQUARE;
        boatRef = boatID;
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

    public void setSquareState(int state) {
        squareState = state;
    }

    public void setBoatRef(int boatID) {
        boatRef = boatID;
    }
}
