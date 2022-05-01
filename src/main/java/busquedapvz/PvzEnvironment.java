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
	Set<ZombieCell> zombiesOnMap;

	public PvzEnvironment() {
		zombiesOnMap = new HashSet<ZombieCell>();
		this.environmentState = new PvzEnvironmentState();
	}

	@Override
	public Perception getPercept() {

		ChomperPerception ret = new ChomperPerception();

		PvzEnvironmentState environmentState = this.getEnvironmentState();

		Integer chomperPositionX = environmentState.getChomperPosition().getX();
		Integer chomperPositionY = environmentState.getChomperPosition().getY();

		Cell[][] actualEnvironmentState = environmentState.getWorld();

		ret.zombiesAmount = environmentState.getZombiesAmount();
		ret.chomperEnergy = getEnvironmentState().getChomperEnergy();
		System.out.println("Energia enviada: " + ret.chomperEnergy);
	
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
					System.out.println("Soles:" + ((SunflowerCell) world[i][j]).getSunQuantity() );
				}
			}
		}
	}

	public void addZombies(Integer amount) {
		Integer remainingZombies = amount;
		while (remainingZombies > 0) {
			Integer posY = RandomHandler.nextInt(RandomType.ZombiePosition);

			if (!(this.getEnvironmentState().getWorld()[MAP_SIZE_X - 1][posY] instanceof ZombieCell)) {
				ZombieCell zombieToAdd = new ZombieCell(new Position(MAP_SIZE_X - 1, posY), false,
						RandomHandler.nextInt(RandomType.ZombieHp), 34);
				this.getEnvironmentState().getWorld()[MAP_SIZE_X - 1][posY] = zombieToAdd;
				zombiesOnMap.add(zombieToAdd);
				remainingZombies--;
			}

		}

	}

	// Fires the chance of zombies walking 1 step.
	public void walkZombies() {
		// Check for each zombie on list
		Integer n;
		for (ZombieCell zombie : zombiesOnMap) {
			n = RandomHandler.nextInt(RandomType.ZombieWalk);
			// If there is a plant on the new position of the zombie it will be deleted with
			// all its suns
			if (n <= zombie.getWalkChance()) {
				Position newPos = zombie.getPosition().clone();
				newPos.decrementX();

				if (newPos.getX() < 0) {
					getEnvironmentState().setAgentFailed(true);
					getEnvironmentState().getWorld()[zombie.getPosition().getX()][zombie.getPosition()
							.getY()] = new EmptyCell(zombie.getPosition(), false);
				} else {
					if (!((PvzEnvironmentState) this.environmentState).zombieOnPosition(newPos)) {
						moveZombie(zombie.getPosition(), newPos);
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
		((PvzEnvironmentState) this.environmentState).updatePosition(oldPos, newPos);
	}

	public Set<ZombieCell> getZombiesOnMap() {
		return zombiesOnMap;
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
