package blodia.model;

import java.util.ArrayList;

import org.eclipse.gef.geometry.planar.Point;

import com.google.common.collect.Lists;

public class Wire extends AbstractModelItem {
	
	private Anchorage start;
	private Anchorage end;
	private ArrayList<Point> wayPoints = Lists.newArrayList();
	
	public Wire(Anchorage start, Anchorage end) {
		this.start = start;
		this.end = end;
		
		
	}

	public Wire addWayPoint( double x, double y ) {
		wayPoints.add(new Point( x, y ));
		return this;
	}
	
	public Anchorage getStart() {
		return start;
	}
	
	public Anchorage getEnd() {
		return end;
	}
	
	public ArrayList<Point> getWayPoints() {
		return wayPoints;
	}
}
