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
		// TODO Auto-generated method stub
		return null;
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
