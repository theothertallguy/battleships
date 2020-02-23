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
                assertEquals(100, testGrid.getBattleGrid()[i][j].getSquareState());
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

        testGrid.shootAtGrid(2,6);
        assertEquals(111, testGrid.getCoordinateState(2,6));
        assertEquals(1, testGrid.getPatrolHits());

        testGrid.shootAtGrid(4,4);
        assertEquals(111, testGrid.getCoordinateState(4,4));
        assertEquals(1, testGrid.getSubmarineHits());

        testGrid.shootAtGrid(1,1);
        assertEquals(111, testGrid.getCoordinateState(1,1));
        assertEquals(1, testGrid.getDestroyerHits());

        testGrid.shootAtGrid(2,0);
        assertEquals(111, testGrid.getCoordinateState(2,0));
        assertEquals(1, testGrid.getBattleshipHits());

        testGrid.shootAtGrid(5,9);
        testGrid.shootAtGrid(9,9);
        assertEquals(111, testGrid.getCoordinateState(5,9));
        assertEquals(111, testGrid.getCoordinateState(9,9));
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
    void testSwitchBranchPass() {
        Boat weirdBoat = new Boat(6, "A8", 'U');

        testGrid.placeBoatOnGrid(weirdBoat);

        testGrid.shootAtGrid(0,6);
        assertEquals(110, testGrid.getCoordinateState(0,6));
    }

    @Test
    void testIsEqualToBoatIDNotEqual() {
        Grid equalGrid = new Grid();
        equalGrid.makePatrolBoat("C7", 'W');
        equalGrid.makeSubmarineBoat("E4", 'E');
        equalGrid.makeDestroyerBoat("A1", 'S');
        equalGrid.makeBattleshipBoat("E0", 'N');
        equalGrid.makeAircraftCarrierBoat("J9", 'N');

        equalGrid.getBattleGrid()[0][0].setBoatRef(3);

        assertFalse(testGrid.isEqualTo(equalGrid));
    }

    @Test
    void testIsEqualToSquareStateNotEqual() {
        Grid equalGrid = new Grid();
        equalGrid.makePatrolBoat("C7", 'W');
        equalGrid.makeSubmarineBoat("E4", 'E');
        equalGrid.makeDestroyerBoat("A1", 'S');
        equalGrid.makeBattleshipBoat("E0", 'N');
        equalGrid.makeAircraftCarrierBoat("J9", 'N');

        equalGrid.shootAtGrid(2,2);

        assertFalse(testGrid.isEqualTo(equalGrid));
    }

    @Test
    void testExtraSetters() {
        Grid equalGrid = new Grid();
        equalGrid.setPatrolBoat(testGrid.getPatrolBoat());
        equalGrid.setSubmarineBoat(testGrid.getSubmarineBoat());
        equalGrid.setDestroyerBoat(testGrid.getDestroyerBoat());
        equalGrid.setBattleshipBoat(testGrid.getBattleshipBoat());
        equalGrid.setAircraftCarrierBoat(testGrid.getAircraftCarrierBoat());

        assertTrue(equalGrid.getPatrolBoat().isEqualTo(testGrid.getPatrolBoat()));
        assertTrue(equalGrid.getSubmarineBoat().isEqualTo(testGrid.getSubmarineBoat()));
        assertTrue(equalGrid.getDestroyerBoat().isEqualTo(testGrid.getDestroyerBoat()));
        assertTrue(equalGrid.getBattleshipBoat().isEqualTo(testGrid.getBattleshipBoat()));
        assertTrue(equalGrid.getAircraftCarrierBoat().isEqualTo(testGrid.getAircraftCarrierBoat()));

        equalGrid.setPatrolHits(1);
        equalGrid.setSubmarineHits(1);
        equalGrid.setDestroyerHits(1);
        equalGrid.setBattleshipHits(1);
        equalGrid.setAircraftCarrierHits(1);

        assertEquals(1,equalGrid.getPatrolHits());
        assertEquals(1,equalGrid.getSubmarineHits());
        assertEquals(1,equalGrid.getDestroyerHits());
        assertEquals(1,equalGrid.getBattleshipHits());
        assertEquals(1,equalGrid.getAircraftCarrierHits());
    }
}
