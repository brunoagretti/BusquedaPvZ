package busquedapvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ChomperGoalState extends GoalTest{

	@Override
	public boolean isGoalState(AgentState agentState) {
		if(((ChomperAgentState) agentState).getZombiesAmountOnMap() == 0)
			return true;
		else
			return false;
		
	}
	
}
