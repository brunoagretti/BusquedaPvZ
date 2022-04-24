package busquedapvz;

public class ZombieCell extends Cell{
  
	Integer hp;
	Integer walkChance;
	
	public ZombieCell(Integer hp, Position p) {
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
	
	

	
}
