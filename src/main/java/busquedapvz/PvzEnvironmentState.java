package busquedapvz;

import frsf.cidisi.faia.state.EnvironmentState;

public class PvzEnvironmentState extends EnvironmentState {
	
	private CellContent world[][];
	private Integer chomperPositionX;
	private Integer chomperPositionY;
	private Integer chomperEnergy;
	
	
	
	public PvzEnvironmentState(CellContent[][] world) {
        this.world = world;
        this.initState();
    }

    public PvzEnvironmentState() {
        initWorld();
        this.initState();
    }
    
	@Override
	public void initState() {
		// TODO Auto-generated method stub
		//Aca no iria la logica de los soles y demas, aca solo debemos inicializar
		
		chomperEnergy = RandomHandler.nextInt(RandomType.StartingAgentEnergy); 
		chomperPositionX = 0; 
		chomperPositionY = RandomHandler.nextInt(RandomType.AgentPosition);
		
		world[chomperPositionY][chomperPositionX].setContainsAgent(true);
	}

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();

        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		if(world[i][j].containsAgent()) {
        			str.append("@");
        			str.append(world[i][j].toString());
        			
        		}else {
        			str.append(" ");
        			str.append(world[i][j].toString());
        			
        		}
            	str.append(" ");
            }
        	str.append("\n");
        }

        return str.toString();
    }

	public CellContent[][] getWorld() {
		return world;
	}

	public void setWorld(CellContent[][] world) {
		this.world = world;
	}

	public Integer getChomperPositionX() {
		return chomperPositionX;
	}

	public void setChomperPositionX(Integer chomperPositionX) {
		this.chomperPositionX = chomperPositionX;
	}

	public Integer getChomperPositionY() {
		return chomperPositionY;
	}

	public void setChomperPositionY(Integer chomperPositionY) {
		this.chomperPositionY = chomperPositionY;
	}

	public Integer getChomperEnergy() {
		return chomperEnergy;
	}

	public void setChomperEnergy(Integer chomperEnergy) {
		this.chomperEnergy = chomperEnergy;
	}
	
    private void initWorld(){
    	world = new CellContent[PvzEnvironment.MAP_SIZE_Y][PvzEnvironment.MAP_SIZE_X];
        for(Integer i=0;i< PvzEnvironment.MAP_SIZE_Y;i++) {
        	for(Integer j=0;j< PvzEnvironment.MAP_SIZE_X;j++) {
        		world[i][j] = new EmptyCell();
            }
        }
    }

	public void updatePosition(Position oldPos, Position newPos) {
		CellContent contentToMove = world[oldPos.getX()][oldPos.getY()];
		world[oldPos.getX()][oldPos.getY()] = new EmptyCell();
		world[newPos.getX()][newPos.getY()] = contentToMove;
	}

	public boolean zombieOnPosition(Position pos) {
		if (world[pos.getX()][pos.getY()] instanceof ZombieEntity)
			return true;
		else
			return false;
	}

	public Integer getZombiesOnLastCol() {
		Integer amount = 0;
		for(int i=0; i<PvzEnvironment.MAP_SIZE_Y; i++){
			if(world[i][PvzEnvironment.MAP_SIZE_X-1] instanceof ZombieEntity)
				amount++;
		}
		
		return amount;
	}
	
	

}
