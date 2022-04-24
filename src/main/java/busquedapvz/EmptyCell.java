package busquedapvz;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmptyCell extends Cell{
	
    public EmptyCell(Position position, Boolean containsAgent) {
      super(position, containsAgent);
    }
  
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("__");
		
		
		return str.toString();
	}
	
	   @Override
	    public EmptyCell clone() {
	      EmptyCell clone = (EmptyCell) super.clone();
	      return clone;
	    }
}
