package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoatTest {
    private Boat myPB;
    private Boat mySB;
    private Boat myDB;
    private Boat myBB;
    private Boat myACB;

    @BeforeEach
    void runBefore() {
        myPB = new Boat(1, "C7", 'W');
        mySB = new Boat(2, "E4", 'E');
        myDB = new Boat(3, "B0", 'S');
        myBB = new Boat(4, "A4", 'N');
        myACB = new Boat(5, "J9", 'N');
    }

    @Test
    void testBoatLength() {
        assertEquals(2, myPB.getBoatLength());
        assertEquals(3, mySB.getBoatLength());
        assertEquals(3, myDB.getBoatLength());
        assertEquals(4, myBB.getBoatLength());
        assertEquals(5, myACB.getBoatLength());
    }

    @Test
    void testDirection() {
        assertEquals(-1, myPB.getXDir());
        assertEquals(0, myPB.getYDir());

        assertEquals(1, mySB.getXDir());
        assertEquals(0, mySB.getYDir());

        assertEquals(0, myDB.getXDir());
        assertEquals(1, myDB.getYDir());

        assertEquals(0, myBB.getXDir());
        assertEquals(-1, myBB.getYDir());

        assertEquals(0, myACB.getXDir());
        assertEquals(-1, myACB.getYDir());
    }

    @Test
    void testCoordinates() {
        assertEquals(2, myPB.getX());
        assertEquals(7, myPB.getY());

        assertEquals(4, mySB.getX());
        assertEquals(4, mySB.getY());

        assertEquals(1, myDB.getX());
        assertEquals(0, myDB.getY());

        assertEquals(0, myBB.getX());
        assertEquals(4, myBB.getY());

        assertEquals(9, myACB.getX());
        assertEquals(9, myACB.getY());
    }

    @Test
    void testBoatID() {
        assertEquals(1, myPB.getBoatType());
        assertEquals(2, mySB.getBoatType());
        assertEquals(3, myDB.getBoatType());
        assertEquals(4, myBB.getBoatType());
        assertEquals(5, myACB.getBoatType());
    }
}