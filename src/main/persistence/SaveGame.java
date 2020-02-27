package persistence;

import model.Grid;

import java.io.FileWriter;
import java.io.IOException;


/*
* Saves the player's game data in a file for their save.
* Each grid square is saved as:
* squareState-boatRef,
* Boats are saved as:
* boatID,inputCoordinate,inputDirection
* Hits are saved as:
* patrolHits,submarineHits,destroyerHits,battleshipHits,aircraftCarrierHits
* Value denoting player turn is either T or F for true or false
* */

public class SaveGame {

    public SaveGame() {

    }

    //REQUIRES: turn = 1 or 2, playerID = 1 or 2, player is a valid Grid with no null fields
    // filePath is a valid file which can be accessed and written to
    //EFFECTS: saves the text for the player's game save to the file in filePath
    public void saveFile(Grid player, int turn, int playerID, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        writer.write(gridText(player));
        writer.write(boatText(player));
        writer.write(statsText(player, turn, playerID));
        writer.close();
    }

    //REQUIRES: player is a valid Grid with no null fields
    //EFFECTS: produces the save text for the Grid
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

    //REQUIRES: player is a valid Grid with no null fields
    //EFFECTS: produces the save text for all 5 boats, concatenated together
    private String boatText(Grid player) {
        String fullText = "";
        fullText = fullText + patrolText(player) + "\r\n";
        fullText = fullText + submarineText(player) + "\r\n";
        fullText = fullText + destroyerText(player) + "\r\n";
        fullText = fullText + battleshipText(player) + "\r\n";
        fullText = fullText + aircraftCarrierText(player) + "\r\n";
        return fullText;
    }

    //REQUIRES: player is a valid Grid with no null fields
    //EFFECTS: produces the save text for the patrol boat
    private String patrolText(Grid player) {
        String fullText = "";
        String coordinate = player.getPatrolBoat().getInputCoordinate();
        String direction = String.valueOf(player.getPatrolBoat().getInputDirection());
        fullText = fullText + 1 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    //REQUIRES: player is a valid Grid with no null fields
    //EFFECTS: produces the save text for the submarine
    private String submarineText(Grid player) {
        String fullText = "";
        String coordinate = player.getSubmarineBoat().getInputCoordinate();
        String direction = String.valueOf(player.getSubmarineBoat().getInputDirection());
        fullText = fullText + 2 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    //REQUIRES: player is a valid Grid with no null fields
    //EFFECTS: produces the save text for the destroyer
    private String destroyerText(Grid player) {
        String fullText = "";
        String coordinate = player.getDestroyerBoat().getInputCoordinate();
        String direction = String.valueOf(player.getDestroyerBoat().getInputDirection());
        fullText = fullText + 3 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    //REQUIRES: player is a valid Grid with no null fields
    //EFFECTS: produces the save text for the battleship
    private String battleshipText(Grid player) {
        String fullText = "";
        String coordinate = player.getBattleshipBoat().getInputCoordinate();
        String direction = String.valueOf(player.getBattleshipBoat().getInputDirection());
        fullText = fullText + 4 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    //REQUIRES: player is a valid Grid with no null fields
    //EFFECTS: produces the save text for the aircraft carrier
    private String aircraftCarrierText(Grid player) {
        String fullText = "";
        String coordinate = player.getAircraftCarrierBoat().getInputCoordinate();
        String direction = String.valueOf(player.getAircraftCarrierBoat().getInputDirection());
        fullText = fullText + 5 + "," + coordinate + "," + direction + ",";
        return fullText;
    }

    //REQUIRES: turn = 1 or 2, playerID = 1 or 2, player is a valid Grid with no null fields
    //EFFECTS: produces the save text for the number of boat hits, and the current turn
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
