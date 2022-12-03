package tests;

import org.junit.jupiter.api.Test;
import starb.client.ClientGameState;
import starb.puzzle.Coordinate;
import starb.puzzle.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ClientGameStateTest {

    Parser p = new Parser();
    String puzzle1 = p.puzzleFileToString("puzzles/puzzle-1-1-1.txt");

    ClientGameState g = new ClientGameState(puzzle1);

    /**
     *ensures the method returns the proper value for a given space
     */
    @Test
    void getSquare1() {
        assertEquals(' ',g.getSquare(0,0));
    }

    /**
     * ensures that attempts to access a square outside the bounds will throw an exception
     */
    @Test // This same code is in all the set and get methods involving a single square
    void getSquareWrongDimension() {
        assertThrows(IllegalArgumentException.class,() ->{g.getSquare(1000,1000);});
    }

    /**
     * ensures a star is properly set to the game state at the given location
     */
    @Test
    void setStar() {
        g.setStar(0,0);
        assertEquals('*', g.getSquare(0,0));
    }

    /**
     * ensures a point is properly set to the game state at the given location
     */
    @Test
    void setPoint() {
        g.setPoint(0,0);
        assertEquals('.', g.getSquare(0,0));
    }

    /**
     * ensures a space is properly set to the game state at the given location
     */
    @Test
    void setSpace() {
        g.setSpace(0,0);
        assertEquals(' ', g.getSquare(0,0));
    }
    /*
    @Test
    void getBoard() {
    }

    @Test
    void getGameState() {
    }
     */

    /**
     * ensures that check win returns true when the puzzle has been solved
     */
    @Test
    void checkWin() {
        ArrayList<Coordinate> solution = p.stringToBoard(puzzle1).getSolution();

        for(int i = 0; i < solution.size(); i++){
            g.setStar(solution.get(i).getX(), solution.get(i).getY());
        }
        assertTrue(g.checkWin());
    }

    /**
     *ensures checkwin returns false when the number of stars is incorrect
     */
    @Test
    void checkWinFalseSize() {
        ArrayList<Coordinate> solution = p.stringToBoard(puzzle1).getSolution();

        for(int i = 0; i < solution.size()-1; i++){
            g.setStar(solution.get(i).getX(), solution.get(i).getY());
        }

        assertFalse(g.checkWin());
    }

    /**
     *ensures checkWin returns false when given an incorrect solution of the proper size
     */
    @Test
    void checkWinFalseCoordinate() {
        ArrayList<Coordinate> solution = p.stringToBoard(puzzle1).getSolution();

        for(int i = 0; i < solution.size()-1; i++){
            g.setStar(solution.get(i).getX(), solution.get(i).getY());
        }

        g.setStar(0,0);

        assertFalse(g.checkWin());
    }
}