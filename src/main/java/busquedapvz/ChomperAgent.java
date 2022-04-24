package busquedapvz;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;

public class ChomperAgent extends SearchBasedAgent {

	
  @Override
  public void see(Perception p) {
    this.getAgentState().updateState(p);
  }

  @Override
  public Action selectAction() {
    // TODO Auto-generated method stub
    return null;
  }

}
