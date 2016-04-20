// R7 code referred
public class IdVal extends KeyItem<String> {
	
	protected Boolean val;
	
	public IdVal(String id, Boolean val){
		super(id);
		this.val = val;
	}

	public String toString(){
		return "[" + getKey() + ": " + val+ "]"; 
	}
	
}

