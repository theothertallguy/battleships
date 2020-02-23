package persistence;

import model.Boat;
import model.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LoadGameTest {
    private static final String PLAYER_ONE_SAVE_FILE = "./data/playerOneLoadingTest.txt";
    private static final String PLAYER_TWO_SAVE_FILE = "./data/playerTwoLoadingTest.txt";

    LoadGame lg;
    Grid player1 = new Grid();
    Grid player2 = new Grid();
    int turn;

    @BeforeEach
    void runBefore() {
        lg = new LoadGame();
        player1 = lg.loadPlayer(player1, 1, PLAYER_ONE_SAVE_FILE);
        player2 = lg.loadPlayer(player2, 2, PLAYER_TWO_SAVE_FILE);
        turn = lg.getTurn();
    }

    @Test
    void testPlayerTurn() {
        assertEquals(2,turn);
    }

    @Test
    void testPlayer1Load() {
        Grid testGrid = new Grid();

        testGrid.setPatrolBoat(new Boat(1,"A7",'E'));
        testGrid.setSubmarineBoat(new Boat(2,"C6",'S'));
        testGrid.setDestroyerBoat(new Boat(3,"E2",'S'));
        testGrid.setBattleshipBoat(new Boat(4,"I4",'W'));
        testGrid.setAircraftCarrierBoat(new Boat(5,"J9",'N'));

        testGrid.makePatrolBoat("A7",'E');
        testGrid.makeSubmarineBoat("C6",'S');
        testGrid.makeDestroyerBoat("E2",'S');
        testGrid.makeBattleshipBoat("I4",'W');
        testGrid.makeAircraftCarrierBoat("J9",'N');

        testGrid.shootAtGrid(0,7);
        testGrid.shootAtGrid(0,8);
        testGrid.shootAtGrid(1,5);
        testGrid.shootAtGrid(1,7);
        testGrid.shootAtGrid(2,6);
        testGrid.shootAtGrid(4,2);
        testGrid.shootAtGrid(6,2);
        testGrid.shootAtGrid(7,9);
        testGrid.shootAtGrid(9,3);
        testGrid.shootAtGrid(9,9);

        testGrid.sunkBoatCheck();

        assertTrue(testGrid.isEqualTo(player1));
    }

    @Test
    void testPlayer2Load() {
        Grid testGrid = new Grid();

        testGrid.setPatrolBoat(new Boat(1,"B2",'N'));
        testGrid.setSubmarineBoat(new Boat(2,"D3",'S'));
        testGrid.setDestroyerBoat(new Boat(3,"F5",'E'));
        testGrid.setBattleshipBoat(new Boat(4,"H7",'W'));
        testGrid.setAircraftCarrierBoat(new Boat(5,"J1",'N'));

        testGrid.makePatrolBoat("B2",'N');
        testGrid.makeSubmarineBoat("D3",'S');
        testGrid.makeDestroyerBoat("F5",'E');
        testGrid.makeBattleshipBoat("H7",'W');
        testGrid.makeAircraftCarrierBoat("J1",'N');

        testGrid.shootAtGrid(0,2);
        testGrid.shootAtGrid(1,6);
        testGrid.shootAtGrid(2,7);
        testGrid.shootAtGrid(3,8);
        testGrid.shootAtGrid(4,3);
        testGrid.shootAtGrid(5,5);
        testGrid.shootAtGrid(7,4);
        testGrid.shootAtGrid(7,5);
        testGrid.shootAtGrid(7,6);
        testGrid.shootAtGrid(7,7);

        testGrid.sunkBoatCheck();

        assertTrue(testGrid.isEqualTo(player2));
    }
}
