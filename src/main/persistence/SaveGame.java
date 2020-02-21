package persistence;

import model.Grid;

import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {
    private static final String PLAYER_ONE_SAVE = "./data/playerOneGameSave.txt";
    private static final String PLAYER_TWO_SAVE = "./data/playerTwoGameSave.txt";

    public SaveGame(Grid player1, Grid player2, int currTurn) {
        saveFile(player1, currTurn, 1, PLAYER_ONE_SAVE);
        saveFile(player2, currTurn, 1, PLAYER_TWO_SAVE);
    }

    private void saveFile(Grid player, int turn, int playerID, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(gridText(player));
            writer.write(boatText(player));
            writer.write(statsText(player, turn, playerID));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String gridText(Grid player) {
        String fullText = "";
        for (int i = 0; i < 10; i++) {
            String rowText = "";
            for (int j = 0; j < 10; j++) {
                int state = player.getCoordinateState(i,j);
                int boat = player.getBoatTypeOnSquare(i,j);
                rowText = rowText + state + "-" + boat + ",";
            }
            rowText += "\r\n";
            fullText += rowText;
        }
        return fullText;
    }

    private String boatText(Grid player) {
        String fullText = "";
        fullText = fullText + patrolText(player) + "\r\n";
        fullText = fullText + submarineText(player) + "\r\n";
        fullText = fullText + destroyerText(player) + "\r\n";
        fullText = fullText + battleshipText(player) + "\r\n";
        fullText = fullText + aircraftCarrierText(player) + "\r\n";
        return fullText;
    }

    private String patrolText(Grid player) {
        String fullText = "";
        String coordinate = player.getPatrolBoat().getInputCoordinate();
        String direction = String.valueOf(player.getPatrolBoat().getInputDirection());
        fullText = fullText + 1 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    private String submarineText(Grid player) {
        String fullText = "";
        String coordinate = player.getSubmarineBoat().getInputCoordinate();
        String direction = String.valueOf(player.getSubmarineBoat().getInputDirection());
        fullText = fullText + 2 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    private String destroyerText(Grid player) {
        String fullText = "";
        String coordinate = player.getDestroyerBoat().getInputCoordinate();
        String direction = String.valueOf(player.getDestroyerBoat().getInputDirection());
        fullText = fullText + 3 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    private String battleshipText(Grid player) {
        String fullText = "";
        String coordinate = player.getBattleshipBoat().getInputCoordinate();
        String direction = String.valueOf(player.getBattleshipBoat().getInputDirection());
        fullText = fullText + 4 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    private String aircraftCarrierText(Grid player) {
        String fullText = "";
        String coordinate = player.getAircraftCarrierBoat().getInputCoordinate();
        String direction = String.valueOf(player.getAircraftCarrierBoat().getInputDirection());
        fullText = fullText + 5 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    private String statsText(Grid player, int turn, int playerID) {
        String stateText = "";
        int patrolHits = player.getPatrolHits();
        int submarineHits = player.getSubmarineHits();
        int destroyerHits = player.getDestroyerHits();
        int battleshipHits = player.getBattleshipHits();
        int aircraftCarrierHits = player.getAircraftCarrierHits();

        stateText = stateText + patrolHits + "," + submarineHits + ",";
        stateText = stateText + destroyerHits + "," + battleshipHits + ",";
        stateText = stateText + aircraftCarrierHits + "," + "\r\n";

        if (turn == playerID) {
            stateText += "T,";
        } else {
            stateText += "F,";
        }

        return stateText;
    }
}
