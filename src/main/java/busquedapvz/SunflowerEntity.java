package busquedapvz; 

public class SunflowerEntity extends CellContent{
	
	Integer sunQuantity;
	
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("S" + sunQuantity);
		
		
		return str.toString();
	}
	
}
