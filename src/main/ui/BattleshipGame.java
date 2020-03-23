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

    private boolean gameIsBeingPlayed = true;
    private int currTurn = 1;
    private boolean shipBattleHasEnded = false;
    private boolean playerIsLeaving = false;

    Scanner userInput = new Scanner(System.in);

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
    //EFFECTS: loads the main menu, and allows the player to choose what they want to do
    private void mainMenu() {
        System.out.println("Welcome to Battleships!");
        System.out.println("Start A New Game | N");
        System.out.println("Load Saved Game  | L");
        System.out.println("Quit Application | Q");
        String menuChoice = userInput.nextLine().toUpperCase();

        if (menuChoice.equals("N")) {
            System.out.println("New Game Start!");
            System.out.println("Player 2, look away! Player 1, place your ships.");
            placeShips(player1);
            System.out.println("Player 1, look away! Player 2, place your ships.");
            placeShips(player2);
        } else if (menuChoice.equals("L")) {
            gameLoader();
        } else if (menuChoice.equals("Q")) {
            gameIsBeingPlayed = false;
            shipBattleHasEnded = true;
            playerIsLeaving = true;
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

    //MODIFIES: this
    //EFFECTS: selects whose turn it is,a cnd checks to see if either player has won
    private void turnChooser() {
        if (player1.sunkBoatCheck() == 5) {
            System.out.println("Player 2 wins!");
            shipBattleHasEnded = true;
        } else if (player2.sunkBoatCheck() == 5) {
            System.out.println("Player 1 wins!");
            shipBattleHasEnded = true;
        } else if (currTurn == 1) {
            yourTurn(player1, player2);
            currTurn = 2;
        } else if (currTurn == 2) {
            yourTurn(player2, player1);
            currTurn = 1;
        }
    }

    //EFFECTS: creates each of the 5 boats and places each of them on the player's grid
    private void placeShips(Grid player) {
        if (gameIsBeingPlayed) {
            startTurn();
            if (gameIsBeingPlayed) {

                placePatrolBoat(player);
                placeSubmarineBoat(player);
                placeDestroyerBoat(player);
                placeBattleshipBoat(player);
                placeAircraftCarrierBoat(player);

                endTurn();
            }
        }
    }

    //EFFECTS: receives user input about where they want their patrol boat to be placed, and in what direction
    private void placePatrolBoat(Grid player) {
        System.out.println("Choose the co-ordinate for your patrol boat.");
        String coordinate = userInput.nextLine();
        coordinate = coordinate.toUpperCase();
        System.out.println("Choose the direction for your patrol boat.");
        System.out.println(("N -> North, E -> East, S -> South, W -> West"));
        String dirIn = userInput.nextLine();
        dirIn = dirIn.toUpperCase();
        char direction = dirIn.charAt(0);

        player.makePatrolBoat(coordinate, direction);
    }

    //EFFECTS: receives user input about where they want their submarine to be placed, and in what direction
    private void placeSubmarineBoat(Grid player) {
        System.out.println("Choose the co-ordinate for your submarine.");
        String coordinate = userInput.nextLine();
        coordinate = coordinate.toUpperCase();
        System.out.println("Choose the direction for your submarine.");
        System.out.println(("N -> North, E -> East, S -> South, W -> West"));
        String dirIn = userInput.nextLine();
        dirIn = dirIn.toUpperCase();
        char direction = dirIn.charAt(0);

        player.makeSubmarineBoat(coordinate, direction);
    }

    //EFFECTS: receives user input about where they want their destroyer to be placed, and in what direction
    private void placeDestroyerBoat(Grid player) {
        System.out.println("Choose the co-ordinate for your destroyer.");
        String coordinate = userInput.nextLine();
        coordinate = coordinate.toUpperCase();
        System.out.println("Choose the direction for your destroyer.");
        System.out.println(("N -> North, E -> East, S -> South, W -> West"));
        String dirIn = userInput.nextLine();
        dirIn = dirIn.toUpperCase();
        char direction = dirIn.charAt(0);

        player.makeDestroyerBoat(coordinate, direction);
    }

    //EFFECTS: receives user input about where they want their battleship to be placed, and in what direction
    private void placeBattleshipBoat(Grid player) {
        System.out.println("Choose the co-ordinate for your battleship.");
        String coordinate = userInput.nextLine();
        coordinate = coordinate.toUpperCase();
        System.out.println("Choose the direction for your battleship.");
        System.out.println(("N -> North, E -> East, S -> South, W -> West"));
        String dirIn = userInput.nextLine();
        dirIn = dirIn.toUpperCase();
        char direction = dirIn.charAt(0);

        player.makeBattleshipBoat(coordinate, direction);
    }

    //EFFECTS: receives user input about where they want their aircraft carrier to be placed, and in what direction
    private void placeAircraftCarrierBoat(Grid player) {
        System.out.println("Choose the co-ordinate for your aircraft carrier.");
        String coordinate = userInput.nextLine();
        coordinate = coordinate.toUpperCase();
        System.out.println("Choose the direction for your aircraft carrier.");
        System.out.println(("N -> North, E -> East, S -> South, W -> West"));
        String dirIn = userInput.nextLine();
        dirIn = dirIn.toUpperCase();
        char direction = dirIn.charAt(0);

        player.makeAircraftCarrierBoat(coordinate, direction);
    }

    //MODIFIES: enemyPlayer (a Grid)
    //EFFECTS: runs the player's turn; they input the coordinate of their shot, and then it shoots at the enemy grid
    private void yourTurn(Grid playerWithTurn, Grid enemyPlayer) {
        startTurn();

        if (gameIsBeingPlayed) {
            System.out.println("Where would you like to shoot?");
            String coordinate = userInput.nextLine();
            coordinate = coordinate.toUpperCase();

            int row = getRow(coordinate);
            int column = getColumn(coordinate);
            int sunkBefore = enemyPlayer.sunkBoatCheck();

            enemyPlayer.shootAtGrid(row, column);

            int sunkAfter = enemyPlayer.sunkBoatCheck();

            if (enemyPlayer.getCoordinateState(row, column) < 111) {
                System.out.println("You missed...");
            } else {
                System.out.println("You hit one of the enemy ships!");
            }

            if (sunkBefore != sunkAfter) {
                System.out.println("Whoa! That hit sunk one of their ships!");
            }

            endTurn();
        }
    }

    //MODIFIES: this
    //EFFECTS: confirms that the player wants to start their turn, or offers the option to quit
    private void startTurn() {
        System.out.println("It's player " + currTurn + "'s turn. If it isn't your turn, look away!");
        System.out.println("Otherwise, enter Q to quit, or anything else to start your turn.");
        String choice = userInput.nextLine();
        choice = choice.toUpperCase();

        if (choice.equals("Q")) {
            gameIsBeingPlayed = false;
            shipBattleHasEnded = true;
        }
    }

    //MODIFIES: this
    //EFFECTS: a confirmation to end the current players turn, or quit if they want to
    private void endTurn() {
        System.out.println("If you want to quit, enter Q, otherwise, enter anything to end your turn.");
        String choice = userInput.nextLine();
        choice = choice.toUpperCase();

        if (choice.equals("Q")) {
            gameIsBeingPlayed = false;
            shipBattleHasEnded = true;
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }

    //REQUIRES: a valid coordinate
    // a valid coordinate is a 2 character string, first A-J, second 0-9
    //EFFECTS: gets the numbered row from a coordinate string
    private int getRow(String coordinate) {
        int row = coordinate.charAt(0);

        row = row - 65;
        // A == ASCII 65
        return row;
    }

    //REQUIRES: a valid coordinate
    // a valid coordinate is a 2 character string, first A-J, second 0-9
    //EFFECTS: gets the numbered column from a coordinate string
    private int getColumn(String coordinate) {
        int column = coordinate.charAt(1);

        column = column - 48;
        // 0 == ASCII 48
        return column;
    }

    //MODIFIES: this, Grid
    //EFFECTS: asks the player if they want to play again, or quit out of the game
    private void askIfPlayingAgain() {
        System.out.println("Choose an Option");
        System.out.println("Start A New Game    | N");
        System.out.println("Resume Current Game | R");
        System.out.println("Save And Quit       | S");
        System.out.println("Quit Without Saving | Q");
        String againAnswer = userInput.nextLine();
        againAnswer = againAnswer.toUpperCase();
        System.out.println();

        if (againAnswer.equals("N")) {
            gameReset();
        } else if (againAnswer.equals("R")) {
            System.out.println("Resume Goes Here");
        } else if (againAnswer.equals("S")) {
            gameSaver();
            gameIsBeingPlayed = false;
            System.out.println("Goodbye now.");
        } else {
            gameIsBeingPlayed = false;
            System.out.println("Goodbye now.");
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
        System.out.println("Ok, restarting!");
        player1 = new Grid();
        player2 = new Grid();
        currTurn = 1;
        shipBattleHasEnded = false;
        gameIsBeingPlayed = true;
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
}
