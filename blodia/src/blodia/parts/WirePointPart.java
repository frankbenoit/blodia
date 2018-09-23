package blodia.parts;

import blodia.model.WirePoint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeType;

public class WirePointPart extends AnchoragePart {

	private static final double RAD = 2;
	@Override
	protected Ellipse doCreateVisual() {
		return new Ellipse(0, 0, RAD, RAD);
	}

	@Override
	protected void doRefreshVisual(Ellipse visual) {
		WirePoint cnt = getContent();
		Ellipse vis = getVisual();
		vis.setStrokeType(StrokeType.INSIDE);
		vis.setTranslateX(cnt.getPosition().x);
		vis.setTranslateY(cnt.getPosition().y);
	}
	
	@Override
	public WirePoint getContent() {
		return (WirePoint) super.getContent();
	}
}
