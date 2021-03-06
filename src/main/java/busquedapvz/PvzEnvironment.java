package busquedapvz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class PvzEnvironment extends Environment {

	public static final Integer MAP_SIZE_X = 9;
	public static final Integer MAP_SIZE_Y = 5;


	public PvzEnvironment() {
		this.environmentState = new PvzEnvironmentState();
	}

	@Override
	public Perception getPercept() {

		ChomperPerception ret = new ChomperPerception();

		PvzEnvironmentState environmentState = this.getEnvironmentState();

		Integer chomperPositionX = environmentState.getChomperPosition().getX();
		Integer chomperPositionY = environmentState.getChomperPosition().getY();

		Cell[][] actualEnvironmentState = environmentState.getWorld();

		ret.zombiesAmount = environmentState.getRemainingZombiesAmount();
		ret.chomperEnergy = getEnvironmentState().getChomperEnergy();
	
		ret.chomperPosition = environmentState.getChomperPosition();
		for (Integer i = chomperPositionX; i < PvzEnvironment.MAP_SIZE_X; i++) {

			ret.sensedCells.put(new Position(i, chomperPositionY), actualEnvironmentState[i][chomperPositionY].clone());

			if (!(actualEnvironmentState[i][chomperPositionY] instanceof EmptyCell || actualEnvironmentState[i][chomperPositionY] instanceof SunflowerCell)) {
				break;
			}
		}
		for (Integer i = chomperPositionX; i >= 0; i--) {

			ret.sensedCells.put(new Position(i, chomperPositionY), actualEnvironmentState[i][chomperPositionY].clone());

			if (!(actualEnvironmentState[i][chomperPositionY] instanceof EmptyCell || actualEnvironmentState[i][chomperPositionY] instanceof SunflowerCell)) {
				break;
			}
		}
		for (Integer j = chomperPositionY; j < PvzEnvironment.MAP_SIZE_Y; j++) {

			ret.sensedCells.put(new Position(chomperPositionX, j), actualEnvironmentState[chomperPositionX][j].clone());

			if (!(actualEnvironmentState[chomperPositionX][j] instanceof EmptyCell || actualEnvironmentState[chomperPositionX][j] instanceof SunflowerCell)) {
				break;
			}
		}
		for (Integer j = chomperPositionY; j >= 0; j--) {

			ret.sensedCells.put(new Position(chomperPositionX, j), actualEnvironmentState[chomperPositionX][j].clone());

			if (!(actualEnvironmentState[chomperPositionX][j] instanceof EmptyCell || actualEnvironmentState[chomperPositionX][j] instanceof SunflowerCell )) {
				break;
			}
		}
		ret.sensedCells.get(ret.chomperPosition).setContainsAgent(true);

		return ret;
	}

	@Override
	public PvzEnvironmentState getEnvironmentState() {
		return (PvzEnvironmentState) super.getEnvironmentState();
	}

	public void generateSuns() {
		Cell[][] world = ((PvzEnvironmentState) this.environmentState).getWorld();

		for (Integer i = 0; i < PvzEnvironment.MAP_SIZE_X; i++) {
			for (Integer j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
				if (world[i][j] instanceof SunflowerCell) {
					((SunflowerCell) world[i][j]).addSuns(RandomHandler.nextInt(RandomType.SunSpawns));
				}
			}
		}
	}

	public void generateZombies() {
		PvzEnvironmentState state = this.getEnvironmentState();
		
		//Max zombies that will spawn in this cycle
		Integer spawningZombies = RandomHandler.nextInt(RandomType.ZombieSpawns);
		
		while (state.getZombiesLeftToSpawn()>0 && spawningZombies>0) {

			Integer posY = RandomHandler.nextInt(RandomType.ZombiePosition);
			if (!(state.getWorld()[MAP_SIZE_X - 1][posY] instanceof ZombieCell)) {
				ZombieCell zombieToAdd = new ZombieCell(new Position(MAP_SIZE_X - 1, posY), false,
						RandomHandler.nextInt(RandomType.ZombieHp), 34);
				state.getWorld()[MAP_SIZE_X - 1][posY] = zombieToAdd;
				state.getZombiesOnMap().add(zombieToAdd);
				spawningZombies--;
				state.decrementZombiesToSpawn(1);
			}
		}
	}

	// Fires the chance of zombies walking 1 step.
	public void walkZombies() {
		// Check for each zombie on list
		
		getEnvironmentState().walkZombies();

	}




	@Override
	public boolean agentFailed(Action actionReturned) {
		PvzEnvironmentState state = this.getEnvironmentState();
		return (state.getAgentFailed() || state.getChomperEnergy() < 1);
	}

	@Override
	public String toString() {
		return getEnvironmentState().toString();
	}
	
}
