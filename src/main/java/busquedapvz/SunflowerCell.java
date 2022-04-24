package busquedapvz;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SunflowerCell extends Cell implements Cloneable{
	
	Integer sunQuantity;
	
	public SunflowerCell(Position pos, Boolean containsAgent, Integer sunQuantity) {
		super(pos, containsAgent);
		this.sunQuantity = sunQuantity;
	}

	public SunflowerCell() {
		super();
		this.sunQuantity = 0;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("S" + sunQuantity);
		
		
		return str.toString();
	}
	
	public void addSuns(Integer amount) {
		this.sunQuantity+=amount;
	}
	
	public void takeSuns() {
		this.sunQuantity = 0;
	}
	
	
	 @Override
	 public SunflowerCell clone() {
	    	return new SunflowerCell(new Position(position.getX(), position.getY()), Boolean.valueOf(containsAgent), Integer.valueOf(sunQuantity));
	    	
	 }
	
}
