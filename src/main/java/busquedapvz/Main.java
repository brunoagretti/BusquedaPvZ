package busquedapvz;

import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
   
//    Cell[][] world = {
//    		{new SunflowerCell(),new SunflowerCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
//			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new ZombieCell(2, new Position(4,1)),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
//			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
//			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new ZombieCell(5, new Position(6,3)),new EmptyCell(),new EmptyCell()},
//			{new EmptyCell(),new EmptyCell(),new ZombieCell(1, new Position(2,4)),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()}
//	};

    PvzEnvironment chomperEnvironment = new PvzEnvironment();
    
    ChomperAgent chomperAgent = new ChomperAgent(chomperEnvironment.getEnvironmentState().getZombiesAmount());
    
    ChomperSimulator simulator =
            new ChomperSimulator(chomperEnvironment, chomperAgent);
    
    simulator.start();

//    HashMap<Position, Cell> sensedCells = new HashMap<Position, Cell>();
//    sensedCells.put(new Position(1,1), new EmptyCell(new Position(1,1), true));
//    
//    ChomperPerception ret = new ChomperPerception();
//    ret.setSensedCells(sensedCells);
//    
//    System.out.println(ret.getSensedCells());
//    System.out.println(ret.getSensedCells().get(new Position(1,1)));
//    
////    System.out.println((new Position(1,1)).equals(new Position(1,1)));
    
  }

}
