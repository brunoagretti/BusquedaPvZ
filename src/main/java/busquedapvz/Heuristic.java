package busquedapvz;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction {

	@Override
	public double getEstimatedCost(NTree node) {
		ChomperAgentState chomperState = (ChomperAgentState) node
				.getAgentState();
		Cell[][] map = chomperState.getKnownWorld();

		return (chomperState.getCeldasVisitadas()
				+ chomperState.getZombiesAmount() * 5
				+ chomperState.getUnplantedCellsAmount()
				+ zombiesOnRow(0, map) * 9 
				+ zombiesOnRow(1, map) * 8
				+ zombiesOnRow(2, map) * 7
				+ zombiesOnRow(3, map) * 6
				+ zombiesOnRow(4, map) * 5
				+ zombiesOnRow(5, map) * 4
				+ zombiesOnRow(6, map) * 3
				+ zombiesOnRow(7, map) * 2
				+ zombiesOnRow(8, map) * 1);
	}

	private Integer zombiesOnRow(Integer column, Cell[][] map) {
		Integer count = 0;
		for (int j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
			if (map[column][j] instanceof ZombieCell) {
				count++;
			}
		}
		return count;
	}

}
