package busquedapvz; 

public class SunflowerEntity extends CellContent{
	
	Integer sunQuantity;
	
	public SunflowerEntity(Integer sunQuantity) {
		super();
		this.sunQuantity = sunQuantity;
	}

	public SunflowerEntity() {
		super();
		this.sunQuantity = 0;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("S" + sunQuantity);
		
		
		return str.toString();
	}
	
}
