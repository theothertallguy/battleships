package persistence;

import model.Boat;
import model.Grid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadGame {
    private static final String PLAYER_ONE_SAVE_FILE = "./data/playerOneSaveTest.txt";
    private static final String PLAYER_TWO_SAVE_FILE = "./data/playerTwoSaveTest.txt";

    Grid player1 = new Grid();
    Grid player2 = new Grid();

    int turn;

    public LoadGame() {
        loadPlayer(player1, 1, PLAYER_ONE_SAVE_FILE);
        loadPlayer(player2, 2, PLAYER_TWO_SAVE_FILE);

    }

    private void loadPlayer(Grid player, int id, String file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            for (int i = 0; i < 10; i++) {
                line = bufferedReader.readLine();
                setRow(player, i, line);
            }

            for (int i = 0; i < 5; i++) {
                placeBoat(player, bufferedReader.readLine());
            }

            setHits(player, bufferedReader.readLine());
            setTurn(id, bufferedReader.readLine());

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void placeBoat(Grid player, String boatInfo) {
        int boatType = boatInfo.charAt(0);
        boatType = boatType - 48;
        String coordinate = boatInfo.substring(2,4);
        char direction = boatInfo.charAt(5);
        switch (boatType) {
            case 1:
                player.setPatrolBoat(new Boat(boatType, coordinate, direction));
                break;
            case 2:
                player.setSubmarineBoat(new Boat(boatType, coordinate, direction));
                break;
            case 3:
                player.setDestroyerBoat(new Boat(boatType, coordinate, direction));
                break;
            case 4:
                player.setBattleshipBoat(new Boat(boatType, coordinate, direction));
                break;
            case 5:
                player.setAircraftCarrierBoat(new Boat(boatType, coordinate, direction));
                break;
        }
    }

    private void setRow(Grid player, int i, String rowText) {
        for (int j = 0; j < 10; j++) {
            int subStart = 0 + (j * 6);
            int subEnd = 3 + (j * 6);
            int state = Integer.parseInt(rowText.substring(subStart, subEnd));
            int boatChar = 4 + (j * 6);
            int boat = rowText.charAt(boatChar);
            boat = boat - 48;
            player.getBattleGrid()[i][j].setSquareState(state);
            player.getBattleGrid()[i][j].setBoatRef(boat);
        }
    }

    private void setHits(Grid player, String hitsInfo) {
        int patrolHits = hitsInfo.charAt(0);
        patrolHits = patrolHits - 48;
        player.setPatrolHits(patrolHits);

        int submarineHits = hitsInfo.charAt(2);
        submarineHits = submarineHits - 48;
        player.setSubmarineHits(submarineHits);

        int destroyerHits = hitsInfo.charAt(4);
        destroyerHits = destroyerHits - 48;
        player.setDestroyerHits(destroyerHits);

        int battleshipHits = hitsInfo.charAt(6);
        battleshipHits = battleshipHits - 48;
        player.setBattleshipHits(battleshipHits);

        int aircraftCarrierHits = hitsInfo.charAt(8);
        aircraftCarrierHits = aircraftCarrierHits - 48;
        player.setAircraftCarrierHits(aircraftCarrierHits);
    }

    private void setTurn(int id, String isTurn) {
        if (isTurn.equals("T,")) {
            turn = id;
        }
    }

    public Grid getPlayerOne() {
        return player1;
    }

    public Grid getPlayerTwo() {
        return player2;
    }

    public int getTurn() {
        return turn;
    }
}
