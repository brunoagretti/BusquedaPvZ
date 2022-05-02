package busquedapvz.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.awt.image.BufferedImageOp;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import busquedapvz.Cell;
import busquedapvz.EmptyCell;
import busquedapvz.PvzEnvironment;
import busquedapvz.SunflowerCell;
import busquedapvz.ZombieCell;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	public static final int ROWS = PvzEnvironment.MAP_SIZE_Y;
	public static final int COLS = PvzEnvironment.MAP_SIZE_X;
	
	private BufferedImage tiles[] = new BufferedImage[6];
    Cell[][] map = {
    		{new SunflowerCell(null,false,0),new SunflowerCell(null,false,0),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false)},
			{new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new ZombieCell(null,false,2,34),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false)},
			{new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false)},
			{new EmptyCell(null,false),new EmptyCell(null,true),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new ZombieCell(null,false,3,34),new EmptyCell(null,false),new EmptyCell(null,false)},
			{new EmptyCell(null,false),new EmptyCell(null,false),new ZombieCell(null,false,1,34),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false),new EmptyCell(null,false)}
	};
    private static int SPACING = 10;
    private static int CELL_WIDTH = 100;
    private static int CELL_HEIGHT = 100;
    private static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * CELL_WIDTH;
    private static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * CELL_HEIGHT;
	private BufferedImage chomperImage;
	private BufferedImage sunflowerImage;
	private BufferedImage sunImage;
	private BufferedImage zombieImage[] = new BufferedImage[5];
	private Graphics graphics;
	
    public GamePanel(Graphics graphics) {
      this.graphics=graphics;
    	try {
			chomperImage = ImageIO.read(getClass().getResource("chomper.png"));
			sunflowerImage = ImageIO.read(getClass().getResource("sunflower.png"));
			sunImage = ImageIO.read(getClass().getResource("sun.png"));
			zombieImage[0] = ImageIO.read(getClass().getResource("zombie1.png"));
			zombieImage[1] = ImageIO.read(getClass().getResource("zombie2.png"));
			zombieImage[2] = ImageIO.read(getClass().getResource("zombie3.png"));
			zombieImage[3] = ImageIO.read(getClass().getResource("zombie4.png"));
			zombieImage[4] = ImageIO.read(getClass().getResource("zombie5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	paintComponent(graphics);
    }

    @Override
    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);   
        Graphics2D g = (Graphics2D) g2;
        
        g.setColor(new Color(51, 204, 51));
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		for(int row=0;row<ROWS;row++) {
			for(int col=0;col<COLS;col++) {
				int x = SPACING + SPACING * col + CELL_WIDTH * col;
				int y = SPACING + SPACING * row + CELL_HEIGHT * row;
				g.setColor(new Color(0, 153, 0));
				g.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT);
				if(map[row][col] instanceof SunflowerCell) {
				  g.drawImage(sunflowerImage, x, y, null);
				}
				if(map[row][col].containsAgent()) {
				  g.drawImage(chomperImage, x, y, null);
                }
                if(map[row][col] instanceof ZombieCell) {
                  int hp = ((ZombieCell) map[row][col]).getHp();
                  g.drawImage(zombieImage[hp-1], x, y, null);
                  
                  g.setColor(new Color(255, 255, 255));
                  g.setFont(g.getFont().deriveFont(30f));
                  g.drawString("HP: " + hp, x+15, y+88);
                }
                if(map[row][col] instanceof SunflowerCell) {
                  int suns = ((SunflowerCell) map[row][col]).getSunQuantity();
                  g.drawImage(sunImage, x, y, null);
                  g.setColor(new Color(255, 255, 26));
                  g.setFont(g.getFont().deriveFont(30f));
                  g.drawString("x"+suns , x+45, y+88);
                }
				
			}
			g.setColor(new Color(51, 204, 51));
		}
    }
    
    public void nextFrame(Cell[][] map) {
      this.map=map;
      paintComponent(graphics);
    }
	
}
