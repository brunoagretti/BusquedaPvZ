package busquedapvz;

import java.util.ArrayList;
import java.util.Random;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.state.AgentState;

public class PvzEnvironment extends Environment {
	
	static final Integer MAP_SIZE_X = 9;
	static final Integer MAP_SIZE_Y = 5;
	ArrayList<ZombieEntity> zombiesOnMap;
	
	public PvzEnvironment() {
		zombiesOnMap = new ArrayList<ZombieEntity>();
		this.environmentState = new PvzEnvironmentState();
	}
	
	@Override
	public Perception getPercept() {
		// TODO Auto-generated method stub
		return null;
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
		CellContent[][] world = ((PvzEnvironmentState) this.environmentState).getWorld();
		
        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		if(world[i][j] instanceof SunflowerEntity) {
        			((SunflowerEntity) world[i][j]).addSuns(RandomHandler.nextInt(RandomType.SunSpawns));
        		}
            }
        }
	}
	
	public void addZombies(Integer amount) {
		Integer remainingZombies = amount;
		while (remainingZombies > 0) {
			Integer posY = RandomHandler.nextInt(RandomType.ZombiePosition);

			if (!(this.getEnvironmentState().getWorld()[MAP_SIZE_X - 1][posY] instanceof ZombieEntity)) {
				ZombieEntity zombieToAdd = new ZombieEntity(RandomHandler.nextInt(RandomType.ZombieHp),
						new Position(MAP_SIZE_X - 1, posY));
				this.getEnvironmentState().getWorld()[MAP_SIZE_X - 1][posY] = zombieToAdd;
				remainingZombies--;
			}

		}
	}
	
	// makes the (UNDEAD) zombies walk 1 step.
	public void walkZombies() {
		//Check for each zombie on list
		Integer n;
		for(ZombieEntity zombie:zombiesOnMap) {
			n = RandomHandler.nextInt(RandomType.ZombieWalk);
			
			// If there is a plant on the new position of the zombie it will be deleted with all its suns
		    if(n <= zombie.getWalkChance()) {
				Position newPos = zombie.getPosition().clone();
				newPos.decrementX();
				if(newPos.getX() < 0)
					// TODO: Fire event.
				
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

	public ArrayList<ZombieEntity> getZombiesOnMap() {
		return zombiesOnMap;
	}

}
