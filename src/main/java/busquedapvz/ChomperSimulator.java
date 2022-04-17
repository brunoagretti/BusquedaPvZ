package busquedapvz;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.GoalBasedAgent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.environment.Environment;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;
import frsf.cidisi.faia.simulator.events.EventType;
import frsf.cidisi.faia.simulator.events.SimulatorEventNotifier;
import frsf.cidisi.faia.state.AgentState;

public class ChomperSimulator extends SearchBasedAgentSimulator {

	Integer zombiesAmount;
	
	public ChomperSimulator(Environment environment, Agent agent) {
		super(environment, agent);
	}
	
    @Override
    public boolean agentSucceeded(Action actionReturned) {
        SearchBasedAgent sa = (SearchBasedAgent) getAgents().firstElement();
        Problem p = sa.getProblem();
        GoalTest gt = p.getGoalState();
        AgentState aSt = p.getAgentState();

        return gt.isGoalState(aSt);
    }
    
    @Override
    public void start() {

        System.out.println("----------------------------------------------------");
        System.out.println("--- " + this.getSimulatorName() + " ---");
        System.out.println("----------------------------------------------------");
        System.out.println();

        Perception perception;
        Action action;
        GoalBasedAgent agent;

        agent = (ChomperAgent) this.getAgents().firstElement();
        zombiesAmount = RandomHandler.nextInt(RandomType.ZombieAmount);
        System.out.println("The generated amount of zombies in the simulation is: " + zombiesAmount); 
        /*
         * Simulation starts. The environment sends perceptions to the agent, and
         * it returns actions. The loop condition evaluation is placed at the end.
         * This works even when the agent starts with a goal state (see agentSucceeded
         * method in the SearchBasedAgentSimulator).
         */
        do {

        	
            System.out.println("------------------------------------");
            System.out.println("Generating environment changes...");
            ((PvzEnvironment) environment).generateSuns();
            
            // Spawns zombies with 50% chance
            ((PvzEnvironment) environment).addZombies(generateZombiesAmmount());
            
            System.out.println("Sending perception to agent...");
            perception = this.getPercept();
            agent.see(perception);
            System.out.println("Perception: " + perception);

            System.out.println("Agent State: " + agent.getAgentState());
            System.out.println("Environment: " + environment);

            System.out.println("Asking the agent for an action...");
            action = agent.selectAction();

            // After agent executes his action we should see if zombies Walk
            ((PvzEnvironment) environment).walkZombies();
            
            if (action == null) {
                break;
            }

            System.out.println("Action returned: " + action);
            System.out.println();

            this.actionReturned(agent, action);

        } while (!this.agentSucceeded(action) && !this.agentFailed(action) && zombiesAmount>0);

        // Check what happened, if agent has reached the goal or not.
        if (this.agentSucceeded(action)) {
            System.out.println("Agent has reached the goal!");
        } else {
            System.out.println("ERROR: The simulation has finished, but the agent has not reached his goal.");
        }

        // Leave a blank line
        System.out.println();

        // FIXME: This call can be moved to the Simulator class
        this.environment.close();

        // Launch simulationFinished event
        SimulatorEventNotifier.runEventHandlers(EventType.SimulationFinished, null);
    }

	private Integer generateZombiesAmmount() {
		Integer amountToAdd = RandomHandler.nextInt(RandomType.ZombieSpawns); 
		Integer zombiesOnLastCol = ((PvzEnvironmentState) environment.getEnvironmentState()).getZombiesOnLastCol();
		
		// If zombies on last column are less than 3 we can spawn the amount with want
		// If not, we need to reduce the amount to a value we can spawn
		if(zombiesOnLastCol>=3) {
			while(zombiesOnLastCol + amountToAdd > 5)
				amountToAdd--;
		}
		
		zombiesAmount =- amountToAdd;
		
		
		 return amountToAdd;
		
	}

}
