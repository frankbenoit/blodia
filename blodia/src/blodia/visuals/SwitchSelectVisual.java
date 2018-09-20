package blodia.visuals;


import org.eclipse.gef.fx.nodes.GeometryNode;
import org.eclipse.gef.geometry.planar.Ellipse;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;

public class SwitchSelectVisual extends Region {
	
	private static final double LINE_WIDTH = 2.0;
	public static final double RADIUS     = 4.0;
	
	private GeometryNode<Ellipse> circle;
	
	
    public SwitchSelectVisual() {
        
    	circle = new GeometryNode<>( new Ellipse( 0, 0, 2*RADIUS, 2*RADIUS ));
        circle.setStrokeWidth(LINE_WIDTH);
        circle.setStrokeType(StrokeType.INSIDE);

		getChildren().addAll(circle);
    }

    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

	public void setFilled(boolean filled) {
		circle.setFill( filled ? Color.BLACK : null );
	}

}