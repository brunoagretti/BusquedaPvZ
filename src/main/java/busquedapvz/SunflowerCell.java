package busquedapvz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SunflowerCell extends Cell{
	
	Integer sunQuantity;
	
	public SunflowerCell(Position pos, Boolean containsAgent, Integer sunQuantity) {
		super(pos, containsAgent);
		this.sunQuantity = sunQuantity;
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
        SunflowerCell clone = (SunflowerCell) super.clone();
        clone.setSunQuantity(Integer.valueOf(this.sunQuantity));
        return clone;
      }
      
      @Override
      public boolean equals(Object obj) {
      	return super.equals(obj) && (obj instanceof SunflowerCell);
      }

    }
