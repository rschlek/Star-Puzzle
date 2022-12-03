package starb.client;

import java.awt.*;

public class StarbClient {

    public static PuzzleWindow screen = new PuzzleWindow();

    public static void main( String[] args ){
        // Start the GUI
        EventQueue.invokeLater( () -> {
            screen.setVisible(true);
        } );
    }
}
