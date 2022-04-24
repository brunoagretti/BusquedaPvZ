package busquedapvz.actions;

import busquedapvz.ChomperAgentState;
import busquedapvz.Position;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class GoLeft extends SearchAction implements MoveAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		ChomperAgentState chomperState = ((ChomperAgentState) s);

	    Position chomperPos = chomperState.getPosition();
	    Position posToMove = new Position(chomperPos.getX()-1, chomperPos.getY());

	    return move(s, posToMove);
	}

	@Override
	public Double getCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		ChomperAgentState chomperState = ((ChomperAgentState) ast);

	    Position chomperPos = chomperState.getPosition();
	    Position posToMove = new Position(chomperPos.getX() - 1, chomperPos.getY());

	    return move(ast, est, posToMove);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
