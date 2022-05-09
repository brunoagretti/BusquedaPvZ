package busquedapvz;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import busquedapvz.actions.AttackDown;
import busquedapvz.actions.AttackLeft;
import busquedapvz.actions.AttackRight;
import busquedapvz.actions.AttackUp;
import busquedapvz.actions.GoDown;
import busquedapvz.actions.GoLeft;
import busquedapvz.actions.GoRight;
import busquedapvz.actions.GoUp;
import busquedapvz.actions.PlantSunflower;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.Strategy;
import frsf.cidisi.faia.solver.search.UniformCostSearch;
import frsf.cidisi.faia.solver.search.IStepCostFunction;


public class ChomperAgent extends SearchBasedAgent {
	private Vector<SearchAction> operators;
	public ChomperAgent(Integer zombiesAmount) {
		// The Chomper Goal
        SunflowerGoal goal = new SunflowerGoal();

        // The Chomper Agent State
        ChomperAgentState ChomperState = new ChomperAgentState(zombiesAmount);
        this.setAgentState(ChomperState);
        ChomperState.setObChanged(false);
        // Create the operators
        operators = new Vector<SearchAction>();
        operators.addElement(new AttackDown());
        operators.addElement(new AttackUp());
        operators.addElement(new AttackLeft());
        operators.addElement(new AttackRight());
        operators.addElement(new GoRight());
        operators.addElement(new GoUp());
        operators.addElement(new GoDown());
        operators.addElement(new GoLeft());
        operators.addElement(new PlantSunflower());
        // Create the Problem which the Chomper will resolve
        Problem problem = new Problem(goal, ChomperState, operators);
        this.setProblem(problem);
	}
	
  @Override
  public void see(Perception p) {
    this.getAgentState().updateState(p);
  }

	@Override
	public Action selectAction() {
		// Create the search strategy
		Strategy strategy;
		ChomperAgentState state = (ChomperAgentState) getAgentState();
		IStepCostFunction costFunction = new CostFunction();
		if (!state.getObChanged()) {
			strategy = new BreathFirstSearch();
//		 strategy = new UniformCostSearch(costFunction);
//		 strategy = new DepthFirstSearch();
		}else {
			strategy = new BreathFirstSearch();
//			 strategy = new DepthFirstSearch();
//			 strategy = new UniformCostSearch(costFunction);
		}

		/**
		 * Another search strategy examples:
		 * 
		 * Depth First Search: DepthFirstSearch strategy = new
		 * DepthFirstSearch();
		 * 
		 * Breath First Search: BreathFirstSearch strategy = new
		 * BreathFirstSearch();
		 * 
		 * Uniform Cost: IStepCostFunction costFunction = new CostFunction();
		 * UniformCostSearch strategy = new UniformCostSearch(costFunction);
		 * 
		 * A Star Search: IStepCostFunction cost = new CostFunction();
		 * IEstimatedCostFunction heuristic = new Heuristic(); AStarSearch
		 * strategy = new AStarSearch(cost, heuristic);
		 * 
		 * Greedy Search: IEstimatedCostFunction heuristic = new Heuristic();
		 * GreedySearch strategy = new GreedySearch(heuristic);
		 */

		// Create a Search object with the strategy
		Search searchSolver = new Search(strategy);

		/*
		 * Generate an XML file with the search tree. It can also be generated
		 * in other formats like PDF with PDF_TREE
		 */
		searchSolver.setVisibleTree(Search.EFAIA_TREE);

		// Set the Search searchSolver.
      this.setSolver(searchSolver);

      // Ask the solver for the best action
      Action selectedAction = null;
      try {
          selectedAction =
                  this.getSolver().solve(new Object[]{this.getProblem()});
      } catch (Exception ex) {
          Logger.getLogger(ChomperAgent.class.getName()).log(Level.SEVERE, null, ex);
      }

		// Return the selected action
		return selectedAction;
	}

	public void changeObjective() {
		
		this.setProblem(new Problem(new ChomperGoal(2),
				((ChomperAgentState) getAgentState()), operators));
		((ChomperAgentState) getAgentState()).setObChanged(true);
		
	}
	

}
