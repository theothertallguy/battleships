package model;

/*
* The Boat class hols all of the information for a particular boat in the game of Battleships. There are 5, namely the
* patrol boat, the submarine, the destroyer, the battleship, and the aircraft carrier. Each boat is stored as a point
* and a direction, meaning that the boat starts at a point, and can be found in as many squares as equal to it's length
* in the direction the boat has specified. Each boat has an ID from 1 through 5, in the order they were listed above.
* All of the boat's stats are set upon instantiating the boat object, and then each field can be accesses through
* their respective getter methods.
* */

public class Boat {
    private static final int PATROL_LENGTH = 2;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int DESTROYER_LENGTH = 3;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;

    private int boatType = 0;
    private int boatLength = 0;

    private int rowChange = 0;
    private int columnChange = 0;

    private int boatRow = 0;
    private int boatColumn = 0;

    public Boat(int boatID, String coordinate, char direction) {
        boatType = boatID;
        setBoatLength(boatID);
        setCoordinate(coordinate);
        setDirection(direction);
    }

    //REQUIRES: a boat ID of 1, 2, 3, 4, or 5
    //MODIFIES: this
    //EFFECTS: sets the length of the boat based on which boat ID was passed in
    private void setBoatLength(int id) {
        switch (id) {
            case 1:
                boatLength = PATROL_LENGTH;
                break;
            case 2:
                boatLength = SUBMARINE_LENGTH;
                break;
            case 3:
                boatLength = DESTROYER_LENGTH;
                break;
            case 4:
                boatLength = BATTLESHIP_LENGTH;
                break;
            case 5:
                boatLength = AIRCRAFT_CARRIER_LENGTH;
                break;
        }
    }

    //REQUIRES: a valid 2 character string, first char is between A-J, second between 0-9
    //MODIFIES: this
    //EFFECTS: sets the anchor coordinate for the boat
    private void setCoordinate(String coordinate) {
        int row = coordinate.charAt(0);
        int column = coordinate.charAt(1);

        boatRow = row - 65;
        // A == ASCII 65
        boatColumn = column - 48;
        // 0 == ASCII 48
    }

    //REQUIRES: a char, one of 'N', 'S', 'E', or 'W'
    //MODIFIES: this
    //EFFECTS: sets the direction of the boat
    private void setDirection(char direction) {
        switch (direction) {
            case 'N':
                rowChange = -1;
                columnChange = 0;
                break;
            case 'S':
                rowChange = 1;
                columnChange = 0;
                break;
            case 'E':
                rowChange = 0;
                columnChange = 1;
                break;
            case 'W':
                rowChange = 0;
                columnChange = -1;
                break;
        }
    }

    public int getRow() {
        return boatRow;
    }

    public int getColumn() {
        return boatColumn;
    }

    public int getRowChange() {
        return rowChange;
    }

    public int getColumnChange() {
        return columnChange;
    }

    public int getBoatType() {
        return boatType;
    }

    public int getBoatLength() {
        return  boatLength;
    }
}
