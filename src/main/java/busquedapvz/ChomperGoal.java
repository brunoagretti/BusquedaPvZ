package busquedapvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ChomperGoal extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		if(((ChomperAgentState) agentState).noZombiesLeft())
			return true;
		else
			return false;
		
	}
	
}
