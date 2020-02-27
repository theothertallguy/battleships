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
    void testIsEquals() {
        Boat boat1 = new Boat(1,"A0",'N');
        Boat boat2 = new Boat(2,"A0",'N');
        Boat boat3 = new Boat(1,"A7",'N');
        Boat boat4 = new Boat(1,"A0",'E');
        Boat boat5 = new Boat(1,"A7",'E');
        Boat boat6 = new Boat(2,"A0",'E');
        Boat boat7 = new Boat(2,"A7",'N');
        Boat boat8 = new Boat(2,"A7",'E');

        assertFalse(boat1.isEqualTo(boat2));
        assertFalse(boat1.isEqualTo(boat3));
        assertFalse(boat1.isEqualTo(boat4));
        assertFalse(boat1.isEqualTo(boat5));
        assertFalse(boat1.isEqualTo(boat6));
        assertFalse(boat1.isEqualTo(boat7));
        assertFalse(boat1.isEqualTo(boat8));

        Boat testDestroyer = new Boat(myDB.getBoatType(),myDB.getInputCoordinate(),myDB.getInputDirection());

        assertTrue(myDB.isEqualTo(testDestroyer));
    }
}