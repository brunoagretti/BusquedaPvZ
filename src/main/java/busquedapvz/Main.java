package busquedapvz;

public class Main {

  public static void main(String[] args) {

    System.out.println("Hola mundo de pvz");
    
//    String[][] salutation = { {"Mr. ", "Mrs. ", "Ms. "}, {"Kumar"} };

//   ChomperAgent agent = new ChomperAgent(null);
   
    Cell[][] world = {
    		{new SunflowerCell(),new SunflowerCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new ZombieCell(2, new Position(4,1)),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new ZombieCell(5, new Position(6,3)),new EmptyCell(),new EmptyCell()},
			{new EmptyCell(),new EmptyCell(),new ZombieCell(1, new Position(2,4)),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell(),new EmptyCell()}
	};
    PvzEnvironmentState state = new PvzEnvironmentState(world);
    PvzEnvironment enviroment = new PvzEnvironment();
    enviroment.setEnvironmentState(state);
    System.out.println(state.toString());
    
//    System.out.println(new ChomperPerception(agent, enviroment));
    
  }

}
