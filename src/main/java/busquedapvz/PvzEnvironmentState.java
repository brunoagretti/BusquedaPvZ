package busquedapvz;

import frsf.cidisi.faia.state.EnvironmentState;

public class PvzEnvironmentState extends EnvironmentState {
	
	private CellContent world[][];
	private Integer chomperPositionX;
	private Integer chomperPositionY;
	private Integer chomperEnergy;
	
	
	
	public PvzEnvironmentState(CellContent[][] m) {
        world = m;
    }

    public PvzEnvironmentState() {
        world = new CellContent[4][4];
        this.initState();
    }
    
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
            	str.append(world[i][j].toString());
            	str.append(" ");
            }
        	str.append("\n");
        }

        return str.toString();
    }

	public CellContent[][] getWorld() {
		return world;
	}

	public void setWorld(CellContent[][] world) {
		this.world = world;
	}

	public Integer getChomperPositionX() {
		return chomperPositionX;
	}

	public void setChomperPositionX(Integer chomperPositionX) {
		this.chomperPositionX = chomperPositionX;
	}

	public Integer getChomperPositionY() {
		return chomperPositionY;
	}

	public void setChomperPositionY(Integer chomperPositionY) {
		this.chomperPositionY = chomperPositionY;
	}

	public Integer getChomperEnergy() {
		return chomperEnergy;
	}

	public void setChomperEnergy(Integer chomperEnergy) {
		this.chomperEnergy = chomperEnergy;
	}
	
	
	

}
