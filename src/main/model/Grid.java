package model;

public class Grid {
    private static final int GRID_SIZE = 10;

    private GridSquare[][] battleGrid;

    private Boat patrolBoat;             //Boat ID == 1
    private Boat submarineBoat;          //Boat ID == 2
    private Boat destroyerBoat;          //Boat ID == 3
    private Boat battleshipBoat;         //Boat ID == 4
    private Boat aircraftCarrierBoat;    //Boat ID == 5

    private int patrolHits = 0;
    private int submarineHits = 0;
    private int destroyerHits = 0;
    private int battleshipHits = 0;
    private int aircraftCarrierHits = 0;


    public Grid() {
        battleGrid = new GridSquare[GRID_SIZE][GRID_SIZE];
    }

    public int getCoordinateState(int gridX, int gridY) {
        return battleGrid[gridX][gridY].getSquareState();
    }

    public void setBoatOnSquare(int gridX, int gridY, int boatID) {
        battleGrid[gridX][gridY].addBoat(boatID);
    }

    public void shootAtBoat(int gridX, int gridY) {
        battleGrid[gridX][gridY].fireAtSquare();
        if (battleGrid[gridX][gridY].getSquareState() == 111) {
            int shipHit = battleGrid[gridX][gridY].getBoatRef();
            switch (shipHit) {
                case 1:
                    patrolHits++;
                    break;
                case 2:
                    submarineHits++;
                    break;
                case 3:
                    destroyerHits++;
                    break;
                case 4:
                    battleshipHits++;
                    break;
                case 5:
                    aircraftCarrierHits++;
                    break;
            }
        }
    }

    public void sinkBoat(int gridX, int gridY) {
        battleGrid[gridX][gridY].setBoatAsSunk();
    }

    public void makePatrolBoat(String coordinate, char direction) {
        patrolBoat = new Boat(1, coordinate, direction);
        placeBoatOnGrid(patrolBoat);
    }

    public void makeSubmarineBoat(String coordinate, char direction) {
        submarineBoat = new Boat(2, coordinate, direction);
        placeBoatOnGrid(submarineBoat);
    }

    public void makeDestroyerBoat(String coordinate, char direction) {
        destroyerBoat = new Boat(3, coordinate, direction);
        placeBoatOnGrid(destroyerBoat);
    }

    public void makeBattleshipBoat(String coordinate, char direction) {
        battleshipBoat = new Boat(4, coordinate, direction);
        placeBoatOnGrid(battleshipBoat);
    }

    public void makeAircraftCarrierBoat(String coordinate, char direction) {
        aircraftCarrierBoat = new Boat(5, coordinate, direction);
        placeBoatOnGrid(aircraftCarrierBoat);
    }

    private void placeBoatOnGrid(Boat boatToPlace) {
        int boatLength = boatToPlace.getBoatLength();
        int boatID = boatToPlace.getBoatType();
        int currGridX = boatToPlace.getX();
        int currGridY = boatToPlace.getY();
        int boatXDir = boatToPlace.getXDir();
        int boatYDir = boatToPlace.getYDir();

        for (int i = 0; i < boatLength; i++) {
            battleGrid[currGridX][currGridY].addBoat(boatID);
            currGridX += boatXDir;
            currGridY += boatYDir;
        }
    }

    private void sinkBoatOnGrid(Boat boatToPlace) {
        int boatLength = boatToPlace.getBoatLength();
        int currGridX = boatToPlace.getX();
        int currGridY = boatToPlace.getY();
        int boatXDir = boatToPlace.getXDir();
        int boatYDir = boatToPlace.getYDir();

        for (int i = 0; i < boatLength; i++) {
            battleGrid[currGridX][currGridY].setBoatAsSunk();
            currGridX += boatXDir;
            currGridY += boatYDir;
        }
    }
}
