package blodia.visuals;


import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.convert.fx.FX2Geometry;
import org.eclipse.gef.geometry.planar.Ellipse;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.geometry.planar.Rectangle;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

public class SelectableDotVisual extends Region {
	
	private static final double LINE_WIDTH = 2.0;
	private static final double RADIUS     = 4.0;
	
	private GeometryNode<Ellipse> circle;
	private GeometryNode<Ellipse> dot;
	
	
    public SelectableDotVisual() {
        
    	circle = new GeometryNode<>( new Ellipse( -RADIUS, -RADIUS, 2*RADIUS, 2*RADIUS ));
        circle.setStrokeWidth(LINE_WIDTH);
        circle.setStrokeType(StrokeType.INSIDE);

        dot = new GeometryNode<>( new Ellipse( -1, -1, 2, 2 ));
        dot.setStrokeWidth(LINE_WIDTH+2);
        dot.setStroke(Color.RED);
        dot.setStrokeType(StrokeType.CENTERED);
        
        Group group = new Group( circle, dot );
		getChildren().addAll(group);
    }

    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

	public void setSelection(boolean selection) {
		dot.setVisible(selection);
	}

}