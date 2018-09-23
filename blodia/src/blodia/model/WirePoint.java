package blodia.model;

import org.eclipse.gef.geometry.planar.Point;

public class WirePoint extends Anchorage {
	
	private Point position;

	public WirePoint( double x, double y) {
		super(null, "");
		position = new Point( x, y );
	}
	
	@Override
	public Object getObj() {
		return this;
	}

	public Point getPosition() {
		return position;
	}
	
}
