package busquedapvz;

import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class ChomperPerception extends Perception{
	

	Cell[][] perceptedWorld;
	Integer zombieAmountOnMap;
	
	public ChomperPerception() {
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
		
		Integer chomperPositionX = environmentState.getChomperPosition().getX();
		Integer chomperPositionY = environmentState.getChomperPosition().getY();
		
		Cell[][] actualEnvironmentState = environmentState.getWorld();
		
		zombieAmountOnMap = ((PvzEnvironment) environment).getZombiesOnMap().size();
				
		for(Integer i=chomperPositionX;i< PvzEnvironment.MAP_SIZE_X;i++) {
			
			perceptedWorld[chomperPositionY][i]=actualEnvironmentState[chomperPositionY][i];
			
			if(!(actualEnvironmentState[chomperPositionY][i] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer i=chomperPositionX;i>=0;i--) {
			
			perceptedWorld[chomperPositionY][i]=actualEnvironmentState[chomperPositionY][i];
			
			if(!(actualEnvironmentState[chomperPositionY][i] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer j=chomperPositionY;j< PvzEnvironment.MAP_SIZE_Y;j++) {
			
			perceptedWorld[j][chomperPositionX]=actualEnvironmentState[j][chomperPositionX];
			
			if(!(actualEnvironmentState[j][chomperPositionX] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer j=chomperPositionY;j>=0;j--) {
			
			perceptedWorld[j][chomperPositionX]=actualEnvironmentState[j][chomperPositionX];
			
			if(!(actualEnvironmentState[j][chomperPositionX] instanceof EmptyCell)) { 
				break;
			}
		}
		
		perceptedWorld[chomperPositionY][chomperPositionX].setContainsAgent(true);
		
	}
	
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		
        		if(perceptedWorld[i][j].containsAgent()) {
        			str.append("@");
        			str.append(perceptedWorld[i][j].toString());
        			
        		}else {
        			str.append(" ");
        			str.append(perceptedWorld[i][j].toString());
        			
        		}
            	str.append(" ");
            }
        	str.append("\n");
        }

        return str.toString();
    }
    
    private void initSensor(){
    	perceptedWorld = new Cell[PvzEnvironment.MAP_SIZE_Y][PvzEnvironment.MAP_SIZE_X];
        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		perceptedWorld[i][j] = new UnknownCell();
            }
        }
    }

	public void setZombieAmountOnMap(Integer zombieAmountOnMap) {
		this.zombieAmountOnMap = zombieAmountOnMap;
	}

	public Cell[][] getPerceptedWorld() {
		return perceptedWorld;
	}

	public void setPerceptedWorld(Cell[][] perceptedWorld) {
		this.perceptedWorld = perceptedWorld;
	}
}
