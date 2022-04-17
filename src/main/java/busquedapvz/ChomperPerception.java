package busquedapvz;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class ChomperPerception extends Perception{
	

	CellContent[][] environmentSensor;
	Integer chomperEnergy;
	Integer zombieAmountOnMap;
	
	public ChomperPerception() {
		chomperEnergy = 10; //TODO: add randomness
	}
	
	public ChomperPerception(Agent agent, Environment environment) {
		super(agent, environment);
	}
	
	public Integer getZombieAmountOnMap() {
		return zombieAmountOnMap;
	}

	@Override
	public void initPerception(Agent agent, Environment environment) {
		// TODO Auto-generated method stub
//		ChomperAgent chomperAgent = (ChomperAgent) agent;
		initSensor();
		
		PvzEnvironment pvzEnvironment = (PvzEnvironment) environment;
		PvzEnvironmentState environmentState =
				pvzEnvironment.getEnvironmentState();
		
		Integer chomperPositionX = environmentState.getChomperPositionX();
		Integer chomperPositionY = environmentState.getChomperPositionY();
		
		CellContent[][] actualEnvironmentState = environmentState.getWorld();
		
		zombieAmountOnMap = ((PvzEnvironment) environment).getZombiesOnMap().size();
				
		for(Integer i=chomperPositionX;i< PvzEnvironment.MAP_SIZE_X;i++) {
			
			environmentSensor[chomperPositionY][i]=actualEnvironmentState[chomperPositionY][i];
			
			if(!(actualEnvironmentState[chomperPositionY][i] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer i=chomperPositionX;i>=0;i--) {
			
			environmentSensor[chomperPositionY][i]=actualEnvironmentState[chomperPositionY][i];
			
			if(!(actualEnvironmentState[chomperPositionY][i] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer j=chomperPositionY;j< PvzEnvironment.MAP_SIZE_Y;j++) {
			
			environmentSensor[j][chomperPositionX]=actualEnvironmentState[j][chomperPositionX];
			
			if(!(actualEnvironmentState[j][chomperPositionX] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer j=chomperPositionY;j>=0;j--) {
			
			environmentSensor[j][chomperPositionX]=actualEnvironmentState[j][chomperPositionX];
			
			if(!(actualEnvironmentState[j][chomperPositionX] instanceof EmptyCell)) { 
				break;
			}
		}
		
		environmentSensor[chomperPositionY][chomperPositionX].setContainsAgent(true);
		
	}
	
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		
        		if(environmentSensor[i][j].containsAgent()) {
        			str.append("@");
        			str.append(environmentSensor[i][j].toString());
        			
        		}else {
        			str.append(" ");
        			str.append(environmentSensor[i][j].toString());
        			
        		}
            	str.append(" ");
            }
        	str.append("\n");
        }

        return str.toString();
    }
    
    private void initSensor(){
    	environmentSensor = new CellContent[PvzEnvironment.MAP_SIZE_Y][PvzEnvironment.MAP_SIZE_X];
        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		environmentSensor[i][j] = new UnknownCell();
            }
        }
    }

}
