package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridSquareTest {
    private GridSquare A1;

    @BeforeEach
    void runBefore() {
        A1 = new GridSquare();
    }

    @Test
    void testConstructor() {
        assertEquals(GridSquare.EMPTY_SQUARE, A1.getSquareState());
    }

    @Test
    void testShootAtEmptySquare() {
        A1.fireAtSquare();

        assertEquals(GridSquare.MISSED_SQUARE, A1.getSquareState());
    }

    @Test
    void testShootAtBoat() {
        A1.addBoat(1);

        assertEquals(GridSquare.BOAT_ON_SQUARE, A1.getSquareState());
        assertEquals(1, A1.getBoatRef());

        A1.fireAtSquare();

        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE, A1.getSquareState());
    }

    @Test
    void testSinkBoat() {
        A1.addBoat(1);
        A1.setBoatAsSunk();

        assertEquals(GridSquare.SUNK_BOAT_ON_SQUARE, A1.getSquareState());
    }

    @Test
    void testSettingMethods() {
        assertEquals(GridSquare.EMPTY_SQUARE,A1.getSquareState());
        assertEquals(0,A1.getBoatRef());

        A1.setSquareState(GridSquare.HIT_BOAT_ON_SQUARE);
        A1.setBoatRef(3);

        assertEquals(GridSquare.HIT_BOAT_ON_SQUARE,A1.getSquareState());
        assertEquals(3,A1.getBoatRef());
    }
}
