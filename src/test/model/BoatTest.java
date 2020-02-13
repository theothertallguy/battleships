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
        assertEquals(0, myPB.getRowChange());
        assertEquals(-1, myPB.getColumnChange());

        assertEquals(0, mySB.getRowChange());
        assertEquals(1, mySB.getColumnChange());

        assertEquals(1, myDB.getRowChange());
        assertEquals(0, myDB.getColumnChange());

        assertEquals(-1, myBB.getRowChange());
        assertEquals(0, myBB.getColumnChange());

        assertEquals(-1, myACB.getRowChange());
        assertEquals(0, myACB.getColumnChange());
    }

    @Test
    void testCoordinates() {
        assertEquals(2, myPB.getRow());
        assertEquals(7, myPB.getColumn());

        assertEquals(4, mySB.getRow());
        assertEquals(4, mySB.getColumn());

        assertEquals(1, myDB.getRow());
        assertEquals(0, myDB.getColumn());

        assertEquals(0, myBB.getRow());
        assertEquals(4, myBB.getColumn());

        assertEquals(9, myACB.getRow());
        assertEquals(9, myACB.getColumn());
    }

    @Test
    void testBoatID() {
        assertEquals(1, myPB.getBoatType());
        assertEquals(2, mySB.getBoatType());
        assertEquals(3, myDB.getBoatType());
        assertEquals(4, myBB.getBoatType());
        assertEquals(5, myACB.getBoatType());
    }

    @Test
    void testSwitchBranchPass() {
        Boat weirdBoat = new Boat(6, "A6", 'U');

        assertEquals(0, weirdBoat.getRowChange());
        assertEquals(0, weirdBoat.getColumnChange());

        assertEquals(0, weirdBoat.getBoatLength());

        assertEquals(6, weirdBoat.getBoatType());
    }
}