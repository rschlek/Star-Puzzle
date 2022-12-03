package starb.client;

import starb.puzzle.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;

import static starb.client.ClientGameState.SPACE;
import static starb.client.ClientGameState.STAR;

public class ClientInteraction {

    private ClientGameState g;

    /**
     * Initializes a client interaction. 
     */
    public ClientInteraction(){}


    /**
     * Updates the gamestate after a click.
     * @param x x coordinate of the click
     * @param y y coordinate of the click
     */
    public void boardClick(int x, int y) {
        x = convertCoordinate(x);
        y = convertCoordinate(y);

        if(g.getSquare(x,y) == SPACE){
            g.setStar(x,y);
        }else if(g.getSquare(x,y) == STAR){
            g.setPoint(x,y);
        }else{
            g.setSpace(x,y);
        }
    }


    /**
     * Loads a puzzle sent from the server. 
     */
    public void loadPuzzle() {
        HttpRequestGenerator generator = new HttpRequestGenerator();
        String puzzleString;
        try {
            puzzleString = generator.getNewPuzzle();
            g = new ClientGameState(puzzleString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Checks if ClientGameState object "g" is null or not.
     * @return true if not null, false if null.
     */
    public boolean checkInit(){
        if(g!=null){
            return true;
        }
        return false;
    }

    /**
     * Calls checkWin from ClientGameState.
     */
    public boolean checkWin(){
        return g.checkWin();
    }

    /**
     * Uses the client game state to create/ find the 
     * puzzle's sections. This method completes the task for the rows
     * when drawn in the GUI. It compares each value in each column,
     * determining if the line should be bolded or not. 
     * @return a HashMap of true false values for each row. 
     */
    public HashMap<Integer, boolean[]> getSectionRow(){
        //get initial client game state for sections
        int[][] grid = g.getBoard();

        HashMap<Integer, boolean[]> sectionsRow = new HashMap<>();

        //ROWS ARRAY
        for(int j =0; j < grid.length ; j++){ //rows
            boolean[] row1 = new boolean[10];
            for(int k =0; k < grid.length ; k++){ //columns
                int cur = grid[j][k];
                int next;
                if(j==grid.length-1){
                    next = grid[j][k];
                }else{
                    next = grid[j+1][k];
                }
                
                if( cur != next){
                    row1[k] = true;
                }
                else{
                    row1[k] = false;
                }
            }
            sectionsRow.put(j, row1);
        }

        return sectionsRow;

    }

    /**
     * Uses the client game state to create/ find the 
     * puzzle's sections. This method completes the task for the columns
     * when drawn in the GUI. It compares each value in each row,
     * determining if the line should be bolded or not. 
     * @return a HashMap of true false values for each column. 
     */
    public HashMap<Integer, boolean[]> getSectionCol(){
        //get intial client game state for sections
        int[][] grid = g.getBoard();

        HashMap<Integer, boolean[]> sectionsCol = new HashMap<>();

        //COLUMNS ARRAY
        for(int j =0; j < grid.length ; j++){ //columns
            boolean[] col1 = new boolean[10];
            for(int k =0; k < grid.length ; k++){ //rows
                int cur = grid[k][j];
                int next;
                if(j==grid.length-1){
                    next = grid[k][j];
                }
                else{
                    next = grid[k][j+1];
                }
                if( cur != next){
                    col1[k] = true;
                }
                else{
                    col1[k] = false;
                }
            }
            sectionsCol.put(j, col1);
        }

        return sectionsCol;

    }

    /**
     * Returns if a line should be bolded or not. 
     * @param row row number
     * @param index row line to be drawn
     * @param rowsMap
     * @return a boolean value. 
     */
    public boolean getBoldRows(int row, int index, HashMap<Integer, boolean[]> rowsMap){
        boolean[] result = rowsMap.get(row);
        return result[index];
    }

    /**
     * Returns if a line should be bolded or not. 
     * @param col column number
     * @param index column line to be drawn
     * @param colMap
     * @return a boolean value. 
     */
    public boolean getBoldCols(int col, int index, HashMap<Integer, boolean[]> colMap){
        boolean[] result = colMap.get(col);
        return result[index];
    }


    /**
     * Returns a list of the locations of the stars on the current board. 
     * @return the list of stars currently placed on the board
     */
    public ArrayList<Coordinate> getPlacedStars() {
        return g.getPlacedStars();
    }

    /**
     * Returns a list of the locations of the dots on the current board.
     * @return the list of points currently placed on the board
     */
    public ArrayList<Coordinate> getPlacedPoints() {
        return g.getPlacedPoints();
    }


    /**
     * Converts a x or y value from the mouse click into a coordinate
     * that corresponds to the game state. 
     * @param val
     * @return
     */
    private int convertCoordinate(int val){
        final int width = 500;
        int dim = g.getGameState().length;
        int threshold = width/dim;

        return val/threshold;
    }
}