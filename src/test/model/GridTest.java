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
        testGrid.makeDestroyerBoat("A1", 'S');
        testGrid.makeBattleshipBoat("E0", 'N');
        testGrid.makeAircraftCarrierBoat("J9", 'N');
    }

    @Test
    void testMakeGrid() {
        testGrid = new Grid();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(GridSquare.EMPTY_SQUARE, testGrid.getCoordinateState(i, j));
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
        assertEquals(GridSquare.MISSED_SQUARE, testGrid.getCoordinateState(0,0));

        testGrid.shootAtGrid(2,6);
        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE, testGrid.getCoordinateState(2,6));
        assertEquals(1, testGrid.getPatrolHits());

        testGrid.shootAtGrid(4,4);
        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE, testGrid.getCoordinateState(4,4));
        assertEquals(1, testGrid.getSubmarineHits());

        testGrid.shootAtGrid(1,1);
        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE, testGrid.getCoordinateState(1,1));
        assertEquals(1, testGrid.getDestroyerHits());

        testGrid.shootAtGrid(2,0);
        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE, testGrid.getCoordinateState(2,0));
        assertEquals(1, testGrid.getBattleshipHits());

        testGrid.shootAtGrid(5,9);
        testGrid.shootAtGrid(9,9);
        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE, testGrid.getCoordinateState(5,9));
        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE, testGrid.getCoordinateState(9,9));
        assertEquals(2, testGrid.getAircraftCarrierHits());
    }

    @Test
    void testSinking() {
        assertEquals(0, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(2,7);
        testGrid.shootAtGrid(2,6);

        assertEquals(1, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(4,4);
        testGrid.shootAtGrid(4,5);
        testGrid.shootAtGrid(4,6);

        assertEquals(2, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(0,1);
        testGrid.shootAtGrid(1,1);
        testGrid.shootAtGrid(2,1);

        assertEquals(3, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(4,0);
        testGrid.shootAtGrid(3,0);
        testGrid.shootAtGrid(2,0);
        testGrid.shootAtGrid(1,0);

        assertEquals(4, testGrid.sunkBoatCheck());

        testGrid.shootAtGrid(9,9);
        testGrid.shootAtGrid(8,9);
        testGrid.shootAtGrid(7,9);
        testGrid.shootAtGrid(6,9);
        testGrid.shootAtGrid(5,9);

        assertEquals(5, testGrid.sunkBoatCheck());
    }

    @Test
    void testGetBoatRef() {
        assertEquals(0, testGrid.getBoatTypeOnSquare(0, 0));
        assertEquals(1, testGrid.getBoatTypeOnSquare(2, 7));
        assertEquals(2, testGrid.getBoatTypeOnSquare(4, 4));
        assertEquals(3, testGrid.getBoatTypeOnSquare(1, 1));
        assertEquals(4, testGrid.getBoatTypeOnSquare(3, 0));
        assertEquals(5, testGrid.getBoatTypeOnSquare(5, 9));
    }

    @Test
    void testIsEqualToSameGrids() {
        assertTrue(testGrid.isEqualTo(testGrid));
    }

    @Test
    void testNotEqualGridSquares() {
        Grid badGrid = new Grid();

        badGrid.makePatrolBoat("C7", 'W');
        badGrid.makeSubmarineBoat("E4", 'E');
        badGrid.makeDestroyerBoat("A1", 'S');
        badGrid.makeBattleshipBoat("E0", 'N');
        badGrid.makeAircraftCarrierBoat("J9", 'N');

        assertTrue(testGrid.isEqualTo(testGrid));

        badGrid.getBattleGrid()[0][0].setSquareState(GridSquare.HIT_BOAT_ON_SQUARE);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.getBattleGrid()[0][0].setSquareState(GridSquare.EMPTY_SQUARE);

        badGrid.getBattleGrid()[0][0].setBoatRef(6);
        assertFalse(badGrid.isEqualTo(testGrid));
    }

    @Test
    void notEqualBecauseBoatHits() {
        Grid badGrid = new Grid();

        badGrid.makePatrolBoat("C7", 'W');
        badGrid.makeSubmarineBoat("E4", 'E');
        badGrid.makeDestroyerBoat("A1", 'S');
        badGrid.makeBattleshipBoat("E0", 'N');
        badGrid.makeAircraftCarrierBoat("J9", 'N');

        assertTrue(badGrid.isEqualTo(testGrid));

        badGrid.setPatrolHits(1);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setPatrolHits(0);

        badGrid.setSubmarineHits(2);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setSubmarineHits(0);

        badGrid.setDestroyerHits(3);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setDestroyerHits(0);

        badGrid.setBattleshipHits(4);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setBattleshipHits(0);

        badGrid.setAircraftCarrierHits(5);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setAircraftCarrierHits(0);
    }

    @Test
    void testBoatNotEquals() {
        Grid badGrid = new Grid();

        badGrid.makePatrolBoat("C7", 'W');
        badGrid.makeSubmarineBoat("E4", 'E');
        badGrid.makeDestroyerBoat("A1", 'S');
        badGrid.makeBattleshipBoat("E0", 'N');
        badGrid.makeAircraftCarrierBoat("J9", 'N');

        Boat goodPB = new Boat(1,"C7",'W');
        Boat goodSB = new Boat(2,"E4",'E');
        Boat goodDB = new Boat(3,"A1",'S');
        Boat goodBB = new Boat(4,"E0",'N');
        Boat goodAB = new Boat(5,"J9",'N');

        Boat badPB = new Boat(1,"A9",'S');
        Boat badSB = new Boat(2,"B8",'S');
        Boat badDB = new Boat(3,"A9",'S');
        Boat badBB = new Boat(4,"B8",'S');
        Boat badAB = new Boat(5,"A9",'S');

        assertTrue(badGrid.isEqualTo(testGrid));

        badGrid.setPatrolBoat(badPB);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setPatrolBoat(goodPB);

        badGrid.setSubmarineBoat(badSB);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setSubmarineBoat(goodSB);

        badGrid.setDestroyerBoat(badDB);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setDestroyerBoat(goodDB);

        badGrid.setBattleshipBoat(badBB);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setBattleshipBoat(goodBB);

        badGrid.setAircraftCarrierBoat(badAB);
        assertFalse(badGrid.isEqualTo(testGrid));
        badGrid.setAircraftCarrierBoat(goodAB);
    }
}
