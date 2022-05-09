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
	    	
	    	chomperState.getKnownWorld()[chomperX][chomperY].setContainsAgent(false);
	    	chomperState.setPosition(new Position(posToMove.getX(), posToMove.getY()));
	    	
	    	cellToMove.setContainsAgent(true);
	    	chomperState.increaseCeldasVisitadas(1);
	    	
	    	//System.out.println("MOVER: " + posToMove);//TODO DEBUG
	    	return chomperState;
	    	
	    }
	   
	    return null;
	  }
	  
	  public default EnvironmentState move(AgentState ast, EnvironmentState est, Position posToMove) {
	    PvzEnvironmentState environmentState = (PvzEnvironmentState) est;
	    ChomperAgentState chomperState = ((ChomperAgentState) ast);
	    
	    Integer chomperX = environmentState.getChomperPosition().getX();
	    Integer chomperY = environmentState.getChomperPosition().getY();
	    //If coordinate is out of bound, return invalid action
	    Boolean invalidXCoord = posToMove.getX()<0 || posToMove.getX()>PvzEnvironment.MAP_SIZE_X-1;
	    Boolean invalidYCoord = posToMove.getY()<0 || posToMove.getY()>PvzEnvironment.MAP_SIZE_Y-1;
	   
	    if(!invalidXCoord && !invalidYCoord) {
	    	Cell cellToMove = chomperState.getKnownWorld()[posToMove.getX()][posToMove.getY()];
	    	Cell cellToMoveEnvironment = environmentState.getWorld()[posToMove.getX()][posToMove.getY()];
	    	//System.out.println(cellToMoveEnvironment.toString());
	    	
	    	if(cellToMoveEnvironment instanceof SunflowerCell) {
	    		//System.out.println(((SunflowerCell) cellToMove).getSunQuantity());
	    		chomperState.addEnergy(((SunflowerCell) cellToMove).getSunQuantity());
	    		//((SunflowerCell) cellToMove).takeSuns();
	    		
	    		//System.out.println(((SunflowerCell) cellToMoveEnvironment).getSunQuantity());
	    		environmentState.addChomperEnergy(((SunflowerCell) cellToMove).getSunQuantity());
	    		((SunflowerCell) cellToMoveEnvironment).takeSuns();
	    		
	    		//System.out.println(((SunflowerCell) cellToMove).getSunQuantity());
	    		//System.out.println(((SunflowerCell) cellToMoveEnvironment).getSunQuantity());
	    		
	    		
	    	}
	    	
	    	else {
	    		if(cellToMoveEnvironment instanceof ZombieCell) {
	    			chomperState.decrementEnergy(((ZombieCell) cellToMoveEnvironment).getHp()*2);
	    			environmentState.decrementChomperEnergy(((ZombieCell) cellToMoveEnvironment).getHp()*2);
		    	}
	    	}
	    	
	    	
	    	chomperState.getKnownWorld()[chomperX][chomperY].setContainsAgent(false);
	    	environmentState.getWorld()[chomperX][chomperY].setContainsAgent(false);
	    	chomperState.setPosition(posToMove.clone());
	    	environmentState.setChomperPosition(posToMove.clone());
	    	cellToMove.setContainsAgent(true);
	    	cellToMoveEnvironment.setContainsAgent(true);
	    	chomperState.increaseCeldasVisitadas(1);
	    	
	    	return environmentState;
	    	
	    }
	   
	    return null;
	  }
}
