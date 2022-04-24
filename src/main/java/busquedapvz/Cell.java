package busquedapvz;

public class Cell {

    Position position;

	Boolean containsAgent;
	
	public Cell() {
		this.containsAgent = false;
	}

	public Boolean containsAgent() {
		return containsAgent;
	}

	public void setContainsAgent(Boolean containsAgent) {
		this.containsAgent = containsAgent;
	}
	
    public Position getPosition() {
      return position;
    }

    public void setPosition(Position position) {
      this.position = position;
    }
    
    @Override
    public boolean equals(Object obj) {
    	boolean isEqual = false;
    	
    	if(obj instanceof Cell) {
    		if(((Cell) obj).getPosition().equals(this.position) && ((Cell) obj).containsAgent() == this.containsAgent) {
    			isEqual = true;
    		}
    	}
    	
    	
    	return isEqual;
    	
    }
    
    
    
    

}
