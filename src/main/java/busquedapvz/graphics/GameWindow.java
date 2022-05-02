package busquedapvz.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

import busquedapvz.Cell;
import busquedapvz.EmptyCell;
import busquedapvz.PvzEnvironment;
import busquedapvz.SunflowerCell;
import busquedapvz.ZombieCell;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	public static final int ROWS = PvzEnvironment.MAP_SIZE_Y;
	public static final int COLS = PvzEnvironment.MAP_SIZE_X;
	
	private BufferedImage tiles[] = new BufferedImage[6];
    Cell[][] map = {
    		{new SunflowerCell(null,null,null),new SunflowerCell(null,null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null)},
			{new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new ZombieCell(null,null,null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null)},
			{new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null)},
			{new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new ZombieCell(null,null,null,null),new EmptyCell(null,null),new EmptyCell(null,null)},
			{new EmptyCell(null,null),new EmptyCell(null,null),new ZombieCell(null,null,null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null),new EmptyCell(null,null)}
	};
    private BufferedImage gameBoard;
    private BufferedImage finalBoard;
    private static int SPACING = 10;
    private static int CELL_WIDTH = 100;
    private static int CELL_HEIGHT = 100;
    private static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * CELL_WIDTH;
    private static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * CELL_HEIGHT;
    
    public GameWindow(int x, int y) {
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    	gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    	finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);

    	setSize(x, y);
    	setVisible(true);
    	GamePanel panel = new GamePanel(getGraphics());
    	this.add(panel);

    	
    }

}
