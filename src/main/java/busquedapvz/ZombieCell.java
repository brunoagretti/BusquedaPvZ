package busquedapvz;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
