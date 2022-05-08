package busquedapvz;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import busquedapvz.utils.MapManager;
import frsf.cidisi.faia.state.EnvironmentState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
public class PvzEnvironmentState extends EnvironmentState implements Cloneable {

	Cell world[][];
	Position chomperPosition;
	Integer chomperEnergy;

	
	// Zombies that have to be killed for the simulation to end
	Integer remainingZombiesAmount;
	
	// Zombies that have not been added to the map yet
	Integer zombiesLeftToSpawn;

	Boolean agentFailed = false;
	HashSet<ZombieCell> zombiesOnMap;

	public PvzEnvironmentState(Cell[][] world) {
		zombiesOnMap = new HashSet<ZombieCell>();
		this.world = world;
		this.initState();
    }

    public PvzEnvironmentState() {
		zombiesOnMap = new HashSet<ZombieCell>();
        initWorld();
        this.initState();
    }
   
	@Override
	public void initState() {
		
		chomperEnergy = RandomHandler.nextInt(RandomType.StartingAgentEnergy); 
		remainingZombiesAmount = RandomHandler.nextInt(RandomType.TotalZombiesToSpawn);
		zombiesLeftToSpawn=Integer.valueOf(remainingZombiesAmount);
		
		chomperPosition = new Position(0, RandomHandler.nextInt(RandomType.AgentPosition));
		world[0][chomperPosition.getY()].setContainsAgent(true);
	}

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for(Integer j=0;j< PvzEnvironment.MAP_SIZE_Y;j++) {
        	for(Integer i=0;i< PvzEnvironment.MAP_SIZE_X;i++) {
        		if(world[i][j].containsAgent()) {
        			str.append("@");
        			str.append(world[i][j].toString());
        			
        		}else {
        			str.append(" ");
        			str.append(world[i][j].toString());
        			
        		}
            	str.append(" ");
            }
        	str.append("\n");
        }

        return str.toString();
    }

	public Cell[][] getWorld() {
		return world;
	}

	public void setWorld(Cell[][] world) {
		this.world = world;
	}
	
	public Position getChomperPosition() {
    return chomperPosition;
  }

  public void setChomperPosition(Position chomperPosition) {
    this.chomperPosition = chomperPosition;
  }

  public Integer getChomperEnergy() {
		return chomperEnergy;
	}

	public void setChomperEnergy(Integer chomperEnergy) {
		this.chomperEnergy = chomperEnergy;
	}
	
    private void initWorld(){
      world = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
      for (Integer i = 0; i < PvzEnvironment.MAP_SIZE_X; i++) {
        for (Integer j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
          world[i][j] = new EmptyCell(new Position(i,j),false);
        }
      }
    }

	public void updatePosition(Position oldPos, Position newPos) {
		 
		Cell contentToMove = world[oldPos.getX()][oldPos.getY()];
		world[oldPos.getX()][oldPos.getY()] = new EmptyCell(oldPos, false);
		world[newPos.getX()][newPos.getY()] = contentToMove;
		contentToMove.setPosition(newPos);
	}

	public boolean zombieOnPosition(Position pos) {
		if (world[pos.getX()][pos.getY()] instanceof ZombieCell)
			return true;
		else
			return false;
	}

	public Integer getZombiesOnLastCol() {
		Integer amount = 0;
		for(int j=0; j<PvzEnvironment.MAP_SIZE_Y; j++){
			if(world[PvzEnvironment.MAP_SIZE_X-1][j] instanceof ZombieCell)
				amount++;
		}
		
		return amount;
	}

	public void decrementZombiesAmount(Integer amount) {
		remainingZombiesAmount -= amount;
	}
	
	public void decrementZombiesToSpawn(Integer amount) {
		zombiesLeftToSpawn -= amount;
	}

    public void decrementChomperEnergy(Integer amount) {
      this.chomperEnergy -= amount;
    }
    
    public void addChomperEnergy(Integer amount) {
    	
        this.chomperEnergy += amount;
    }
    
    @Override
	public PvzEnvironmentState clone() {
    	PvzEnvironmentState newState = new PvzEnvironmentState(MapManager.copyOf(world), 
    			chomperPosition.clone(), Integer.valueOf(chomperEnergy), Integer.valueOf(remainingZombiesAmount), 
    			Integer.valueOf(remainingZombiesAmount), Boolean.valueOf(agentFailed), (HashSet<ZombieCell>) zombiesOnMap.clone());
    	return newState;
	}

	public void walkZombies() {
		Integer n;
		for (ZombieCell zombie : zombiesOnMap) {
			n = RandomHandler.nextInt(RandomType.ZombieWalk);
			// If there is a plant on the new position of the zombie it will be deleted with
			// all its suns
			if (n <= zombie.getWalkChance()) {
				Position newPos = zombie.getPosition().clone();
				newPos.decrementX();
				
				if (newPos.getX() < 0) {
					setAgentFailed(true);
					getWorld()[zombie.getPosition().getX()][zombie.getPosition()
							.getY()] = new EmptyCell(zombie.getPosition(), false);
				} else {
					if (!zombieOnPosition(newPos)) {
						zombie.setContainsAgent(getWorld()[newPos.getX()][newPos.getY()].containsAgent());
						moveZombie(zombie.getPosition(), newPos);
						
						//Resta energía del agente
						if(zombie.containsAgent()) {
							decrementChomperEnergy(2*zombie.getHp());
						}
						
						zombie.setWalkChance(34);
					}
				}
			}

			else {
				zombie.setWalkChance(zombie.getWalkChance() + 33);
			}

		}
	}
	
	public void moveZombie(Position oldPos, Position newPos) {
		updatePosition(oldPos, newPos);
	}

}
