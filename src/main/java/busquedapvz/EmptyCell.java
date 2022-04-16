package busquedapvz;

public class EmptyCell extends CellContent{
	
	
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("__");
		
		
		return str.toString();
	}
}
