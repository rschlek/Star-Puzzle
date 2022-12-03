package starb.client;

import starb.puzzle.Coordinate;
import starb.puzzle.Parser;
import starb.puzzle.PuzzleAdt;

import java.util.ArrayList;


public class ClientGameState {

    private char[][] gameState;
    private PuzzleAdt puzzle;

    // Representations on board
    final static public char STAR = '*';
    final static public char POINT = '.';
    final static public char SPACE = ' ';

    /**
     * Note: the puzzle file should be parsed via the server before being passed
     * to this constructor. The constructor takes the string representation, not the
     * filepath
     * @param boardString String representation of the board to be represented
     */
    public ClientGameState(String boardString){
        Parser p = new Parser();
        puzzle = p.stringToBoard(boardString);
        gameState = new char[puzzle.getBoard().length][puzzle.getBoard().length];

        // Initialize gameBoard with all spaces
        for(int i = 0; i < gameState.length; i++){
            for(int j = 0; j < gameState[0].length; j++){
                gameState[i][j] = SPACE;
            }
        }
    }

    /**
     * Method places a star at the given x,y coordinate
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void setStar(int x, int y){
        if(x < 0 || x > gameState.length || y < 0 || y > gameState.length){
            throw new IllegalArgumentException();
        }
        gameState[x][y] = STAR;

    }

    /**
     * Method places a star at the given x,y coordinate
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void setPoint(int x, int y){
        if(x < 0 || x > gameState.length || y < 0 || y > gameState.length){
            throw new IllegalArgumentException();
        }
        gameState[x][y] = POINT;
    }

    /**
     * Method places a star at the given x,y coordinate
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void setSpace(int x, int y){
        if(x < 0 || x > gameState.length || y < 0 || y > gameState.length){
            throw new IllegalArgumentException();
        }
        gameState[x][y] = SPACE;
    }

    /**
     * Gets the current cell based on gameState
     * @param x x- coordinate
     * @param y y-coordinate
     * @return that character at that location in game state. 
     */
    public char getSquare(int x, int y) {

        if(x < 0 || x > gameState.length || y < 0 || y > gameState.length){
            throw new IllegalArgumentException();
        }
        return gameState[x][y];
    }

    /**
     * This is the function to call if you want to see what the current
     * puzzle's cell locations are
     * @return the int[][] array representation of the puzzleBoard
     */
    public int[][] getBoard(){
        return puzzle.getBoard();
    }

    /**
     * This is the function to call if you want to see where the player
     * has placed stars/points
     * @return the char[][] array representation of the gameState
     */
    public char[][] getGameState(){
        return gameState;
    }

    /**
     * checks to see if the stars on the current board match the stars in the puzzle's given solution
     * @return true if the stars match the solution, else false
     */
    public boolean checkWin(){
        // Gets and stores all current stars in the game state
        ArrayList<Coordinate> currStateStars = getPlacedStars();//new ArrayList<>();

        // Checks to see if the solution and currState contain the same number of stars
        if(currStateStars.size() != puzzle.getSolution().size()){
            return false;
        }

        // Checks to see if all stars in currState are in the solution
        for(int i = 0; i < currStateStars.size(); i++){
            boolean inSolution = false;
            for(int j = 0; j < puzzle.getSolution().size(); j++){
                if(currStateStars.get(i).compareCoord(puzzle.getSolution().get(j))){
                    inSolution = true;
                }
            }
            if(!inSolution){
                return false;
            }
        }
        return true;
    }

    /**
     * creates a list of coordinate objects representing the location of placed stars
     * @return a list containing the locations of placed stars
     */
    public ArrayList<Coordinate> getPlacedStars() {
        ArrayList<Coordinate> stars = new ArrayList<>();

        for(int i = 0; i < gameState.length; i++){
            for(int j = 0; j < gameState[0].length; j++){
                if(getSquare(i,j) == STAR){
                    Coordinate c = new Coordinate(i,j);
                    stars.add(c);
                }
            }
        }

        return stars;
    }

    /**
     * creates a list of coordinate objects representing the location of placed points
     * @return a list containing the locations of placed points
     */
    public ArrayList<Coordinate> getPlacedPoints() {
        ArrayList<Coordinate> points = new ArrayList<>();

        for(int i = 0; i < gameState.length; i++){
            for(int j = 0; j < gameState[0].length; j++){
                if(getSquare(i,j) == POINT){
                    Coordinate c = new Coordinate(i,j);
                    points.add(c);
                }
            }
        }

        return points;
    }
}
