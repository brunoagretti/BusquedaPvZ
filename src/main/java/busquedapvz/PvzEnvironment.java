package busquedapvz;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PvzEnvironment extends Environment {
	
	static final Integer MAP_SIZE_X = 9;
	static final Integer MAP_SIZE_Y = 5;
	
	public PvzEnvironment() {
		this.environmentState = new PvzEnvironmentState();
	}
	
	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PvzEnvironmentState getEnvironmentState() {
		return (PvzEnvironmentState) super.getEnvironmentState();
	}

}
