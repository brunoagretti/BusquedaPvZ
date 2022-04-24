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

}
