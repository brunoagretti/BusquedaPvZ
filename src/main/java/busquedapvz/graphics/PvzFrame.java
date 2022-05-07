package busquedapvz.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicVerticalLayoutManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import busquedapvz.Cell;
import busquedapvz.EmptyCell;
import busquedapvz.Position;
import busquedapvz.PvzEnvironment;
import busquedapvz.PvzEnvironmentState;
import busquedapvz.SunflowerCell;
import busquedapvz.ZombieCell;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class PvzFrame extends JFrame {

	public static final int ROWS = PvzEnvironment.MAP_SIZE_Y;
	public static final int COLS = PvzEnvironment.MAP_SIZE_X;

	final int screenWidth = 1500;
	final int screenHeight = 1000;
	
	//Images
	private BufferedImage backgroundImage;
	private BufferedImage emptyImage;
	private BufferedImage chomperImage;
	private BufferedImage sunflowerImage;
	private BufferedImage sunImage;
	private BufferedImage grassImage;
	private BufferedImage zombieImage[] = new BufferedImage[5];

	// Table and data definition
	JLabel labelHead = new JLabel("Agente Inteligente - Plants Vs Zombies");
	JLabel labelEnergy = new JLabel("Entergia del agente: ");
	JLabel labelZombies = new JLabel("Zombies restantes: ");
	DefaultTableModel model = new DefaultTableModel(5, 9);
	Object[][] tableData = new Object[5][9];
	String[] columnnames = {"", "", "", "", "", "", "", "", ""};

	JTable table = new JTable(model) {
		public Class<?> getColumnClass(int column) {
			return ImageIcon.class;
		}
		public boolean isCellEditable(int a, int c) {
			return false;
		}
		public Component prepareRenderer(TableCellRenderer renderer,
				int row, int column) {
			Component c = super.prepareRenderer(renderer, row, column);
			// We want renderer component to be transparent so background
			// image is visible
			if (c instanceof JComponent)
				((JComponent) c).setOpaque(false);
			return c;
		}
		
//		public void paintComponent(Graphics graphics) {
//			graphics.drawImage(backgroundImage, 0, 0, this);
//			super.paintComponent(graphics);
//		}
	};
	JPanel topPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel bottomPanel = new JPanel();

	public PvzFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tableData = getEmptyImageArray();
		loadImages();
		
        //data for JTable in a 2D table
        model.setDataVector(tableData, columnnames);
        model.setRowCount(5);
        table.setTableHeader(null);
        table.setRowSelectionAllowed(false);
        table.setRowHeight(100);
        table.setOpaque(false);
        
        model.addTableModelListener(new TableModelListener() {
            @Override public void tableChanged(final TableModelEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override public void run() {
                        table.setRowHeight(e.getFirstRow(), 100); //replace 15 with your own height
                    }
                });
            }
		});
		for (int i = 0; i < 9; i++) {
			table.getColumnModel().getColumn(i).setMinWidth(100);
		}

		labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		topPanel.add(labelHead);
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		centerPanel.add(table);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.setSize(1000, 600);
        
		labelEnergy.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		labelZombies.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		bottomPanel.add(labelEnergy);
		bottomPanel.add(labelZombies);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
       

		setSize(screenWidth, screenHeight);
		
		add(topPanel, BorderLayout.PAGE_START);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.PAGE_END);
		
		setVisible(true);
		this.pack();
	}
	
	public PvzFrame(PvzEnvironmentState state) {
		this();
		drawTable(state);
	}
	
	public void drawTable(PvzEnvironmentState state) {
		Cell[][] map = state.getWorld();
		for (Integer i = 0; i < COLS; i++) {
			for (Integer j = 0; j < ROWS; j++) {
				table.getModel().setValueAt(generateTileImage(map[i][j]), j, i);
			}
		}
		labelEnergy.setText("Entergia del agente: " + state.getChomperEnergy());
		labelZombies.setText("Zombies restantes: " + state.getZombiesAmount());
		
		if(state.getAgentFailed()) {
			JOptionPane.showMessageDialog(this,
				    "El agente ha fallado",
				    "Simulador Finalizado",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private Object[][] getEmptyImageArray() {
		Object[][] tableData = new Object[COLS][ROWS];
		for (Integer i = 0; i < COLS; i++) {
			for (Integer j = 0; j < ROWS; j++) {
				tableData[i][j] = emptyImage;
			}
		}
		return tableData;
	}

	private ImageIcon generateTileImage(Cell cell) {
		
		BufferedImage tile = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics g = tile.getGraphics();
		int x = 0;
		int y = 0;
		
		g.drawImage(grassImage, x, y, null);
		
		if(cell instanceof SunflowerCell) {
			  g.drawImage(sunflowerImage, x, y, null);
			}
			if(cell.containsAgent()) {
			  g.drawImage(chomperImage, x, y, null);
          }
          if(cell instanceof ZombieCell) {
            int hp = ((ZombieCell) cell).getHp();
            g.drawImage(zombieImage[hp-1], x, y, null);
            
            g.setColor(new Color(255, 255, 255));
            g.setFont(g.getFont().deriveFont(30f));
            g.drawString("HP: " + hp, x+15, y+88);
          }
          if(cell instanceof SunflowerCell) {
            int suns = ((SunflowerCell) cell).getSunQuantity();
            g.drawImage(sunImage, x, y, null);
            g.setColor(new Color(255, 255, 26));
            g.setFont(g.getFont().deriveFont(30f));
            g.drawString("x"+suns , x+45, y+88);
          }
          
		g.dispose();
		return new ImageIcon(tile);
	}
	
	private void loadImages() {
		try {
			emptyImage = ImageIO.read(getClass().getResource("empty.png"));
			backgroundImage = ImageIO.read(getClass().getResource("background.png"));
			chomperImage = ImageIO.read(getClass().getResource("chomper.png"));
			sunflowerImage = ImageIO.read(getClass().getResource("sunflower.png"));
			sunImage = ImageIO.read(getClass().getResource("sun.png"));
			grassImage = ImageIO.read(getClass().getResource("grass.png"));
			zombieImage[0] = ImageIO.read(getClass().getResource("zombie1.png"));
			zombieImage[1] = ImageIO.read(getClass().getResource("zombie2.png"));
			zombieImage[2] = ImageIO.read(getClass().getResource("zombie3.png"));
			zombieImage[3] = ImageIO.read(getClass().getResource("zombie4.png"));
			zombieImage[4] = ImageIO.read(getClass().getResource("zombie5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
