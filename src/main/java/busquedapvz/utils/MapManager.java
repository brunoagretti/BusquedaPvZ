package busquedapvz.utils;

import busquedapvz.Cell;
import busquedapvz.EmptyCell;
import busquedapvz.Position;
import busquedapvz.PvzEnvironment;

public class MapManager {

	  public static Cell[][] createEmptyWorld(){
		  Cell[][] world = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
	      for (Integer i = 0; i < PvzEnvironment.MAP_SIZE_X; i++) {
	        for (Integer j = 0; j < PvzEnvironment.MAP_SIZE_Y; j++) {
	          world[i][j] = new EmptyCell(new Position(i,j),false);
	        }
	      }
	      return world;
	    }
	  
	  public static Cell[][] copyOf(Cell[][] world){
			Cell newWorld[][] = new Cell[PvzEnvironment.MAP_SIZE_X][PvzEnvironment.MAP_SIZE_Y];
			for(int i=0; i<PvzEnvironment.MAP_SIZE_X; i++) {
	        	for(int j=0; j<PvzEnvironment.MAP_SIZE_Y; j++) {
	        		newWorld[i][j] = world[i][j].clone();
	            }
	        }
			return newWorld;
	  }
	
}
