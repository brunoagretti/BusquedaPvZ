package busquedapvz;

public class ZombieEntity extends CellContent{
	

	String name;
	Integer hp;
	Integer walkChance;
	Position position;
	
	public ZombieEntity(Integer hp, Position p) {
		super();
		this.hp = hp;
		this.walkChance = 34;
		this.position = p;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Z" + hp);
		
		
		return str.toString();
	}
	
	public Integer getWalkChance() {
		return walkChance;
	}
	
	public void setWalkChance(Integer walkChance) {
		this.walkChance = walkChance;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}
