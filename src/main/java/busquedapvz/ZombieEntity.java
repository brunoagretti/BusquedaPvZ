package busquedapvz;

public class ZombieEntity extends CellContent{
	String name;
	Integer hp;
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Z" + hp);
		
		
		return str.toString();
	}
}
