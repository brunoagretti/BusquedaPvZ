package busquedapvz;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class ChomperPerception extends Perception{
	

	CellContent[][] environmentSensor;
	Integer chomperEnergy;
	
	public ChomperPerception() {
		chomperEnergy = 10; //TODO: add randomness
	}
	
	public ChomperPerception(Agent agent, Environment environment) {
		super(agent, environment);
	}
	
	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
//		ChomperAgent chomperAgent = (ChomperAgent) agent;
		PvzEnvironment pvzEnvironment = (PvzEnvironment) environment;
		PvzEnvironmentState environmentState =
				pvzEnvironment.getEnvironmentState();
		
		Integer chomperPositionX = environmentState.getChomperPositionX();
		Integer chomperPositionY = environmentState.getChomperPositionY();
		
		CellContent[][] actualEnvironmentState = environmentState.getWorld();
		
		for(Integer i=chomperPositionX;i< PvzEnvironment.MAP_SIZE_X;i++) {
			
			environmentSensor[chomperPositionY][i]=actualEnvironmentState[chomperPositionY][i];
			
			if(actualEnvironmentState[chomperPositionY][i] != null) { //TODO null safe check
				break;
			}
		}
		for(Integer i=chomperPositionX;i>=0;i--) {
			
			environmentSensor[chomperPositionY][i]=actualEnvironmentState[chomperPositionY][i];
			
			if(actualEnvironmentState[chomperPositionY][i] != null) { //TODO null safe check
				break;
			}
		}
		for(Integer j=0;j< PvzEnvironment.MAP_SIZE_Y;j++) {
			
			environmentSensor[j][chomperPositionX]=actualEnvironmentState[j][chomperPositionX];
			
			if(actualEnvironmentState[j][chomperPositionX] != null) { //TODO null safe check
				break;
			}
		}
		for(Integer j=0;j< PvzEnvironment.MAP_SIZE_Y;j++) {
			
			environmentSensor[j][chomperPositionX]=actualEnvironmentState[j][chomperPositionX];
			
			if(actualEnvironmentState[j][chomperPositionX] != null) { //TODO null safe check
				break;
			}
		}
	
	}
	
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_X;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_Y;i++) {
            	str.append(environmentSensor[i][j].toString());
            }
        }

        return str.toString();
    }

}
