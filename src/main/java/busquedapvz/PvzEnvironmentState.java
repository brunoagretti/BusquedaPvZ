package busquedapvz;

import java.util.Arrays;
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
public class PvzEnvironmentState extends EnvironmentState {
	
	 Cell world[][];
	 Position chomperPosition;
	 Integer chomperEnergy;
	 Integer zombiesAmount;//Zombies that will be added
	 Integer spawnedZombies;
	
	Boolean agentFailed = false;
	
	public PvzEnvironmentState(Cell[][] world) {
        this.world = world;
        this.initState();
    }

    public PvzEnvironmentState() {
        initWorld();
        this.initState();
    }
   
	@Override
	public void initState() {
		
		chomperEnergy = RandomHandler.nextInt(RandomType.StartingAgentEnergy); 
		zombiesAmount = RandomHandler.nextInt(RandomType.ZombieAmount);
		spawnedZombies=0;
		
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
		zombiesAmount -= amount;
	}
	
	public void addSpawnedZombies(Integer amount) {
		spawnedZombies += amount;
	}

    public void decrementChomperEnergy(Integer amount) {
      this.chomperEnergy -= amount;
    }
    
    public void addChomperEnergy(Integer amount) {
    	
        this.chomperEnergy += amount;
    }

	

}
