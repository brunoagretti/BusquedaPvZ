package busquedapvz;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import busquedapvz.graphics.PvzFrame;
import busquedapvz.utils.MapManager;

public class Main {

  public static void main(String[] args) throws InterruptedException, IOException {
   

    PvzEnvironment chomperEnvironment = new PvzEnvironment();
    
    ChomperAgent chomperAgent = new ChomperAgent(chomperEnvironment.getEnvironmentState().getRemainingZombiesAmount());
    
    ChomperSimulator simulator =
            new ChomperSimulator(chomperEnvironment, chomperAgent);
    
    simulator.start();
	  
//	  PvzFrame game = new PvzFrame();
//	  game.setResizable(true);
	  
//	  Cell[][] map1 = MapManager.createEmptyWorld();
//	  map1[1][1].setContainsAgent(true);
//	  Cell[][] map2 = MapManager.createEmptyWorld();
//	  map2[1][2].setContainsAgent(true);
//	  Cell[][] map3 = MapManager.createEmptyWorld();
//	  map3[1][3].setContainsAgent(true);
//	  Cell[][] map4 = MapManager.createEmptyWorld();
//	  map4[1][4].setContainsAgent(true);
//	  Cell[][] map5 = MapManager.createEmptyWorld();
//	  map5[2][4].setContainsAgent(true);
//	  Cell[][] map6 = MapManager.createEmptyWorld();
//	  map6[3][4].setContainsAgent(true);
//	  Cell[][] map7 = MapManager.createEmptyWorld();
//	  map7[4][4].setContainsAgent(true);
//	  Cell[][] map8 = MapManager.createEmptyWorld();
//	  map8[3][4].setContainsAgent(true);
//	  
//	  game.drawTable(map1);
//	  Thread.sleep(1000);
//	  game.drawTable(map2);
//	  Thread.sleep(1000);
//	  game.drawTable(map3);
//	  Thread.sleep(1000);
//	  game.drawTable(map4);
//	  Thread.sleep(1000);
//	  game.drawTable(map5);
//	  Thread.sleep(1000);
//	  game.drawTable(map6);
//	  Thread.sleep(1000);
//	  game.drawTable(map7);
//	  Thread.sleep(1000);
//	  game.drawTable(map8);

  }


}
