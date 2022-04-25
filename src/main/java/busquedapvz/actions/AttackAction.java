package busquedapvz.actions;

import busquedapvz.Cell;
import busquedapvz.ChomperAgentState;
import busquedapvz.EmptyCell;
import busquedapvz.Position;
import busquedapvz.PvzEnvironment;
import busquedapvz.PvzEnvironmentState;
import busquedapvz.ZombieCell;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import frsf.cidisi.faia.state.AgentState;
import frsf.cidisi.faia.state.EnvironmentState;

public interface AttackAction {

  
  public default SearchBasedAgentState attack(SearchBasedAgentState s, Position posToAttack) {
    ChomperAgentState chomperState = (ChomperAgentState) s;
    
    //If coordinate is out of bound, return invalid action
    Boolean invalidXCoord = posToAttack.getX()<0 || posToAttack.getX()>PvzEnvironment.MAP_SIZE_X-1;
    Boolean invalidYCoord = posToAttack.getY()<0 || posToAttack.getY()>PvzEnvironment.MAP_SIZE_Y-1;
    if(invalidXCoord || invalidYCoord) {
      return null;
    }
    
    Cell cellToAttack = chomperState.getKnownWorld()[posToAttack.getX()][posToAttack.getY()];
        
    /**
     * Preconditions: there is a zombie where we want to attack and we have enough energy
     * Postcondition: where we wanted to attack becomes an empty cell
     * Otherwise return null
     */
    if(cellToAttack instanceof ZombieCell) {
      Integer zombieHp = ((ZombieCell) cellToAttack).getHp();
      if(zombieHp<chomperState.getEnergy()) {
        chomperState.getKnownWorld()[posToAttack.getX()][posToAttack.getY()] = new EmptyCell();
        chomperState.decrementEnergy(((ZombieCell) cellToAttack).getHp());
        chomperState.decrementZombiesAmount(1);
        return chomperState;
      }
    }
    return null;
  }
  
  public default EnvironmentState attack(AgentState ast, EnvironmentState est, Position posToAttack) {
    PvzEnvironmentState environmentState = (PvzEnvironmentState) est;
    ChomperAgentState chomperState = ((ChomperAgentState) ast);
    
    //If coordinate is out of bound, return invalid action
    Boolean invalidXCoord = posToAttack.getX()<0 || posToAttack.getX()>PvzEnvironment.MAP_SIZE_X-1;
    Boolean invalidYCoord = posToAttack.getY()<0 || posToAttack.getY()>PvzEnvironment.MAP_SIZE_Y-1;
    if(invalidXCoord || invalidYCoord) {
      return null;
    }
    
    Cell cellToAttack = chomperState.getKnownWorld()[posToAttack.getX()][posToAttack.getY()];
        
    /**
     * Preconditions: there is a zombie where we want to attack and we have enough energy
     * Postcondition: where we wanted to attack becomes an empty cell
     * Otherwise return null
     */
    if(cellToAttack instanceof ZombieCell) {
      Integer zombieHp = ((ZombieCell) cellToAttack).getHp();
      if(zombieHp<chomperState.getEnergy()) {
        
        environmentState.getWorld()[posToAttack.getX()][posToAttack.getY()] = new EmptyCell();
        environmentState.decrementChomperEnergy(zombieHp);
        environmentState.decrementZombiesAmount(1);
        chomperState.getKnownWorld()[posToAttack.getX()][posToAttack.getY()] = new EmptyCell();
        chomperState.decrementEnergy(zombieHp);
        chomperState.decrementZombiesAmount(1);
        return environmentState;
      }
    }
    
    return null;
  }
}
