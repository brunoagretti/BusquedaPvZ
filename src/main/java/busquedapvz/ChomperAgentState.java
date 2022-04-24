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
			knownWorld[pos.getY()][pos.getX()] = cell;
		});

	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();

		str.append("===========================================\n");
		str.append("Agent State: \n");
		str.append("Agent Energy = " + energy + "\n");
		str.append("Zombies left = " + zombiesAmount + "\n");
		str.append("Agent Position = (" + position.getX() + ", " + position.getY() + ")\n");
		str.append("Agent Map = (" + position.getX() + ", " + position.getY() + ")\n");
		for (Integer i = 0; i < PvzEnvironment.MAP_SIZE_Y; i++) {
			for (Integer j = 0; j < PvzEnvironment.MAP_SIZE_X; j++) {
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
		Arrays.fill(knownWorld, new UnknownCell());
		energy = RandomHandler.nextInt(RandomType.StartingAgentEnergy);
		position = new Position(0, RandomHandler.nextInt(RandomType.AgentPosition));
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (obj instanceof ChomperAgentState) {
			ChomperAgentState state = (ChomperAgentState) obj;
			if (state.getEnergy() == this.energy && state.getZombiesAmount() == this.zombiesAmount
					&& state.getPosition().equals(this.position) && state.getKnownWorld().equals(this.knownWorld)) {
				isEqual = true;

			}
		}

		return isEqual;
	}

	@Override
	public SearchBasedAgentState clone() {
		
		Cell newWorld[][] = Arrays.copyOf(knownWorld, knownWorld.length);
 		
		ChomperAgentState ret = new ChomperAgentState(newWorld, Integer.valueOf(energy),
				Integer.valueOf(zombiesAmount), new Position(position.getX(), position.getY()));
		
		return ret;
	}
	
	
	public boolean noZombiesLeft() {
        if(zombiesAmount<1)
        	return true;
        else
        	return false;
    }

    public void decrementEnergy(Integer amount) {
      this.energy -= amount;
    }

    public void decrementZombiesAmount(Integer amount) {
      zombiesAmount -= amount;
    }


}
