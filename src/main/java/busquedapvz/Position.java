package busquedapvz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
	Integer x;
	Integer y;
	
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

    @Override
    public boolean equals(Object obj) {
      boolean isEquals = false;
      if (obj instanceof Position) {
        if(((Position) obj).getX() == this.x && ((Position) obj).getY() == this.y) {
          isEquals = true;
        }
      }
      return isEquals;
    }
    
    @Override
    public int hashCode() {
      return x.hashCode()*7 + y.hashCode();
    }

    @Override
    public String toString() {
      return "Position [x=" + x + ", y=" + y + "]";
    }
    
    
  }
