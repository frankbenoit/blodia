package blodia.parts;

import java.util.List;

import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.Anchorage;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;

public class AnchoragePart extends AbstractContentPart<Ellipse> {

	@Override
	protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
		Anchorage anchorage = getContent();
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
	protected Ellipse doCreateVisual() {
		return new Ellipse(0, 0, 0, 0);
	}

	@Override
	protected void doRefreshVisual(Ellipse visual) {
	}

	@Override
	public Anchorage getContent() {
		return (Anchorage) super.getContent();
	}
}
