package persistence;

import model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SaveGameTest {
    private static final String PLAYER_ONE_SAVE_FILE = "./data/playerOneSavingTest";
    private static final String PLAYER_TWO_SAVE_FILE = "./data/playerTwoSavingTest";
    private static final String PLAYER_ONE_LOAD_FILE = "./data/playerOneLoadingTest.txt";
    private static final String PLAYER_TWO_LOAD_FILE = "./data/playerTwoLoadingTest.txt";

    private Grid player1 = new Grid();
    private Grid player2 = new Grid();
    private int turn;

    @BeforeEach
    void runBefore() {
        LoadGame myLoadGame = new LoadGame();
        try {
            player1 = myLoadGame.loadPlayer(player1,1,PLAYER_ONE_LOAD_FILE);
            player2 = myLoadGame.loadPlayer(player2,2,PLAYER_TWO_LOAD_FILE);
            SaveGame mySaveGame = new SaveGame();
            mySaveGame.saveFile(player1,turn,1,PLAYER_ONE_SAVE_FILE);
            mySaveGame.saveFile(player2,turn,2,PLAYER_TWO_SAVE_FILE);
            turn = myLoadGame.getTurn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPlayerOneSave() throws IOException {
        FileReader readerSave = new FileReader(PLAYER_ONE_SAVE_FILE);
        BufferedReader bufferedReaderSave = new BufferedReader(readerSave);
        FileReader readerLoad = new FileReader(PLAYER_ONE_LOAD_FILE);
        BufferedReader bufferedReaderLoad = new BufferedReader(readerLoad);

        String saveLine;
        String loadLine;

        while ((saveLine = bufferedReaderSave.readLine()) != null
                && (loadLine = bufferedReaderLoad.readLine()) != null) {
            assertTrue(saveLine.equals(loadLine));
        }

        readerSave.close();
        readerLoad.close();
    }

    @Test
    void testPlayerTwoSave() throws IOException {
        FileReader readerSave = new FileReader(PLAYER_TWO_SAVE_FILE);
        BufferedReader bufferedReaderSave = new BufferedReader(readerSave);
        FileReader readerLoad = new FileReader(PLAYER_TWO_LOAD_FILE);
        BufferedReader bufferedReaderLoad = new BufferedReader(readerLoad);

        String saveLine;
        String loadLine;

        while ((saveLine = bufferedReaderSave.readLine()) != null
                && (loadLine = bufferedReaderLoad.readLine()) != null) {
            assertTrue(saveLine.equals(loadLine));
        }

        readerSave.close();
        readerLoad.close();
    }
    @Test
    public void testIOExceptionExpected() {
        SaveGame exceptionGame = new SaveGame();
        try {
            exceptionGame.saveFile(player1, 1,1, "cats");
            fail("Uncaught exception!");
        } catch (IOException e) {
            //expected
        }
    }

}
