package model;

public class Boat {
    private static final int PATROL_LENGTH = 2;
    private static final int SUBMARINE_LENGTH = 3;
    private static final int DESTROYER_LENGTH = 3;
    private static final int BATTLESHIP_LENGTH = 4;
    private static final int AIRCRAFT_CARRIER_LENGTH = 5;

    private int boatType;
    private int boatLength;

    private int facingX = 0;
    private int facingY = 0;

    private int boatX = 0;
    private int boatY = 0;

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
        int x = coordinate.charAt(0);
        int y = coordinate.charAt(1);

        boatX = x - 65;
        // A == ASCII 65
        boatY = y - 48;
        // 0 == ASCII 48

        //rows are x, columns are y
    }

    private void setDirection(char direction) {
        switch (direction) {
            case 'N':
                facingX = 0;
                facingY = -1;
                break;
            case 'S':
                facingX = 0;
                facingY = 1;
                break;
            case 'E':
                facingX = 1;
                facingY = 0;
                break;
            case 'W':
                facingX = -1;
                facingY = 0;
                break;
        }
    }

    public int getX() {
        return boatX;
    }

    public int getY() {
        return boatY;
    }

    public int getXDir() {
        return facingX;
    }

    public int getYDir() {
        return facingY;
    }

    public int getBoatType() {
        return boatType;
    }

    public int getBoatLength() {
        return  boatLength;
    }
}
