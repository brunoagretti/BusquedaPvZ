package busquedapvz; 

public class SunflowerCell extends Cell{
	
	Integer sunQuantity;
	
	public SunflowerCell(Integer sunQuantity) {
		super();
		this.sunQuantity = sunQuantity;
	}

	public SunflowerCell() {
		super();
		this.sunQuantity = 0;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("S" + sunQuantity);
		
		
		return str.toString();
	}
	
	public void addSuns(Integer amount) {
		this.sunQuantity+=amount;
	}
	
}
