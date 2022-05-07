package busquedapvz;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ChomperGoal extends GoalTest{
	Integer goal;
	
	public ChomperGoal(Integer goal) {
		this.goal = goal;
	}
	
	@Override
	public boolean isGoalState(AgentState agentState) {
		ChomperAgentState chomperState = (ChomperAgentState) agentState;
			if(goal == 2 && chomperState.noZombiesOnMap()) {
				return true;
			}
			if(goal == 1 && chomperState.allMapSunflowered()) {
				return true;
			}
			else
				return false;
	}
	
}
