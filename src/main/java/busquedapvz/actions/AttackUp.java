package busquedapvz.actions;

import busquedapvz.ChomperAgentState;
import busquedapvz.Position;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class AttackUp extends SearchAction implements AttackAction{

  @Override
  public SearchBasedAgentState execute(SearchBasedAgentState s) {
    ChomperAgentState chomperState = ((ChomperAgentState) s);

    Position chomperPos = chomperState.getPosition();
    Position posToAttack = new Position(chomperPos.getX(), chomperPos.getY() - 1);

    return attack(s, posToAttack);
  }

  @Override
  public Double getCost() {
	  return Double.valueOf(1.5);
  }

  @Override
  public EnvironmentState execute(AgentState ast, EnvironmentState est) {
    ChomperAgentState chomperState = ((ChomperAgentState) ast);

    Position chomperPos = chomperState.getPosition();
    Position posToAttack = new Position(chomperPos.getX(), chomperPos.getY() - 1);

    return attack(ast, est, posToAttack);
  }

  @Override
  public String toString() {
      return "Action: Attack Up";
  }

}
