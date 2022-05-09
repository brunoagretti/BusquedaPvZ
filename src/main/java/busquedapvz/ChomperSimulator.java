package busquedapvz;

import busquedapvz.graphics.PvzFrame;
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
	Integer totalZombies;
	
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

        System.out.println("----------------------------------------------------");
        System.out.println("--- " + this.getSimulatorName() + " ---");
        System.out.println("----------------------------------------------------");
        System.out.println();
        Perception perception;
        Action action;
        GoalBasedAgent agent;

        agent = (ChomperAgent) this.getAgents().firstElement();
        ChomperAgentState state = (ChomperAgentState) agent.getAgentState();
        System.out.println("The generated amount of zombies in the simulation is: " + environment.getEnvironmentState().getRemainingZombiesAmount()); 
       
		PvzFrame game = new PvzFrame(environment.getEnvironmentState());
		game.setResizable(true);

		SunflowerGoal energyGatheringGoal = new SunflowerGoal();
		
        do {
        	
            System.out.println("------------------------------------");
            System.out.println("Generating environment changes...");
            environment.generateSuns();
            
            // Spawns zombies
            environment.generateZombies();

            System.out.println("Sending perception to agent...");

            // After agent executes his action we should see if zombies Walk
            environment.walkZombies();
            if(environment.getEnvironmentState().getAgentFailed()) {
            	action=null;
            	break;
            }
            
            perception = this.getPercept();
            agent.see(perception);
            System.out.println("Perception: " + perception);

            System.out.println("Agent State: " + agent.getAgentState());
            System.out.println("Environment:\n" + environment);
            
            if(energyGatheringGoal.isGoalState(agent.getAgentState())) {
            	System.out.println("CAMBIO DE OBJETIVO");
            	((ChomperAgent) agent).changeObjective();
            }

            //Draw map
            game.addNewState(environment.getEnvironmentState());

            System.out.println("Asking the agent for an action...");
            action = agent.selectAction();

        
            

            if (action == null) {
                break;
            }

            System.out.println("Action returned: " + action);
            System.out.println();
            
            this.actionReturned(agent, action);
            if(energyGatheringGoal.isGoalState(agent.getAgentState())) {
            	System.out.println("CAMBIO DE OBJETIVO");
            	((ChomperAgent) agent).changeObjective();
            }
            
//            try {
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}  

            
        } while (!this.agentSucceeded(action) && !this.agentFailed(action));
        game.addNewState(environment.getEnvironmentState());
        //Mostrar botones de control
        game.setButtonsVisible(true);
        
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
	
    @Override
    public boolean agentFailed(Action actionReturned) {
        return this.environment.agentFailed(actionReturned);
    }

}
