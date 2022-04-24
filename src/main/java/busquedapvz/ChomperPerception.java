package busquedapvz;

import java.util.HashMap;
import java.util.Map;
import frsf.cidisi.faia.agent.Agent;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChomperPerception extends Perception{

	Map<Position, Cell> sensedCells;
	Integer chomperEnergy;
	Integer zombiesAmount;
	Position chomperPosition;
	
	public ChomperPerception() {
	  sensedCells = new HashMap<Position, Cell>();
	}
	
	public ChomperPerception(Agent agent, Environment environment) {
		super(agent, environment);
		sensedCells = new HashMap<Position, Cell>();
	}
	

	@Override
	public void initPerception(Agent agent, Environment environment) {
		initSensor();
		
		PvzEnvironment pvzEnvironment = (PvzEnvironment) environment;
		PvzEnvironmentState environmentState =
				pvzEnvironment.getEnvironmentState();
		
		Integer chomperPositionX = environmentState.getChomperPosition().getX();
		Integer chomperPositionY = environmentState.getChomperPosition().getY();
		
		Cell[][] actualEnvironmentState = environmentState.getWorld();
		
		

        for (Integer i = chomperPositionX; i < PvzEnvironment.MAP_SIZE_X; i++) {

          sensedCells.put(new Position(i, chomperPositionY),
              actualEnvironmentState[i][chomperPositionY]);

          if (!(actualEnvironmentState[i][chomperPositionY] instanceof EmptyCell)) {
            break;
          }
        }
        for (Integer i = chomperPositionX; i >= 0; i--) {

          sensedCells.put(new Position(i, chomperPositionY),
              actualEnvironmentState[i][chomperPositionY]);

          if (!(actualEnvironmentState[i][chomperPositionY] instanceof EmptyCell)) {
            break;
          }
        }
        for (Integer j = chomperPositionY; j < PvzEnvironment.MAP_SIZE_Y; j++) {

          sensedCells.put(new Position(chomperPositionX, j),
              actualEnvironmentState[chomperPositionX][j]);


          if (!(actualEnvironmentState[chomperPositionX][j] instanceof EmptyCell)) {
            break;
          }
        }
        for (Integer j = chomperPositionY; j >= 0; j--) {

          sensedCells.put(new Position(chomperPositionX, j),
              actualEnvironmentState[chomperPositionX][j]);

          if (!(actualEnvironmentState[chomperPositionX][j] instanceof EmptyCell)) {
            break;
          }
        }
        sensedCells.get(new Position(chomperPositionX,chomperPositionY)).setContainsAgent(true);
      }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        sensedCells.forEach((Position pos,Cell cell)->{
          str.append("Sensed cell at ("+pos.getX() + ", " + pos.getY() + "): ");
          str.append(cell + "\n");
        });

        return str.toString();
    }
    
    private void initSensor(){
    	sensedCells = new HashMap<Position, Cell>();
    }



}
