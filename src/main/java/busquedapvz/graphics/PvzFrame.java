package busquedapvz.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import busquedapvz.Cell;
import busquedapvz.PvzEnvironment;
import busquedapvz.PvzEnvironmentState;
import busquedapvz.SunflowerCell;
import busquedapvz.ZombieCell;
import busquedapvz.utils.MapManager;
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
	JLabel labelTime = new JLabel("Ciclo: ");
	JLabel labelEnergy = new JLabel("Entergia del agente: ");
	JLabel labelZombies = new JLabel("Zombies restantes: ");
	DefaultTableModel model = new DefaultTableModel(5, 9);
	Object[][] tableData = new Object[5][9];
	String[] columnnames = {"", "", "", "", "", "", "", "", ""};

	//Simulation States
	List<PvzEnvironmentState> states = new ArrayList<>();
	Integer currentStateIndex = 0;
	
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
		
	};
	JPanel topPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JPanel buttonsPanel = new JPanel();
	JButton nextFrameBtn = new JButton("Siguiente");
	JButton previousFrameBtn = new JButton("Anterior");

	public PvzFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Plants Vs Zombies - Simulator");
		tableData = getEmptyImageArray();
		loadImages();
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
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
                        table.setRowHeight(e.getFirstRow(), 100);
                    }
                });
            }
		});
		for (int i = 0; i < 9; i++) {
			table.getColumnModel().getColumn(i).setMinWidth(100);
		}

		setUpLayout(layout);
		
		PvzEnvironmentState emptyState = new PvzEnvironmentState(MapManager.createEmptyWorld(), null, 0, 0, 0, false);
		states.add(emptyState);
		currentStateIndex=0;
		
		setUpButtons();
		setButtonsVisible(false);
		
		setSize(screenWidth, screenHeight);
		setVisible(true);
		this.pack();
	}

	private void setUpLayout(GridBagLayout layout) {
		labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		topPanel.add(labelHead);
		topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		centerPanel.add(table);
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.setSize(1000, 600);
        
        labelTime.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		labelEnergy.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		labelZombies.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));
		infoPanel.setLayout(new GridLayout(2, 1));
		infoPanel.add(labelTime);
		infoPanel.add(labelEnergy);
		infoPanel.add(labelZombies);
		infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buttonsPanel.add(previousFrameBtn);
		buttonsPanel.add(nextFrameBtn);
		
		add(topPanel);
		add(centerPanel);
		add(infoPanel);
		add(buttonsPanel);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridy=0;
		c1.gridx=0;
		c1.gridheight=1;
		c1.gridwidth=2;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridy=1;
		c2.gridx=0;
		c2.gridheight=2;
		c2.gridwidth=2;
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridy=3;
		c3.gridx=0;
		c3.gridheight=1;
		c3.gridwidth=2;
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridy=4;
		c4.gridx=0;
		c4.gridheight=1;
		c4.gridwidth=2;
		layout.setConstraints(topPanel,c1);
		layout.setConstraints(centerPanel, c2);
		layout.setConstraints(infoPanel, c3);
		layout.setConstraints(buttonsPanel, c4);
	}
	
	public PvzFrame(PvzEnvironmentState state) {
		this();
		addNewState(state);
	}
	
	public void addNewState(PvzEnvironmentState state) {
		PvzEnvironmentState newState = state.clone();
		drawTable(newState);

		this.currentStateIndex += 1;
		labelTime.setText("Ciclo: " + currentStateIndex);
		this.states.add(newState);
	}
	
	private void drawTable(PvzEnvironmentState state) {
		Cell[][] map = state.getWorld();
		for (Integer i = 0; i < COLS; i++) {
			for (Integer j = 0; j < ROWS; j++) {
				table.getModel().setValueAt(generateTileImage(map[i][j]), j, i);
			}
		}
		labelEnergy.setText("Entergia del agente: " + state.getChomperEnergy());
		labelZombies.setText("Zombies restantes: " + state.getRemainingZombiesAmount());
		
//		if(state.getAgentFailed()) {
//			JOptionPane.showMessageDialog(this,
//				    "El agente ha fallado",
//				    "Simulador Finalizado",
//				    JOptionPane.ERROR_MESSAGE);
//		}
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
	
	public void setButtonsVisible(boolean visible) {
		if(visible) {
			buttonsPanel.setVisible(true);
		}else {
			buttonsPanel.setVisible(false);
		}
		pack();
	}
	
	private void setUpButtons() {
		nextFrameBtn.addActionListener(e->{
			if(currentStateIndex<states.size()-1) {
				currentStateIndex +=1;
				labelTime.setText("Ciclo: " + currentStateIndex);
				drawTable(states.get(currentStateIndex));
			}
		});
		previousFrameBtn.addActionListener(e->{
			if(currentStateIndex>0) {
				currentStateIndex -=1;
				labelTime.setText("Ciclo: " + currentStateIndex);
				drawTable(states.get(currentStateIndex));
			}
		});
	}
}
