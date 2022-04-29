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

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Z" + hp);
		
		
		return str.toString();
	}
	
	@Override
	public ZombieCell clone() {
      ZombieCell clone = (ZombieCell) super.clone();
      clone.setHp(Integer.valueOf(hp));
      clone.setWalkChance(Integer.valueOf(walkChance));
      return clone;
	}
    @Override
    public boolean equals(Object obj) {
    	return super.equals(obj) && (obj instanceof ZombieCell);
    }

}
