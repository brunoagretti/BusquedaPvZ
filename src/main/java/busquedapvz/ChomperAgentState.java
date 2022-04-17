package busquedapvz;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.state.AgentState;

public class ChomperAgentState extends AgentState {
	CellContent[][] knownWorld;
	Integer energy;
	Integer zombiesAmountOnMap;
	Position position;
	
	public Integer getZombiesAmountOnMap() {
		return zombiesAmountOnMap;
	}

	@Override
	public void updateState(Perception p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initState() {
		// TODO Auto-generated method stub
		
	}

}
