package blodia.parts;

import java.util.List;

import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.Port;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class PortPart extends AbstractContentPart<Path> {

	@Override
	protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
		Port anchorage = getContent();
		HashMultimap<Object, String> res = HashMultimap.create();
		res.put( anchorage, anchorage.getRole() );
		return res;
	}

	@Override
	protected void doAttachToAnchorageVisual(IVisualPart<? extends Node> anchorage, String role) {
	}
	
	@Override
	protected List<? extends Object> doGetContentChildren() {
		return Lists.newArrayList();
	}

	@Override
	protected Path doCreateVisual() {
		
		final double WIDTH      = 80;
		final double HEIGHT     = 30;
		final double RAD      =  3;
		
		final double HEIGHT_2   = HEIGHT/2;
		final double SIN_45 = Math.sin( Math.toRadians( 45 ) );
		final double RAD_2 = RAD/Math.tan( Math.toRadians( 67.5 ) );
		
		return new Path( new MoveTo(0, 0), 
				new LineTo( HEIGHT_2-RAD_2*SIN_45, HEIGHT_2-RAD_2*SIN_45), 
				new ArcTo(  RAD, RAD, 0, HEIGHT_2+RAD_2, HEIGHT_2, false, false ),
				new LineTo( WIDTH-RAD,  HEIGHT_2),
				new ArcTo(  RAD, RAD, 0, WIDTH, HEIGHT_2-RAD, false, false ),
				new LineTo( WIDTH, -HEIGHT_2+RAD), 
				new ArcTo(  RAD, RAD, 0, WIDTH-RAD, -HEIGHT_2, false, false ),
				new LineTo(   HEIGHT_2+RAD_2, -HEIGHT_2), 
				new ArcTo(  RAD, RAD, 0, HEIGHT_2-RAD_2*SIN_45, -HEIGHT_2+RAD_2*SIN_45, false, false ),
				new ClosePath() );
	}

	@Override
	protected void doRefreshVisual(Path visual) {
		Path vis = getVisual();
		Point pos = getContent().getPosition();
		vis.setStrokeWidth( 1 );
		vis.setStroke( Color.BLACK );
		vis.setFill( Color.LIGHTYELLOW );
		vis.setTranslateX( pos.x );
		vis.setTranslateY( pos.y );
	}

	@Override
	public Port getContent() {
		return (Port) super.getContent();
	}
}
