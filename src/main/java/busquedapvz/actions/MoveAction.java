package busquedapvz.actions;

import busquedapvz.Cell;
import busquedapvz.ChomperAgentState;
import busquedapvz.EmptyCell;
import busquedapvz.Position;
import busquedapvz.PvzEnvironment;
import busquedapvz.PvzEnvironmentState;
import busquedapvz.SunflowerCell;
import busquedapvz.ZombieCell;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public interface MoveAction {
	
	public default SearchBasedAgentState move(SearchBasedAgentState s, Position posToMove) {
	    ChomperAgentState chomperState = (ChomperAgentState) s;
	    Integer chomperX = chomperState.getPosition().getX();
	    Integer chomperY = chomperState.getPosition().getY();
	    //If coordinate is out of bound, return invalid action
	    Boolean invalidXCoord = posToMove.getX()<0 || posToMove.getX()>PvzEnvironment.MAP_SIZE_X-1;
	    Boolean invalidYCoord = posToMove.getY()<0 || posToMove.getY()>PvzEnvironment.MAP_SIZE_Y-1;
	   
	    if(!invalidXCoord && !invalidYCoord) {
	    	Cell cellToMove = chomperState.getKnownWorld()[posToMove.getX()][posToMove.getY()];
	    	
	    	if(cellToMove instanceof SunflowerCell) {
	    		chomperState.addEnergy(((SunflowerCell) cellToMove).getSunQuantity());
	    		((SunflowerCell) cellToMove).takeSuns();
	    	}
	    	
	    	else {
	    		if(cellToMove instanceof ZombieCell) {
	    			chomperState.decrementEnergy(((ZombieCell) cellToMove).getHp()*2);
		    	}
	    	}
	    	
	    	chomperState.setPosition(posToMove);
	    	chomperState.getKnownWorld()[chomperX][chomperY].setContainsAgent(false);
	    	cellToMove.setContainsAgent(true);
	    	
	    	System.out.println("MOVER: " + posToMove);//TODO DEBUG
	    	return chomperState;
	    	
	    }
	   
	    return null;
	  }
	  
	  public default EnvironmentState move(AgentState ast, EnvironmentState est, Position posToMove) {
	    PvzEnvironmentState environmentState = (PvzEnvironmentState) est;
	    ChomperAgentState chomperState = ((ChomperAgentState) ast);
	    
	    Integer chomperX = chomperState.getPosition().getX();
	    Integer chomperY = chomperState.getPosition().getY();
	    //If coordinate is out of bound, return invalid action
	    Boolean invalidXCoord = posToMove.getX()<0 || posToMove.getX()>PvzEnvironment.MAP_SIZE_X-1;
	    Boolean invalidYCoord = posToMove.getY()<0 || posToMove.getY()>PvzEnvironment.MAP_SIZE_Y-1;
	   
	    if(!invalidXCoord && !invalidYCoord) {
	    	Cell cellToMove = chomperState.getKnownWorld()[posToMove.getX()][posToMove.getY()];
	    	Cell cellToMoveEnvironment = environmentState.getWorld()[posToMove.getX()][posToMove.getY()];
	    	
	    	if(cellToMove instanceof SunflowerCell) {
	    		chomperState.addEnergy(((SunflowerCell) cellToMove).getSunQuantity());
	    		((SunflowerCell) cellToMove).takeSuns();
	    		environmentState.addChomperEnergy(((SunflowerCell) cellToMove).getSunQuantity());
	    		((SunflowerCell) cellToMoveEnvironment).takeSuns();
	    		
	    		
	    	}
	    	
	    	else {
	    		if(cellToMove instanceof ZombieCell) {
	    			chomperState.decrementEnergy(((ZombieCell) cellToMove).getHp()*2);
	    			environmentState.decrementChomperEnergy(((ZombieCell) cellToMoveEnvironment).getHp()*2);
		    	}
	    	}
	    	
	    	chomperState.setPosition(posToMove.clone());
	    	environmentState.setChomperPosition(posToMove.clone());
	    	chomperState.getKnownWorld()[chomperX][chomperY].setContainsAgent(false);
	    	environmentState.getWorld()[chomperX][chomperY].setContainsAgent(false);
	    	cellToMove.setContainsAgent(true);
	    	cellToMoveEnvironment.setContainsAgent(true);
	    	
	    	
	    	return environmentState;
	    	
	    }
	   
	    return null;
	  }
}
