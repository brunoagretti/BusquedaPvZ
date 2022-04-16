package busquedapvz;

public class Main {

  public static void main(String[] args) {

    System.out.println("Hola mundo de pvz");
    
//    String[][] salutation = { {"Mr. ", "Mrs. ", "Ms. "}, {"Kumar"} };

   ChomperAgent agent = new ChomperAgent();
   
    CellContent[][] world = {
    		{new SunflowerEntity(),new SunflowerEntity(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new ZombieEntity(1),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new ZombieEntity(3),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new ZombieEntity(2),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()}
	};
    PvzEnvironmentState state = new PvzEnvironmentState(world);
    PvzEnvironment enviroment = new PvzEnvironment();
    enviroment.setEnvironmentState(state);
    System.out.println(state.toString());
    
    System.out.println(new ChomperPerception(agent, enviroment));
    
  }

}
