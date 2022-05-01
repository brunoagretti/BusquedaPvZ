package busquedapvz;

import java.util.Arrays;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChomperAgentState extends SearchBasedAgentState {
	Cell[][] knownWorld;
	Integer energy;
	Integer zombiesAmount;
	Position position;
	private Position lastPos;

	public ChomperAgentState(Integer zombiesAmount) {
		knownWorld = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
		this.zombiesAmount = zombiesAmount;
		initState();
	}

	@Override
	public void updateState(Perception p) {

		ChomperPerception per = (ChomperPerception) p;
		energy = per.getChomperEnergy();
		zombiesAmount = per.getZombiesAmount();
		position = per.getChomperPosition();

		per.getSensedCells().forEach((Position pos, Cell cell) -> {
			if(cell instanceof ZombieCell) {
				for(int i=pos.getX()+1;i<PvzEnvironment.MAP_SIZE_X;i++) {
					knownWorld[i][pos.getY()] = new EmptyCell(new Position(i,pos.getY()), false);
				}
			}
			knownWorld[pos.getX()][pos.getY()] = cell;
			
		});
		
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		str.append("===========================================\n");
		str.append("Agent Energy = " + energy + "\n");
		str.append("Zombies left = " + zombiesAmount + "\n");
		str.append("Agent Position = (" + position.getX() + ", " + position.getY() + ")\n");
		str.append("Agent Map: \n");

		for (Integer j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
			for (Integer i = 0; i < PvzEnvironment.MAP_SIZE_X; i++) {
				if (knownWorld[i][j].containsAgent()) {
					str.append("@");
					str.append(knownWorld[i][j].toString());

				} else {
					str.append(" ");
					str.append(knownWorld[i][j].toString());

				}
				str.append(" ");
			}
			str.append("\n");
		}
		str.append("===========================================\n");
		
		return str.toString();
	}

	@Override
	public void initState() {
		// SET KNOWN WORLD TO UNKNOWN


      knownWorld = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
      for (Integer i = 0; i < PvzEnvironment.MAP_SIZE_X; i++) {
        for (Integer j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
          knownWorld[i][j] = new EmptyCell(new Position(i, j), false);
        }
      }
	  
		energy = RandomHandler.nextInt(RandomType.StartingAgentEnergy);
		position = new Position(0, RandomHandler.nextInt(RandomType.AgentPosition));
		if(position.getY() > 0 )
			lastPos = new Position(0, 0);
		else 
			lastPos = new Position(0,4);
		knownWorld[0][position.getY()].setContainsAgent(true);
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj instanceof ChomperAgentState) {
			ChomperAgentState state = (ChomperAgentState) obj;
			//System.out.println("Energia: " + (state.getEnergy() == this.energy) + ", Zombies: " + (state.getZombiesAmount() == this.zombiesAmount)  
			//		+ ", Mapa: " + Arrays.deepEquals(state.getKnownWorld(), knownWorld));
			if (state.getEnergy() == this.energy && state.getZombiesAmount() == this.zombiesAmount
				 && Arrays.deepEquals(state.getKnownWorld(), knownWorld) && state.position.equals(this.position)) {
				isEqual = true;
			}
		}
		return isEqual;
	}

	@Override
	public SearchBasedAgentState clone() {
		
		Cell newWorld[][] = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
		for(int i=0; i<PvzEnvironment.MAP_SIZE_X; i++) {
        	for(int j=0; j<PvzEnvironment.MAP_SIZE_Y; j++) {
        		newWorld[i][j] = knownWorld[i][j].clone();
            }
        }
		ChomperAgentState ret = new ChomperAgentState(newWorld, Integer.valueOf(energy),
				Integer.valueOf(zombiesAmount), new Position(position.getX(), position.getY()), new Position(lastPos.getX(), lastPos.getY()));
		
		return ret;
	}
	
	
	
	public boolean allMapSunflowered() {
		// for(int i=0; i<PvzEnvironment.MAP_SIZE_X; i++) {
		// for(int j=0; j<PvzEnvironment.MAP_SIZE_Y; j++) {
		// if(!(knownWorld[i][j] instanceof SunflowerCell)) {
		// return false;
		// }
		// }
		// }
		for (int j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
			if (!(knownWorld[0][j] instanceof SunflowerCell)) {
				
				return false;
			}
		}
		System.out.println("aca");
		return true;
	}
	
	public boolean noZombiesOnMap() {
		for (int i = 0; i < PvzEnvironment.MAP_SIZE_X; i++) {
			for (int j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
				
				if ((knownWorld[i][j] instanceof ZombieCell)) {
					System.out.println("ACA");
					return false;
					
				}
			}
		}
		return true;
	}

	public void decrementEnergy(Integer amount) {
		this.energy -= amount;
	}

	public void addEnergy(Integer amount) {
		this.energy += amount;
	}

	public void decrementZombiesAmount(Integer amount) {
		zombiesAmount -= amount;
	}

	public boolean checkPos() {
		System.out.println(lastPos.getY());
		if(position.equals(lastPos)) {
			System.out.println("nice");
			if(lastPos.getY()==0) {
				lastPos = new Position(0,4);
			}
			else
				lastPos = new Position(0,0);
			return true;
		}
		else {
			return false;
		}
	}

}
