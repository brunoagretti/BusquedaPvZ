package busquedapvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ChomperGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		
		ChomperAgentState chomperState = (ChomperAgentState) agentState;
		
		if (chomperState.allRowsVisited() && chomperState.noZombiesOnMap()) {
			return true;
		}
		return false;
	}

}
