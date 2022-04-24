package busquedapvz;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnknownCell extends Cell{

  public UnknownCell(Position position, Boolean containsAgent) {
    super(position, containsAgent);
  }
  
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("??");
		
		
		return str.toString();
	}
	
    @Override
    public UnknownCell clone() {
      UnknownCell clone = (UnknownCell) super.clone();
      return clone;
    }

}
