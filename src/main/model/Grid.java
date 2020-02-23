package model;

/*
* The grid class holds all of the relevant statistics for a player's grid. It stores each boat, and how many
* times each boat has been hit. It also has a 10x10 array of GridSquares, each with the data for that square.
* Through this class, the main code can access each boat, and what state each GridSquare is in, and what boat
* is on it. It can also access each of the other two classes' public methods, and use them to make boats,
* shoot at the grid, place and sink boats, and sink boats and see how many are sunk.
* */

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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                battleGrid[i][j] = new GridSquare();
            }
        }
    }

    public boolean isEqualTo(Grid other) {
        return boatsEqual(other) && gridEqual(other) && hitsEqual(other);
    }

    private boolean boatsEqual(Grid other) {
        return patrolBoat.isEqualTo(other.getPatrolBoat())
                && submarineBoat.isEqualTo(other.getSubmarineBoat())
                && destroyerBoat.isEqualTo(other.getDestroyerBoat())
                && battleshipBoat.isEqualTo(other.getBattleshipBoat())
                && aircraftCarrierBoat.isEqualTo(other.getAircraftCarrierBoat());
    }

    private boolean gridEqual(Grid other) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!(getCoordinateState(i,j) == other.getCoordinateState(i,j))) {
                    return false;
                } else if (!(getBoatTypeOnSquare(i,j) == other.getBoatTypeOnSquare(i,j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hitsEqual(Grid other) {
        return (patrolHits == other.getPatrolHits())
                && (submarineHits == other.getSubmarineHits())
                && (destroyerHits == other.getDestroyerHits())
                && (battleshipHits == other.getBattleshipHits())
                && (aircraftCarrierHits == other.getAircraftCarrierHits());
    }

    public int getCoordinateState(int gridRow, int gridColumn) {
        return battleGrid[gridRow][gridColumn].getSquareState();
    }

    public int getBoatTypeOnSquare(int gridRow, int gridColumn) {
        return battleGrid[gridRow][gridColumn].getBoatRef();
    }

    //REQUIRES: 0 <= gridRow <= 9 & 0 <= gridColumn <= 9
    //MODIFIES: this, GridSquare
    //EFFECTS: fires a shot at the given square, records a boat hit if that boat was hit
    public void shootAtGrid(int gridRow, int gridColumn) {
        battleGrid[gridRow][gridColumn].fireAtSquare();
        if (battleGrid[gridRow][gridColumn].getSquareState() == 111) {
            int shipHit = battleGrid[gridRow][gridColumn].getBoatRef();
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
                default:
                    aircraftCarrierHits++;
                    break;
            }
        }
    }

    //REQUIRES: valid coordinate and direction
    // a valid coordinate is a 2 character string, first A-J, second 0-9
    // a valid direction is a char and one of 'N', 'S', 'E', or 'W'
    // coordinate and direction cannot make a ship overlap another or go off the grid
    //MODIFIES: Boat, this
    //EFFECTS: places a patrol boat at given coordinate with given direction
    public void makePatrolBoat(String coordinate, char direction) {
        patrolBoat = new Boat(1, coordinate, direction);
        placeBoatOnGrid(patrolBoat);
    }

    //REQUIRES: valid coordinate and direction
    // a valid coordinate is a 2 character string, first A-J, second 0-9
    // a valid direction is a char and one of 'N', 'S', 'E', or 'W'
    // coordinate and direction cannot make a ship overlap another or go off the grid
    //MODIFIES: Boat, this
    //EFFECTS: places a submarine at given coordinate with given direction
    public void makeSubmarineBoat(String coordinate, char direction) {
        submarineBoat = new Boat(2, coordinate, direction);
        placeBoatOnGrid(submarineBoat);
    }

    //REQUIRES: valid coordinate and direction
    // a valid coordinate is a 2 character string, first A-J, second 0-9
    // a valid direction is a char and one of 'N', 'S', 'E', or 'W'
    // coordinate and direction cannot make a ship overlap another or go off the grid
    //MODIFIES: Boat, this
    //EFFECTS: places a destroyer at given coordinate with given direction
    public void makeDestroyerBoat(String coordinate, char direction) {
        destroyerBoat = new Boat(3, coordinate, direction);
        placeBoatOnGrid(destroyerBoat);
    }

    //REQUIRES: valid coordinate and direction
    // a valid coordinate is a 2 character string, first A-J, second 0-9
    // a valid direction is a char and one of 'N', 'S', 'E', or 'W'
    // coordinate and direction cannot make a ship overlap another or go off the grid
    //MODIFIES: Boat, this
    //EFFECTS: places a battleship at given coordinate with given direction
    public void makeBattleshipBoat(String coordinate, char direction) {
        battleshipBoat = new Boat(4, coordinate, direction);
        placeBoatOnGrid(battleshipBoat);
    }

    //REQUIRES: valid coordinate and direction
    // a valid coordinate is a 2 character string, first A-J, second 0-9
    // a valid direction is a char and one of 'N', 'S', 'E', or 'W'
    // coordinate and direction cannot make a ship overlap another or go off the grid
    //MODIFIES: Boat, this
    //EFFECTS: places an aircraft carrier at given coordinate with given direction
    public void makeAircraftCarrierBoat(String coordinate, char direction) {
        aircraftCarrierBoat = new Boat(5, coordinate, direction);
        placeBoatOnGrid(aircraftCarrierBoat);
    }

    //REQUIRES: a boat with valid coordinates and direction
    // coordinate and direction cannot make a ship overlap another or go off the grid
    //MODIFIES: GridSquare
    //EFFECTS: places a boat on the grid by setting each of the boat's squares to having that boat
    public void placeBoatOnGrid(Boat boatToPlace) {
        int boatLength = boatToPlace.getBoatLength();
        int boatID = boatToPlace.getBoatType();
        int currGridRow = boatToPlace.getRow();
        int currGridColumn = boatToPlace.getColumn();
        int boatRowChange = boatToPlace.getRowChange();
        int boatColumnChange = boatToPlace.getColumnChange();

        for (int i = 0; i < boatLength; i++) {
            battleGrid[currGridRow][currGridColumn].addBoat(boatID);
            currGridRow += boatRowChange;
            currGridColumn += boatColumnChange;
        }
    }

    //REQUIRES: a boat with valid coordinates and direction
    // coordinate and direction cannot make a ship overlap another or go off the grid
    //MODIFIES: GridSquare
    //EFFECTS: sinks a boat by setting each of it's squares to having sank
    private void sinkBoatOnGrid(Boat boatToPlace) {
        int boatLength = boatToPlace.getBoatLength();
        int currGridRow = boatToPlace.getRow();
        int currGridColumn = boatToPlace.getColumn();
        int boatRowChange = boatToPlace.getRowChange();
        int boatColumnChange = boatToPlace.getColumnChange();

        for (int i = 0; i < boatLength; i++) {
            battleGrid[currGridRow][currGridColumn].setBoatAsSunk();
            currGridRow += boatRowChange;
            currGridColumn += boatColumnChange;
        }
    }

    //MODIFIES: this
    //EFFECTS: checks how many boats are sunk, and returns how many are
    public int sunkBoatCheck() {
        int boatsSunk = 0;

        if (patrolHits == 2) {
            sinkBoatOnGrid(patrolBoat);
            boatsSunk++;
        }

        if (submarineHits == 3) {
            sinkBoatOnGrid(submarineBoat);
            boatsSunk++;
        }

        if (destroyerHits == 3) {
            sinkBoatOnGrid(destroyerBoat);
            boatsSunk++;
        }

        if (battleshipHits == 4) {
            sinkBoatOnGrid(battleshipBoat);
            boatsSunk++;
        }

        if (aircraftCarrierHits == 5) {
            sinkBoatOnGrid(aircraftCarrierBoat);
            boatsSunk++;
        }

        return boatsSunk;
    }

    public Boat getPatrolBoat() {
        return patrolBoat;
    }

    public Boat getSubmarineBoat() {
        return submarineBoat;
    }

    public Boat getDestroyerBoat() {
        return destroyerBoat;
    }

    public Boat getBattleshipBoat() {
        return battleshipBoat;
    }

    public Boat getAircraftCarrierBoat() {
        return aircraftCarrierBoat;
    }

    public int getPatrolHits() {
        return patrolHits;
    }

    public int getSubmarineHits() {
        return submarineHits;
    }

    public int getDestroyerHits() {
        return destroyerHits;
    }

    public int getBattleshipHits() {
        return battleshipHits;
    }

    public int getAircraftCarrierHits() {
        return aircraftCarrierHits;
    }

    public void setPatrolHits(int hits) {
        patrolHits = hits;
    }

    public void setSubmarineHits(int hits) {
        submarineHits = hits;
    }

    public void setDestroyerHits(int hits) {
        destroyerHits = hits;
    }

    public void setBattleshipHits(int hits) {
        battleshipHits = hits;
    }

    public void setAircraftCarrierHits(int hits) {
        aircraftCarrierHits = hits;
    }

    public GridSquare[][] getBattleGrid() {
        return battleGrid;
    }

    public void setPatrolBoat(Boat patrolBoat) {
        this.patrolBoat = patrolBoat;
    }

    public void setSubmarineBoat(Boat submarineBoat) {
        this.submarineBoat = submarineBoat;
    }

    public void setDestroyerBoat(Boat destroyerBoat) {
        this.destroyerBoat = destroyerBoat;
    }

    public void setBattleshipBoat(Boat battleshipBoat) {
        this.battleshipBoat = battleshipBoat;
    }

    public void setAircraftCarrierBoat(Boat aircraftCarrierBoat) {
        this.aircraftCarrierBoat = aircraftCarrierBoat;
    }
}
