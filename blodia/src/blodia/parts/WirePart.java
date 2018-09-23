package blodia.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.fx.anchors.IAnchor;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;
import org.eclipse.gef.mvc.fx.providers.IAnchorProvider;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.Wire;
import blodia.visuals.WireVisual;
import javafx.scene.Node;

public class WirePart extends AbstractContentPart<WireVisual> {    

	@Override
	protected void doAddChildVisual(IVisualPart<? extends Node> child, int index) {
	}


	@Override
	protected WireVisual doCreateVisual() {
		WireVisual vis = new WireVisual();
		Wire cnt = getContent();
		ArrayList<Point> points = cnt.getWayPoints();
		for( int i = 0; i < points.size(); i++ ) {
			vis.addControlPoint(i, points.get(i));
		}
		return vis;
	}

	@Override
	protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
		HashMultimap<Object, String> res = HashMultimap.create();
		Wire wire = getContent();
		res.put( wire.getStart(), wire.getStart().getRole() );
		res.put( wire.getEnd(), wire.getEnd().getRole() );
		return res;
	}


	@Override
	protected List<? extends Object> doGetContentChildren() {
		return Lists.newArrayList();
	}

	@Override
	protected void doRefreshVisual(WireVisual visual) {
		// no refreshing necessary, just a Group
	}

	@Override
	protected void doRemoveChildVisual(IVisualPart<? extends Node> child, int index) {
		getVisual().getChildren().remove(child.getVisual());
	}

	@Override
	protected void doRemoveContentChild(Object contentChild) {
	}
	
	@Override
	public Wire getContent() {
		return (Wire) super.getContent();
	}
	
	@Override
	protected void doAttachToAnchorageVisual(IVisualPart<? extends Node> anchorage, String role) {
		System.out.printf( "con %s %s%n", anchorage.getClass().getName(), role);
		// find a anchor provider, which must be registered in the module
		// be aware to use the right interfaces (Provider is used a lot)
		IAnchorProvider adapter = anchorage.getAdapter(AnchorageAnchorProvider.class);
		if (adapter == null) {
			throw new IllegalStateException("No adapter  found for <" + anchorage.getClass() + "> found.");
		}
		IAnchor anchor = adapter.get( anchorage, role );

		if (role.equals(getContent().getStart().getRole())) {
			getVisual().setStartAnchor(anchor);
		} else if (role.equals(getContent().getEnd().getRole())) {
			getVisual().setEndAnchor(anchor);
		} else {
			throw new IllegalArgumentException("Invalid role: " + role);
		}
	}

	@Override
	protected void doDetachFromAnchorageVisual(IVisualPart<? extends Node> anchorage, String role) {
		if (role.equals(getContent().getStart().getRole())) {
			getVisual().setStartPoint(getVisual().getStartPoint());
		} else if (role.equals(getContent().getEnd().getRole())) {
			getVisual().setEndPoint(getVisual().getEndPoint());
		} else {
			throw new IllegalArgumentException("Invalid role: " + role);
		}
	}



}