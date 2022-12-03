package starb.puzzle;

public class Coordinate {

    private int x;
    private int y;

    /**
     * Constructor initializes the x and y coordinates.
     * @param x
     * @param y
     */
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Method returns the x-coordinate
     * @return x-coordinate which must be an integer
     */
    public int getX() {
        return x;
    }

    /**
     * Method returns the y-coordinate
     * @return y-coordinate which must be an integer
     */
    public int getY() {
        return y;
    }

    /**
     * Method returns a String version of the coordinates.
     * Used for testing. 
     */
    public String toString() {
        return x + "," + y;
    }

    /**
     * Compares the coordinates. This method is used to compare
     * user client game state and the solution to the puzzle. 
     * @param c coordinate to compare
     * @return true if the coordinates are the same
     */
    public boolean compareCoord(Coordinate c) {
        if (this.x == c.getX() && this.y == c.getY()) {
            return true;
        }
        return false;
    }

}


