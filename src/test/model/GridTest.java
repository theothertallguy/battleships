package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    private Grid testGrid;

    @BeforeEach
    void runBefore() {
        testGrid = new Grid();
        testGrid.makePatrolBoat("C7", 'W');
        testGrid.makeSubmarineBoat("E4", 'E');
        testGrid.makeDestroyerBoat("B0", 'S');
        testGrid.makeBattleshipBoat("A4", 'N');
        testGrid.makeAircraftCarrierBoat("J9", 'N');
    }

    @Test
    void testMakeGrid() {
        testGrid = new Grid();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(100, testGrid.getCoordinateState(i, j));
            }
        }
    }

    @Test
    void testPatrolBoat() {
        assertEquals(1, testGrid.getPatrolBoat().getBoatType());
        assertEquals(2, testGrid.getPatrolBoat().getBoatLength());
    }

    @Test
    void testSubmarineBoat() {
        assertEquals(2, testGrid.getSubmarineBoat().getBoatType());
        assertEquals(3, testGrid.getSubmarineBoat().getBoatLength());
    }

    @Test
    void testDestroyerBoat() {
        assertEquals(3, testGrid.getDestroyerBoat().getBoatType());
        assertEquals(3, testGrid.getDestroyerBoat().getBoatLength());
    }

    @Test
    void testBattleshipBoat() {
        assertEquals(4, testGrid.getBattleshipBoat().getBoatType());
        assertEquals(4, testGrid.getBattleshipBoat().getBoatLength());
    }

    @Test
    void testAircraftCarrierBoat() {
        assertEquals(5, testGrid.getAircraftCarrierBoat().getBoatType());
        assertEquals(5, testGrid.getAircraftCarrierBoat().getBoatLength());
    }

    @Test
    void testShootingAndBoatHitting() {
        testGrid.shootAtGrid(0,0);
        assertEquals(110, testGrid.getCoordinateState(0,0));

        testGrid.shootAtGrid(1,7);
        assertEquals(111, testGrid.getCoordinateState(1,7));
        assertEquals(1, testGrid.getPatrolHits());

        testGrid.shootAtGrid(4,4);
        assertEquals(111, testGrid.getCoordinateState(4,4));
        assertEquals(1, testGrid.getSubmarineHits());

        testGrid.shootAtGrid(1,0);
        assertEquals(111, testGrid.getCoordinateState(1,0));
        assertEquals(1, testGrid.getDestroyerHits());

        testGrid.shootAtGrid(0,2);
        assertEquals(111, testGrid.getCoordinateState(0,2));
        assertEquals(1, testGrid.getBattleshipHits());

        testGrid.shootAtGrid(9,5);
        testGrid.shootAtGrid(9,9);
        assertEquals(111, testGrid.getCoordinateState(9,5));
        assertEquals(111, testGrid.getCoordinateState(9,9));
        assertEquals(2, testGrid.getAircraftCarrierHits());
    }

    @Test
    void testSinking() {
        assertEquals(0, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(2,7);
        testGrid.shootAtGrid(1,7);

        assertEquals(1, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(4,4);
        testGrid.shootAtGrid(5,4);
        testGrid.shootAtGrid(6,4);

        assertEquals(2, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(1,0);
        testGrid.shootAtGrid(1,1);
        testGrid.shootAtGrid(1,2);

        assertEquals(3, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(0,4);
        testGrid.shootAtGrid(0,3);
        testGrid.shootAtGrid(0,2);
        testGrid.shootAtGrid(0,1);

        assertEquals(4, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(9,9);
        testGrid.shootAtGrid(9,8);
        testGrid.shootAtGrid(9,7);
        testGrid.shootAtGrid(9,6);
        testGrid.shootAtGrid(9,5);

        assertEquals(5, testGrid.sunkBoatCheck());
    }
}
