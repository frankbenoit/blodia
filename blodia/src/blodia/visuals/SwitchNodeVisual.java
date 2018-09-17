package blodia.visuals;


import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.planar.Line;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.geometry.planar.Ellipse;
import org.eclipse.gef.geometry.planar.RoundedRectangle;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

public class SwitchNodeVisual extends Region {

    private GeometryNode<RoundedRectangle> shape;
	private GeometryNode<Line> connectionLine;
	private static final double LINE_WIDTH = 2.0;
	private final Point relPosDot1 = new Point( 70, 20 );
	private final Point relPosDot2 = new Point( 70, 40 );
    public SwitchNodeVisual() {
        // create background shape
        shape = new GeometryNode<>(new RoundedRectangle(0, 0, 90, 60, 5, 5));
        shape.setFill(Color.LIGHTGREEN);
        shape.setStroke(Color.BLACK);
        
        GeometryNode<Line> line1 = newLine(  0.0, 30.0, 17.0, 30.0 );
        GeometryNode<Line> line2 = newLine( relPosDot1.x + 2.5, relPosDot1.y, 90.0, relPosDot1.y );
        GeometryNode<Line> line3 = newLine( relPosDot2.x + 2.5, relPosDot2.y, 90.0, relPosDot2.y );

        GeometryNode<Ellipse> point1 = newDot(20, 30);
//        GeometryNode<Ellipse> point2 = newDot(70, 20);
//        GeometryNode<Ellipse> point3 = newDot(70, 40);
        
        connectionLine = new GeometryNode<>( new Line(20, 30, 70, 20));
        connectionLine.setStrokeWidth(LINE_WIDTH);
        connectionLine.setStrokeLineCap(StrokeLineCap.ROUND );
        
        Group group = new Group(shape, line1, line2, line3, point1, /*point2, point3, */connectionLine );
		getChildren().addAll(group);
    }

    private GeometryNode<Line> newLine( double x1, double y1, double x2, double y2 ) {
        GeometryNode<Line> line1 = new GeometryNode<>( new Line( x1, y1, x2, y2 ));
        line1.setStrokeWidth(LINE_WIDTH);
        line1.setStrokeLineCap(StrokeLineCap.BUTT );
    	return line1;
    }
    
    private GeometryNode<Ellipse> newDot( double x1, double y1 ) {
    	GeometryNode<Ellipse> dot = new GeometryNode<>( new Ellipse( x1-3, y1-3, 6, 6 ));
        dot.setStrokeWidth(LINE_WIDTH);
        dot.setStrokeType(StrokeType.CENTERED);
    	return dot;
    }

    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

//    public GeometryNode<?> getGeometryNode() {
//        return shape;
//    }
//
	public void setSelection(int selection) {
		double y = selection == 0 ? 20 : 40;
		connectionLine.getGeometry().setP2(new Point(70, y));
	}

	public Point getRelPosDot1() {
		return relPosDot1;
	}

	public Point getRelPosDot2() {
		return relPosDot2;
	}
	
}