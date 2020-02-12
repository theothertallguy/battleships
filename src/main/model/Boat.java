package model;

public class Boat {
    private static final int PATROL_LENGTH = 2;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int DESTROYER_LENGTH = 3;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;

    private int boatType;
    private int boatLength;

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

    private void setCoordinate(String coordinate) {
        int row = coordinate.charAt(0);
        int column = coordinate.charAt(1);

        boatRow = row - 65;
        // A == ASCII 65
        boatColumn = column - 48;
        // 0 == ASCII 48
    }

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
