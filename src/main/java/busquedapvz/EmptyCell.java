package busquedapvz;

public class EmptyCell extends Cell{
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("__");
		
		
		return str.toString();
	}
}
