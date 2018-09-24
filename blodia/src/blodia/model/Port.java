package blodia.model;

import org.eclipse.gef.geometry.planar.Point;

public class Port extends Anchorage {
	
	
	private final String text;
	private final Point position;
	private Orientation orientation;
	
	public Port( double x, double y, Orientation orientation, String text) {
		super(null, "");
		this.orientation = orientation;
		this.text = text;
		position = new Point( x, y );
	}
	
	@Override
	public Object getObj() {
		return this;
	}

	public Point getPosition() {
		return position;
	}

	public String getText() {
		return text;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
}
