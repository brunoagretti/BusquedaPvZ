package busquedapvz;

import frsf.cidisi.faia.state.EnvironmentState;

public class PvzEnvironmentState extends EnvironmentState{
	
	private Entity world[][];
	private Integer[] chomperPosition;
	private Integer chomperEnergy;
	
	
	
	public PvzEnvironmentState(Entity[][] m) {
        world = m;
    }

    public PvzEnvironmentState() {
        world = new Entity[4][4];
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

}
