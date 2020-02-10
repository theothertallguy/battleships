package model;

public class Grid {
    private static final int GRID_SIZE = 10;

    private GridSquare[][] battleGrid;

    private Boat patrolBoat;
    private Boat submarineBoat;
    private Boat destroyerBoat;
    private Boat battleshipBoat;
    private Boat aircraftCarrierBoat;


    public Grid() {
        battleGrid = new GridSquare[GRID_SIZE][GRID_SIZE];
    }

    public int getCoordinateState(int gridX, int gridY) {
        return battleGrid[gridX][gridY].getSquareState();
    }

    public void setBoatOnSquare(int gridX, int gridY) {
        battleGrid[gridX][gridY].addBoat();
    }

    public void shootAtBoat(int gridX, int gridY) {
        battleGrid[gridX][gridY].fireAtSquare();
    }

    public void sinkBoat(int gridX, int gridY) {
        battleGrid[gridX][gridY].setBoatAsSunk();
    }

    public void makePatrolBoat(String coordinate, char direction) {
        patrolBoat = new Boat(1, coordinate, direction);
        //Need to add functionality for setting grid squares to having a boat
        //Save hit numbers as an int count
        //Add ship code in GridSquare for easy knowledge of what boat is hit
        //Note to self... Making the damned thing play the game is so easy in comparison
    }

    public void makeSubmarineBoat(String coordinate, char direction) {
        submarineBoat = new Boat(2, coordinate, direction);
    }

    public void makeDestroyerBoat(String coordinate, char direction) {
        destroyerBoat = new Boat(3, coordinate, direction);
    }

    public void makeBattleshipBoat(String coordinate, char direction) {
        battleshipBoat = new Boat(4, coordinate, direction);
    }

    public void makeAircraftCarrierBoat(String coordinate, char direction) {
        aircraftCarrierBoat = new Boat(5, coordinate, direction);
    }
}
