package starb.puzzle;

import java.util.ArrayList;

public class PuzzleAdt {

    private int[][] board;
    private int numStars;
    private ArrayList<Coordinate> solution;

    /**
     * Constructor initializes the game board, number of stars, and the solution. 
     * @param board the int array that contains values determing the sections. 
     * @param numStars max number of stars per row, column, and section
     * @param solution the solution to the puzzle
     */
    public PuzzleAdt(int[][] board, int numStars, ArrayList<Coordinate> solution){
        this.board = board;
        this.numStars = numStars;
        this.solution = solution;
    }

    /**
     * Method returns the instance of the game board sections
     * Note: to get board dimension, call getBoard()
     * @return 2d array of the current board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Method returns the number of stars needed for every row, column, and section
     * @return numStars for victory condition for a given puzzle
     */
    public int getNumStars() {
        return numStars;
    }

    /**
     * Method returns the solution to the puzzle. 
     * @return Arraylist<Coordinate> of the solution star locations
     */
    public ArrayList<Coordinate> getSolution() {
        return solution;
    }

}