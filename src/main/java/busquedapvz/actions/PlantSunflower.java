package busquedapvz.actions;

import busquedapvz.Cell;
import busquedapvz.ChomperAgentState;
import busquedapvz.EmptyCell;
import busquedapvz.PvzEnvironmentState;
import busquedapvz.SunflowerCell;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public class PlantSunflower extends SearchAction{

	@Override
	public SearchBasedAgentState execute(SearchBasedAgentState s) {
		ChomperAgentState chomperState = (ChomperAgentState) s;
		
		Cell cellToPlant = chomperState.getKnownWorld()[chomperState.getPosition().getX()][chomperState.getPosition().getY()];
		if(cellToPlant instanceof EmptyCell && chomperState.getEnergy()>1) {//TODO ojo aca
			cellToPlant = new SunflowerCell(cellToPlant.getPosition(),true,0);
			//chomperState.decrementEnergy(1);
			chomperState.getKnownWorld()[chomperState.getPosition().getX()][chomperState.getPosition().getY()] = cellToPlant;
			//System.out.println("PLANTAR: " + cellToPlant.getPosition());//TODO DEBUG
			return chomperState;
		}
			
		return null;
	}

	@Override
	public Double getCost() {
		return Double.valueOf(1.0);
	}

	@Override
	public EnvironmentState execute(AgentState ast, EnvironmentState est) {
		ChomperAgentState chomperState = (ChomperAgentState) ast;
		PvzEnvironmentState environmentState = (PvzEnvironmentState) est;
		
		Cell cellToPlant = chomperState.getKnownWorld()[chomperState.getPosition().getX()][chomperState.getPosition().getY()];
		
		if(cellToPlant instanceof EmptyCell && chomperState.getEnergy()>1) {
			cellToPlant = new SunflowerCell(cellToPlant.getPosition(),true,0);
			environmentState.getWorld()[chomperState.getPosition().getX()][chomperState.getPosition().getY()] = new SunflowerCell(cellToPlant.getPosition(),true,0);
			chomperState.decrementEnergy(1);
			environmentState.decrementChomperEnergy(1);
			
			
			return environmentState;
		}
			
		return null;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Action: PlantSunflower";
	}

}
