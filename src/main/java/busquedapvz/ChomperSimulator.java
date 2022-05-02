package busquedapvz;

import busquedapvz.graphics.GamePanel;
import busquedapvz.graphics.GameWindow;
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
	PvzEnvironment environment;
	
	public ChomperSimulator(Environment environment, Agent agent) {
		super(environment, agent);
		this.environment = (PvzEnvironment) environment;
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
    	GameWindow game = new GameWindow(1280,720, environment.getEnvironmentState().getWorld());
    	game.setSize(1016, 599);
  	  	game.setResizable(true);
 
        System.out.println("----------------------------------------------------");
        System.out.println("--- " + this.getSimulatorName() + " ---");
        System.out.println("----------------------------------------------------");
        System.out.println();

        Perception perception;
        Action action;
        GoalBasedAgent agent;

        agent = (ChomperAgent) this.getAgents().firstElement();

        System.out.println("The generated amount of zombies in the simulation is: " + environment.getEnvironmentState().getZombiesAmount()); 
       

        do {

        	
            System.out.println("------------------------------------");
            System.out.println("Generating environment changes...");
            environment.generateSuns();
            
            // Spawns zombies with 50% chance
            environment.addZombies(generateZombiesAmmount());

            System.out.println("Sending perception to agent...");
            perception = this.getPercept();
            agent.see(perception);
            System.out.println("Perception: " + perception);

            System.out.println("Agent State: " + agent.getAgentState());
            System.out.println("Environment:\n" + environment);
            game.nextFrame(environment.getEnvironmentState().getWorld());
            

            
            System.out.println("Asking the agent for an action...");
            action = agent.selectAction();

         
      
            
            if (action == null) {
                break;
            }

            System.out.println("Action returned: " + action);
            System.out.println();

            this.actionReturned(agent, action);
            
            // After agent executes his action we should see if zombies Walk
            environment.walkZombies();
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } while (!this.agentSucceeded(action) && !this.agentFailed(action));

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
		
		// If zombies on last column are less than 3 we can spawn up to 3
		// If not, we need to reduce the amount to a value we can spawn
		if(zombiesOnLastCol>=3) {
			while(zombiesOnLastCol + amountToAdd > 5)
				amountToAdd--;
		}
		
		
		environment.getEnvironmentState().decrementZombiesAmount(amountToAdd);
		
		
		 return amountToAdd;
		
	}
	
    @Override
    public boolean agentFailed(Action actionReturned) {
        return this.environment.agentFailed(actionReturned);
    }

}
