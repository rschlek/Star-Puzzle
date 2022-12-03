package starb.client;

import starb.puzzle.Coordinate;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PuzzleDrawingPanel extends JComponent{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 540;

    static final int cols = 10;
    static final int rows = 10;
    static final int originX = 0;
    static final int originY = 0;
    static final int cellSide = 50;

    private ClientInteraction c = new ClientInteraction();

    public PuzzleDrawingPanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Method to paint the panel and bring everything together.
     * If gamestate has been instantiated (by load puzzle button) will paint board and state
     * If win is present, will paint background green.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(c.checkInit() && c.checkWin()) {
            g.setColor(Color.green);
            g.fillRect(0,0,WIDTH,WIDTH);
            g.setColor(Color.black);
            
        }
        if(c.checkInit()){
            paintBoard(g);
            paintState(g);
        }
        else{
            paintEmptyBoard(g);
        }
    }

    /**
     * Paints an empty board (no sections)
     * @param g1
     */
    private void paintEmptyBoard(Graphics g1){
        for(int i = 0; i < rows + 1; i++){
            g1.drawLine(originX, originY + (i * cellSide), originX+(cols * cellSide), originY + (i * cellSide));
        }

        for(int i = 0; i < cols + 1; i++){
            g1.drawLine(originX + (i * cellSide), originY, originX+(i * cellSide), originY + (rows * cellSide));
        }
    }

    /**
     * Method to paint the board grid/cells from the gamestate
     * @param g
     */
    private void paintBoard(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        HashMap<Integer, boolean[]> rowsBold = c.getSectionRow();
        HashMap<Integer, boolean[]> colsBold = c.getSectionCol();

        int x = originX;
        int y = originY;

        //borders
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.drawLine(originX, originY , originX+ (cols*cellSide), originY );
        g2d.drawLine(originX, originY + (10*cellSide), originX+ (cols*cellSide), originY + (10*cellSide));
        g2d.drawLine(originX , originY, originX, originY + (rows*cellSide));
        g2d.drawLine(originX + (10*cellSide), originY, originX+(10*cellSide), originY + (rows*cellSide));

        //sections ROWS
        for(int i =0; i < rowsBold.size(); i++){

            for(int j = 0; j < rowsBold.size(); j++){
                if(c.getBoldRows(i, j, rowsBold)){
                    g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.drawLine(x, y + cellSide, x+ cellSide, y + cellSide );
                    x += cellSide;
                }
                else{
                    g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.drawLine(x, y + (cellSide), x+ (cellSide), y + (cellSide));
                    x += cellSide;
                }
            }
            //change row
            y+=cellSide;
            x = originX;
        }
        //reset origin
        x = originX;
        y = originY;

        //sections COLUMNS
        for(int i =0; i < colsBold.size(); i++){

            for(int j = 0; j < colsBold.size(); j++){
                if(c.getBoldCols(i, j, colsBold)){
                    g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.drawLine(x + cellSide, y, x+ cellSide, y + cellSide );
                    y += cellSide;
                }
                else{
                    g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.drawLine(x+ (cellSide), y , x+ (cellSide), y + (cellSide));
                    y += cellSide;
                }
            }
            //change row
            x+=cellSide;
            y = originY;
        }
    }

    /**
     * Method to paint the currently placed stars and points from the gamestate
     * @param g
     */
    private void paintState(Graphics g){

        ArrayList<Coordinate> stars = c.getPlacedStars();
        ArrayList<Coordinate> points = c.getPlacedPoints();

        int loopSize = Math.max(stars.size(), points.size());
        int starSizeScale = 4;
        int pointSizeScale = 3;

        for (int i = 0; i < loopSize; i++) {
            if (i < stars.size()) {
                Coordinate currentStar = stars.get(i);
                int paintX = (currentStar.getX() * cellSide) + (cellSide / starSizeScale) - 5;
                int paintY = (currentStar.getY() * cellSide) + (cellSide / starSizeScale) - 5;
                paintStar(g, paintX, paintY, cellSide / 2);
            }

            if (i < points.size()) {
                Coordinate currentPoint = points.get(i);
                int paintX = (currentPoint.getX() * cellSide) + (cellSide / pointSizeScale);
                int paintY = (currentPoint.getY() * cellSide) + (cellSide / pointSizeScale);
                paintPoint(g, paintX, paintY, cellSide / pointSizeScale);
            }

        }
    }

    /**
     * Draws a star. 
     * @param g
     * @param x the x coordinate to paint the star
     * @param y te y coordinate to paint the star
     */
    private void paintStar(Graphics g, int x, int y, int w){
        int[] xPoints = new int[10];
        int[] yPoints = new int[10];

        xPoints[0] = x+0*2+5;
        xPoints[1] = x+5*2+5;
        xPoints[2] = x+7*2+5;
        xPoints[3] = x+9*2+5;
        xPoints[4] = x+14*2+5;
        xPoints[5] = x+11*2+5;
        xPoints[6] = x+12*2+5;
        xPoints[7] = x+7*2+5;
        xPoints[8] = x+2*2+5;
        xPoints[9] = x+4*2+5;
        
        yPoints[0] = y+6*2+5;
        yPoints[1] = y+6*2+5;
        yPoints[2] = y+0*2+5;
        yPoints[3] = y+6*2+5;
        yPoints[4] = y+6*2+5;
        yPoints[5] = y+9*2+5;
        yPoints[6] = y+14*2+5;
        yPoints[7] = y+12*2+5;
        yPoints[8] = y+14*2+5;
        yPoints[9] = y+9*2+5;

        g.setColor(Color.BLACK);
        g.fillPolygon(xPoints, yPoints, 10);
    }


    /**
     * Draws a dot.
     * @param g
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @param w the width of the point
     */
    private void paintPoint(Graphics g, int x, int y, int w){
        g.setColor(Color.GRAY);
        g.fillOval(x,y,w,w);
    }

    /**
     * Loads a puzzle from the client interaction class.
     */
    public void loadPuzzle(){
        c.loadPuzzle();
    }

    /**
     * Updates the board in client game state based on where the user clicks.
     * @param x the x coordinate to update within the gamestate
     * @param y the y coordinate to update within the gamestate
     */
    public void updateBoard(int x, int y){
        c.boardClick(x,y);
    }


    /**
     * Checks to see if the ClientInteraction instance has been instantiated yet.
     * @return true if the board has been initiated, false otherwise
     */
    public boolean checkInit(){
        if(c.checkInit()){
            return true;
        }
        return false;
    }
}
