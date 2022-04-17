package busquedapvz;

public class Position {
	Integer x;
	Integer y;
	
	public Position(Integer x, Integer y) {
		this.x = x;
		this.y = y;

	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	
	public void decrementX() {
		this.x -= 1;
	}
	
	public void decrementY() {
		this.y -= 1;
	}
	
	
	public Position clone() {
		Position clone = new Position(this.x, this.y);
		return clone;
	}
}
