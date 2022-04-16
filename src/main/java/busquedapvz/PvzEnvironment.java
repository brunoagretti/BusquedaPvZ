package busquedapvz;

import java.util.Random;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

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
	
	@Override
	public void updateState(AgentState ast, Action action) {
		super.updateState(ast, action);
		
		
	}
	
	public void generateSuns() {
		CellContent[][] world = ((PvzEnvironmentState) this.environmentState).getWorld();
		
        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		if(world[i][j] instanceof SunflowerEntity) {
        			Random r = new Random();
        			((SunflowerEntity) world[i][j]).addSuns(1+r.nextInt(3));
        		}
            }
        }
	}
	
	public void addZombies(Integer ammount) {
		// TODO Auto-generated method stub
		
	}

}
