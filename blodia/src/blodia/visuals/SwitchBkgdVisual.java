package blodia.visuals;


import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.planar.Line;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.geometry.planar.RoundedRectangle;

import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

public class SwitchBkgdVisual extends Region {

    private GeometryNode<RoundedRectangle> shape;
	private GeometryNode<Line> connectionLine;
	private static final double LINE_WIDTH = 2.0;
	private final Point relPosDotX = new Point( 20, 30 );
	private final Point relPosDot1 = new Point( 70, 20 );
	private final Point relPosDot2 = new Point( 70, 40 );
    public SwitchBkgdVisual() {
        // create background shape
        shape = new GeometryNode<>(new RoundedRectangle(0, 0, 90, 60, 5, 5));
        shape.setFill(Color.LIGHTGREEN);
        shape.setStroke(Color.BLACK);
        shape.setStrokeWidth(1.01);
        shape.setStrokeType(StrokeType.INSIDE);
        
        // Keep 1.0 away from the border to avoid to trigger the bug with wrong line dimensions
        GeometryNode<Line> line1 = newLine( 1.0, 30.0, 17.0, 30.0 );
        GeometryNode<Line> line2 = newLine( relPosDot1.x + 2.5, relPosDot1.y, 89.0, relPosDot1.y );
        GeometryNode<Line> line3 = newLine( relPosDot2.x + 2.5, relPosDot2.y, 89.0, relPosDot2.y );

        connectionLine = new GeometryNode<>( new Line(20, 30, 70, 20));
        connectionLine.setStrokeWidth(LINE_WIDTH);
        connectionLine.setStrokeLineCap(StrokeLineCap.ROUND );
        
        Group group = new Group(shape, line1, line2, line3, connectionLine );
        getChildren().addAll(group);
    }

    private GeometryNode<Line> newLine( double x1, double y1, double x2, double y2 ) {
        GeometryNode<Line> line1 = new GeometryNode<>( new Line( x1, y1, x2, y2 ));
        line1.setStrokeWidth(LINE_WIDTH);
        line1.setStrokeLineCap(StrokeLineCap.BUTT );
    	return line1;
    }
    
	public void setSelection(int selection) {
		connectionLine.setVisible( selection >= 0 );
		if( selection >= 0 ) {
			double y = 20 + selection * 20;
			Line line = connectionLine.getGeometry().getCopy();
			line.setY2(y);
			connectionLine.setGeometry(line);
		}
	}

	public Point getRelPosDotX(int idx) {
		if( idx == 0 ) return relPosDot1;
		if( idx == 1 ) return relPosDot2;
		return relPosDotX;
	}

}