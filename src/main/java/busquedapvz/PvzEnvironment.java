package busquedapvz;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class PvzEnvironment extends Environment{
	
	
	
	public PvzEnvironment() {
		this.environmentState = new PvzEnvironmentState();
	}
	
	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		return null;
	}

}
