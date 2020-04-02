package ui;

import model.*;
import persistence.LoadGame;
import persistence.SaveGame;

import java.io.IOException;
import java.util.Scanner;

/*
* This class runs the game on a loop. The game runs through each time, and when it ends, through someone winning ot the
* player choosing to quit, they can choose to play again. The way it runs is each player places their ships, and the
* players then take turns shooting a shot at the opponent's grid. Between each player's turn, they confirm the start and
* end of their turns, so as to keep the other's board secret. Players can quit between turns.
* */

public class BattleshipGame {
    private static final String PLAYER_ONE_SAVE = "./data/playerOneGameSave.txt";
    private static final String PLAYER_TWO_SAVE = "./data/playerTwoGameSave.txt";

    private Grid player1 = new Grid();
    private Grid player2 = new Grid();

    private int currTurn = 1;

    public BattleshipGame() {

    }

    public Grid getCurrPlayer() {
        if (currTurn == 1) {
            return player1;
        }

        return player2;
    }

    public Grid getOppPlayer() {
        if (currTurn == 2) {
            return player1;
        }

        return player2;
    }

    public void fireAtGrid(int column, int row) {
        if (currTurn == 2) {
            player1.shootAtGrid(row,column);
        } else {
            player2.shootAtGrid(row,column);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the players' game saves into their game grids
    public void gameLoader() {
        LoadGame loadOldGame = new LoadGame();
        try {
            player1 = loadOldGame.loadPlayer(player1, 1, PLAYER_ONE_SAVE);
            player2 = loadOldGame.loadPlayer(player2, 2, PLAYER_TWO_SAVE);
            currTurn = loadOldGame.getTurn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: PLAYER_ONE_SAVE and PLAYER_TWO_SAVE
    //EFFECTS: saves the game data for player 1 and player 2 in their respective saves
    public void gameSaver() {
        SaveGame gameSave = new SaveGame();
        try {
            gameSave.saveFile(player1, currTurn, 1, PLAYER_ONE_SAVE);
            gameSave.saveFile(player2, currTurn, 2, PLAYER_TWO_SAVE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: resets the game back to two clear grids so game can restart
    public void gameReset() {
        player1 = new Grid();
        player2 = new Grid();
        currTurn = 1;
    }

    public int getCurrTurn() {
        return currTurn;
    }

    public int boatSinkTester() {
        return getOppPlayer().sunkBoatCheck();
    }

    public void swapTurn() {
        if (currTurn == 1) {
            currTurn = 2;
        } else {
            currTurn = 1;
        }
    }

    public int currPlayerBoatOnSquare(int y, int x) {
        return getCurrPlayer().getBoatTypeOnSquare(y,x);
    }

    public int oppGridCoordinateState(int i, int j) {
        return getOppPlayer().getCoordinateState(i,j);
    }

    public int currPlayerGridCoordinateState(int i, int j) {
        return getCurrPlayer().getCoordinateState(i,j);
    }

    public void currPlayerRemoveBoatWithID(int currTool) {
        getCurrPlayer().removeBoatFromGrid(currTool);
    }

    public void currPlayerMakePatrolBoat(String coordinateOf, char charAt) {
        getCurrPlayer().makePatrolBoat(coordinateOf,charAt);
    }

    public void currPlayerMakeSubmarineBoat(String coordinateOf, char charAt) {
        getCurrPlayer().makeSubmarineBoat(coordinateOf,charAt);
    }

    public void currPlayerMakeDestroyerBoat(String coordinateOf, char charAt) {
        getCurrPlayer().makeDestroyerBoat(coordinateOf,charAt);
    }

    public void currPlayerMakeBattleshipBoat(String coordinateOf, char charAt) {
        getCurrPlayer().makeBattleshipBoat(coordinateOf,charAt);
    }

    public void currPlayerMakeAircraftCarrierBoat(String coordinateOf, char charAt) {
        getCurrPlayer().makeAircraftCarrierBoat(coordinateOf,charAt);
    }
}
