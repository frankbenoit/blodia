package blodia.model;

public class Anchorage extends AbstractModelItem {

	private final Object obj;
	private final String role;

	public Anchorage( Object obj, String role ) {
		this.obj = obj;
		this.role = role;
	}
	

	public Object getObj() {
		return obj;
	}
	
	public String getRole() {
		return role;
	}
	
}
