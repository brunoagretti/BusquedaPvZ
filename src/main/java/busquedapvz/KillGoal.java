package busquedapvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KillGoal extends GoalTest {

	private Integer moveObjective=1;
	
	
	@Override
	public boolean isGoalState(AgentState agentState) {
		ChomperAgentState state = (ChomperAgentState) agentState;
		if((isInPosition(state) && !zombiesClose(state)) || state.getZombiesAmount()<1){
			return true;
		}
		return false;
	}

	private boolean zombiesClose(ChomperAgentState state) {
		Cell[][] world = state.getKnownWorld();
		for(int i =0;i<3;i++) {
			for(int j=0;j<PvzEnvironment.MAP_SIZE_Y;j++) {
				if(world[i][j] instanceof ZombieCell) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isInPosition(ChomperAgentState state) {
		if(state.getMoveObjective()==1) {
			return state.getPosition().equals(new Position(0,0));
		}else {
			return state.getPosition().equals(new Position(0,4));
		}
	}

}
