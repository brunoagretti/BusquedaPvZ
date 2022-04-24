package busquedapvz;

import java.util.ArrayList;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

public class PvzEnvironment extends Environment {
	
	static final Integer MAP_SIZE_X = 9;
	static final Integer MAP_SIZE_Y = 5;
	ArrayList<ZombieCell> zombiesOnMap;
	
	public PvzEnvironment() {
		zombiesOnMap = new ArrayList<ZombieCell>();
		this.environmentState = new PvzEnvironmentState();
	}
	
	@Override
	public Perception getPercept() {

		ChomperPerception newPerception = new ChomperPerception();
		
		Integer chomperPositionX = getEnvironmentState().getChomperPosition().getX();
		Integer chomperPositionY = getEnvironmentState().getChomperPosition().getY();
		
		Cell[][] world = getEnvironmentState().getWorld();
		
		newPerception.setZombieAmountOnMap(getZombiesOnMap().size());
		
		Cell[][] perceptedWorld = createMap();
				
		for(Integer i=chomperPositionX;i< PvzEnvironment.MAP_SIZE_X;i++) {
			
			perceptedWorld[chomperPositionY][i]=world[chomperPositionY][i];
			
			if(!(world[chomperPositionY][i] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer i=chomperPositionX;i>=0;i--) {
			
			perceptedWorld[chomperPositionY][i]=world[chomperPositionY][i];
			
			if(!(world[chomperPositionY][i] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer j=chomperPositionY;j< PvzEnvironment.MAP_SIZE_Y;j++) {
			
			perceptedWorld[j][chomperPositionX]=world[j][chomperPositionX];
			
			if(!(world[j][chomperPositionX] instanceof EmptyCell)) { 
				break;
			}
		}
		for(Integer j=chomperPositionY;j>=0;j--) {
			
			perceptedWorld[j][chomperPositionX]=world[j][chomperPositionX];
			
			if(!(world[j][chomperPositionX] instanceof EmptyCell)) { 
				break;
			}
		}
		
		perceptedWorld[chomperPositionY][chomperPositionX].setContainsAgent(true);
		
		newPerception.setPerceptedWorld(perceptedWorld);
		
		return newPerception;
	}
	
	@Override
	public PvzEnvironmentState getEnvironmentState() {
		return (PvzEnvironmentState) super.getEnvironmentState();
	}
	
	@Override
	public void updateState(AgentState ast, Action action) {
		super.updateState(ast, action);
		
		
	}
	
	public void generateSuns() {
		Cell[][] world = ((PvzEnvironmentState) this.environmentState).getWorld();
		
        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		if(world[i][j] instanceof SunflowerCell) {
        			((SunflowerCell) world[i][j]).addSuns(RandomHandler.nextInt(RandomType.SunSpawns));
        		}
            }
        }
	}
	
	public void addZombies(Integer amount) {
		Integer remainingZombies = amount;
		while (remainingZombies > 0) {
			Integer posY = RandomHandler.nextInt(RandomType.ZombiePosition);

			if (!(this.getEnvironmentState().getWorld()[MAP_SIZE_X - 1][posY] instanceof ZombieCell)) {
				ZombieCell zombieToAdd = new ZombieCell(RandomHandler.nextInt(RandomType.ZombieHp),
						new Position(MAP_SIZE_X - 1, posY));
				this.getEnvironmentState().getWorld()[MAP_SIZE_X - 1][posY] = zombieToAdd;
				remainingZombies--;
			}

		}
	}
	
	// Fires the chance of zombies walking 1 step.
	public void walkZombies() {
		//Check for each zombie on list
		Integer n;
		for(ZombieCell zombie:zombiesOnMap) {
			n = RandomHandler.nextInt(RandomType.ZombieWalk);
			
			// If there is a plant on the new position of the zombie it will be deleted with all its suns
		    if(n <= zombie.getWalkChance()) {
				Position newPos = zombie.getPosition().clone();
				newPos.decrementX();
				if(newPos.getX() < 0)
					// TODO: Fire fail event.
				
				if (!((PvzEnvironmentState) this.environmentState).zombieOnPosition(newPos)) {
					moveZombie(zombie.getPosition(), newPos);
					zombie.setWalkChance(34);
				} 
		    }
		    
		    else {
		    	zombie.setWalkChance(zombie.getWalkChance()+33);
		    }
		    
		}	
	}
	
	public void moveZombie(Position oldPos, Position newPos) {
		((PvzEnvironmentState) this.environmentState).updatePosition(oldPos, newPos);
	}

	public ArrayList<ZombieCell> getZombiesOnMap() {
		return zombiesOnMap;
	}
	
    private Cell[][] createMap(){
    	Cell[][] world = new Cell[PvzEnvironment.MAP_SIZE_Y][PvzEnvironment.MAP_SIZE_X];
        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		world[i][j] = new UnknownCell();
            }
        }
        return world;
    }

}
