package busquedapvz;

public class CellContent {
	Integer posX;
	Integer posY;

	Boolean containsAgent;
	
	public CellContent() {
		super();
		this.containsAgent = false;
	}

	public Boolean containsAgent() {
		return containsAgent;
	}

	public void setContainsAgent(Boolean containsAgent) {
		this.containsAgent = containsAgent;
	}
	
	
}
