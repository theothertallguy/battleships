package ui;

import model.*;

import java.util.Scanner;

/*
* This class runs the game on a loop. The game runs through each time, and when it ends, through someone winning ot the
* player choosing to quit, they can choose to play again. The way it runs is each player places their ships, and the
* players then take turns shooting a shot at the opponent's grid. Between each player's turn, they confirm the start and
* end of their turns, so as to keep the other's board secret. Players can quit between turns.
* */

public class BattleshipGame {
    private Grid player1 = new Grid();
    private Grid player2 = new Grid();

    private boolean gameIsBeingPlayed = true;
    private int currTurn = 1;
    private boolean shipBattleHasEnded = false;

    Scanner userInput = new Scanner(System.in);

    public BattleshipGame() {
        while (gameIsBeingPlayed) {
            System.out.println("Player 2, look away! Player 1, place your ships.");
            placeShips(player1);
            System.out.println("Player 1, look away! Player 2, place your ships.");
            placeShips(player2);

            while (!shipBattleHasEnded) {
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
                System.out.println();
            }

            askIfPlayingAgain();
        }
    }

    //EFFECTS: creates each of the 5 boats and places each of them on the player's grid
    private void placeShips(Grid player) {
        if (gameIsBeingPlayed) {
            startTurn();
            if (gameIsBeingPlayed) {
                printFriendlyGrid(player);

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

        printFriendlyGrid(player);
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

        printFriendlyGrid(player);
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

        printFriendlyGrid(player);
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

        printFriendlyGrid(player);
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

        printFriendlyGrid(player);
    }

    //MODIFIES: enemyPlayer (a Grid)
    //EFFECTS: runs the player's turn; they input the coordinate of their shot, and then it shoots at the enemy grid
    private void yourTurn(Grid playerWithTurn, Grid enemyPlayer) {
        startTurn();

        printGrids(playerWithTurn, enemyPlayer);
        System.out.println("Where would you like to shoot?");
        String coordinate = userInput.nextLine();
        coordinate = coordinate.toUpperCase();

        int row = getRow(coordinate);
        int column = getColumn(coordinate);
        int sunkBefore = enemyPlayer.sunkBoatCheck();

        enemyPlayer.shootAtGrid(row, column);

        int sunkAfter = enemyPlayer.sunkBoatCheck();

        printGrids(playerWithTurn, enemyPlayer);

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

    //MODIFIES: this
    //EFFECTS: confirms that the player wants to start their turn, or offers the option to quit
    private void startTurn() {
        System.out.println("If it isn't your turn, look away!");
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
        System.out.println("Would you like to play again? (Y -> Yes , N -> No)");
        String againAnswer = userInput.nextLine();
        againAnswer = againAnswer.toUpperCase();
        System.out.println();

        if (againAnswer.equals("Y")) {
            System.out.println("Ok, restarting!");
            player1 = new Grid();
            player2 = new Grid();
            currTurn = 1;
            shipBattleHasEnded = false;
            gameIsBeingPlayed = true;
        } else {
            gameIsBeingPlayed = false;
            System.out.println("Goodbye now.");
        }
    }

    //EFFECTS: prints both the player and opponent grids for a regular turn
    public void printGrids(Grid playerGrid, Grid opponentGrid) {
        printFriendlyGrid(playerGrid);
        printEnemyGrid(opponentGrid);
    }

    //EFFECTS: prints out the player's grid on their turn
    public void printFriendlyGrid(Grid myGrid) {
        System.out.println("-------Your Grid-------");
        System.out.println("# | 0 1 2 3 4 5 6 7 8 9");
        System.out.println("-----------------------");
        for (int i = 0; i < 10; i++) {
            int rowNum = i + 65;
            char rowLetter = (char) rowNum;
            System.out.print(rowLetter + " |");
            for (int j = 0; j < 10; j++) {
                int type = myGrid.getBoatTypeOnSquare(i, j);
                int state = myGrid.getCoordinateState(i,j);

                System.out.print(printState(type, state));
            }
            System.out.println();
        }
    }

    //EFFECTS: prints the grid for the opponent's grid on a player's turn
    public void printEnemyGrid(Grid theirGrid) {
        System.out.println();
        System.out.println("---Your Enemy's Grid---");
        System.out.println("# | 0 1 2 3 4 5 6 7 8 9");
        System.out.println("-----------------------");
        for (int i = 0; i < 10; i++) {
            int rowNum = i + 65;
            char rowLetter = (char) rowNum;
            System.out.print(rowLetter + " |");
            for (int j = 0; j < 10; j++) {
                if (theirGrid.getCoordinateState(i, j) == 111) {
                    System.out.print(" X");
                } else if (theirGrid.getCoordinateState(i, j) == 222) {
                    System.out.print(" x");
                } else if (theirGrid.getCoordinateState(i, j) == 110) {
                    System.out.print(" o");
                } else {
                    System.out.print(" -");
                }
            }
            System.out.println();
        }
    }

    //EFFECTS: returns the graphics for the grid icon based on the state of the swuare and the boat on it
    private String printState(int type, int state) {
        if (type == 1) {
            return patrolIcon(state);
        } else if (type == 2) {
            return submarineIcon(state);
        } else if (type == 3) {
            return destroyerIcon(state);
        } else if (type == 4) {
            return battleshipIcon(state);
        } else if (type == 5) {
            return aircraftCarrierIcon(state);
        } else {
            return emptySquareIcon(state);
        }
    }

    //EFFECTS: produces the graphics icon for a square with a patrol boat on it
    private String patrolIcon(int state) {
        if (state == 101) {
            return " P";
        } else {
            return " p";
        }
    }

    //EFFECTS: produces the graphics icon for a square with a submarine on it
    private String submarineIcon(int state) {
        if (state == 101) {
            return " S";
        } else {
            return " s";
        }
    }

    //EFFECTS: produces the graphics icon for a square with a destroyer on it
    private String destroyerIcon(int state) {
        if (state == 101) {
            return " D";
        } else {
            return " d";
        }
    }

    //EFFECTS: produces the graphics icon for a square with a battleship on it
    private String battleshipIcon(int state) {
        if (state == 101) {
            return " B";
        } else {
            return " b";
        }
    }

    //EFFECTS: produces the graphics icon for a square with an aircraft carrier on it
    private String aircraftCarrierIcon(int state) {
        if (state == 101) {
            return " A";
        } else {
            return " a";
        }
    }

    //EFFECTS: produces the graphics icon for an empty square
    private String emptySquareIcon(int state) {
        if (state == 100) {
            return " -";
        } else {
            return " o";
        }
    }
}
