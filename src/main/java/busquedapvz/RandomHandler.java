package busquedapvz;

import java.util.Random;

public class RandomHandler {
	
	public static Integer nextInt(RandomType type) {
		Integer n = 0;
		Random r = new Random();
		
		switch (type){
		case TotalZombiesToSpawn:
			// Must be between 5 and 20 TODO
			n = 3;
			break;
			
		case ZombieSpawns:
			// Will spawn between 0 and 3 zombies on a perception cycle
			n = r.nextInt(4);
			break;
			
		case StartingAgentEnergy:
			n = r.nextInt(18)+2;
			break;
			
		case SunSpawns:
			n = r.nextInt(3) + 1;
			break;
			
		case ZombieWalk:
			n = r.nextInt(100)+1;	
			break;
			
		case ZombiePosition:
			n = r.nextInt(5);
			break;
		
		case ZombieHp:
			n = r.nextInt(5)+1;
			break;
		
		case AgentPosition:
			n = r.nextInt(5);
//			n=4;
			break;
			
			
		default:
			System.out.println("WRONG PARAMETER: " + type);
		}
		
		
		return n;
	}
	
		
}
