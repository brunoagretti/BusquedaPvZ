package busquedapvz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZombieCell extends Cell implements Cloneable{
  
	Integer hp;
	Integer walkChance;
	
	public ZombieCell( Position position, Boolean containsAgent, Integer hp, Integer walkChance) {
		super(position, containsAgent);
		this.hp = hp;
		this.walkChance = walkChance;
	}
	
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
	
	@Override
	public ZombieCell clone() {		
		return new ZombieCell(new Position(position.getX(), position.getY()), Boolean.valueOf(containsAgent), Integer.valueOf(hp), Integer.valueOf(walkChance));
	}

}
