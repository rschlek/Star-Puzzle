package tests;

import org.junit.jupiter.api.Test;
import starb.puzzle.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    Coordinate c = new Coordinate(1,2);

    /**
     * ensures the toString method returns the x and y values in the format "X,Y"
     */
    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals("1,2", c.toString());
    }

    /**
     *ensures getX returns the proper x coordinate value
     */
    @Test
    void getX() {
        assertEquals(1,c.getX());
    }

    /**
     *ensures getY returns the proper y coordinate value
     */
    @Test
    void getY() {
        assertEquals(2,c.getY());
    }

    /**
     * ensures that a comparison of two coordinates will return true if the values are equivalent and false if they are different
     */
    @Test
    void testCompare(){
        Coordinate c2 = new Coordinate(1,2);
        Coordinate c3 = new Coordinate(5, 3);
        assertTrue(c.compareCoord(c2));
        assertFalse(c.compareCoord(c3));
    }
}