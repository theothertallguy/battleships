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
        assertEquals(100, A1.getSquareState());
    }

    @Test
    void testShootAtEmptySquare() {
        A1.fireAtSquare();

        assertEquals(110, A1.getSquareState());
    }

    @Test
    void testShootAtBoat() {
        A1.addBoat();

        assertEquals(101, A1.getSquareState());

        A1.fireAtSquare();

        assertEquals(111, A1.getSquareState());
    }

    @Test
    void testSinkBoat() {
        A1.setBoatAsSunk();

        assertEquals(222, A1.getSquareState());
    }
}
