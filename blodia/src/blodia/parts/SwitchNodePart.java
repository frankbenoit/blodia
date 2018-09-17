package blodia.parts;

import java.util.List;

import org.eclipse.gef.geometry.convert.fx.FX2Geometry;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.geometry.planar.Rectangle;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.AbstractModelItem;
import blodia.model.SwitchNode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;

public class SwitchNodePart extends AbstractContentPart<Group> {    

	private SwitchNodeBackgroundPart bgnd;
	private SwitchNodeSelectableDotPart dot1;
	private SwitchNodeSelectableDotPart dot2;
	private Point position = new Point( 0, 0 );
	
	public SwitchNodePart() {
		
		bgnd = new SwitchNodeBackgroundPart();
		dot1 = new SwitchNodeSelectableDotPart();
		dot2 = new SwitchNodeSelectableDotPart();
		addChild(bgnd);
		addChild(dot1);
		addChild(dot2);
	
//		contentProperty().addListener(new ChangeListener<Object>() {
//		    public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
//				refreshVisual();
//				getChildrenUnmodifiable().forEach(vp -> vp.refreshVisual());
//		    }
//		});
	}
	
	@Override
	protected void doAddChildVisual(IVisualPart<? extends Node> child, int index) {
		getVisual().getChildren().add(child.getVisual());
	}

	@Override
	protected void doAddContentChild(Object contentChild, int index) {
		if (contentChild instanceof AbstractModelItem) {
//			getContent().addChildElement((AbstractModelItem) contentChild, index);
		} else {
			throw new IllegalArgumentException("contentChild has invalid type: " + contentChild.getClass());
		}
	}

	@Override
	protected Group doCreateVisual() {
		return new Group();
	}

	@Override
	protected SetMultimap<? extends Object, String> doGetContentAnchorages() {
		return HashMultimap.create();
	}

	@Override
	protected List<? extends Object> doGetContentChildren() {
		return Lists.newArrayList();
	}

	@Override
	protected void doRefreshVisual(Group visual) {
		// no refreshing necessary, just a Group
		if( getContent() == null ) return;
		position = new Point( 20, 20 + 100 * getContent().getPosition());
		bgnd.setPosition(position);
		bgnd.setSelection(getContent().getSelection());
		dot1.setPosition(position.getTranslated(bgnd.getRelPosDot1()));
		dot2.setPosition(position.getTranslated(bgnd.getRelPosDot2()));
		
//		System.out.printf("this: %s%n", getVisual().getLayoutBounds());
//		System.out.printf("bgnd: %s%n", bgnd.getVisual().getLayoutBounds());
//		System.out.printf("Dot1: %s%n", dot1.getVisual().getLayoutBounds());
//		System.out.printf("Dot2: %s%n", dot2.getVisual().getLayoutBounds());

	}

	@Override
	protected void doRemoveChildVisual(IVisualPart<? extends Node> child, int index) {
		getVisual().getChildren().remove(child.getVisual());
	}

	@Override
	protected void doRemoveContentChild(Object contentChild) {
		if (contentChild instanceof AbstractModelItem) {
//			getContent().removeChildElement((AbstractModelItem) contentChild);
		} else {
			throw new IllegalArgumentException("contentChild has invalid type: " + contentChild.getClass());
		}
	}

	@Override
	public SwitchNode getContent() {
		return (SwitchNode) super.getContent();
	}
}