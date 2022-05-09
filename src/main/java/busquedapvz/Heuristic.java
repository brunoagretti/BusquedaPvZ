package busquedapvz;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristic implements IEstimatedCostFunction {

    @Override
    public double getEstimatedCost(NTree node) {
        ChomperAgentState chomperState = (ChomperAgentState) node.getAgentState();

        return (chomperState.getCeldasVisitadas() +
        		chomperState.getZombiesAmount()*5+chomperState.getUnplantedCellsAmount());
    }

}
