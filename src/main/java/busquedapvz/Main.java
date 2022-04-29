package busquedapvz;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) {
   

    PvzEnvironment chomperEnvironment = new PvzEnvironment();
    
    ChomperAgent chomperAgent = new ChomperAgent(chomperEnvironment.getEnvironmentState().getZombiesAmount());
    
    ChomperSimulator simulator =
            new ChomperSimulator(chomperEnvironment, chomperAgent);
    
    simulator.start();


    
//    Cell[][] world = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
//    for(int i = 0 ; i<PvzEnvironment.MAP_SIZE_X ;i++) {
//      for(int j = 0 ; j<PvzEnvironment.MAP_SIZE_Y ;j++) {
//        world[i][j] = new EmptyCell(new Position(i, j), false);
//      }
//    }
//    world[8][4] = new ZombieCell(new Position(8, 4), false, 1, 34);
//    world[8][2] = new ZombieCell(new Position(8, 2), false, 1, 34);
//    world[0][3].setContainsAgent(true);
//    
//    Cell[][] world2 = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
//    for(int i = 0 ; i<PvzEnvironment.MAP_SIZE_X ;i++) {
//      for(int j = 0 ; j<PvzEnvironment.MAP_SIZE_Y ;j++) {
//        world2[i][j] = new EmptyCell(new Position(i, j), false);
//      }
//    }
//    world2[8][4] = new ZombieCell(new Position(8, 4), false, 1, 34);
//    world2[8][2] = new ZombieCell(new Position(8, 2), false, 1, 34);
//    world2[0][3].setContainsAgent(true);
//    
//    System.out.println(Arrays.deepEquals(world, world2));
  }

}
