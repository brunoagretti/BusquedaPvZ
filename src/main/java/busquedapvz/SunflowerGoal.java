package busquedapvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class SunflowerGoal extends GoalTest {
	@Override
	public boolean isGoalState(AgentState agentState) {
		ChomperAgentState chomperState = (ChomperAgentState) agentState;
		if (chomperState.sunflowersPlaced() && chomperState.getEnergy()>calculateRequiredEnergy(chomperState)) {
			return true;
		} else {
			return false;
		}
	}

	private Integer calculateRequiredEnergy(ChomperAgentState chomperState) {
		Integer required = 0;
		Cell[][] world= chomperState.getKnownWorld();
	      for (Integer i = 0; i < PvzEnvironment.MAP_SIZE_X; i++) {
	          for (Integer j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
	            if(world[i][j] instanceof ZombieCell) {
	            	required += ((ZombieCell) world[i][j]).getHp();
	            }
	          }
	        }
			
		return required;
	}

}
