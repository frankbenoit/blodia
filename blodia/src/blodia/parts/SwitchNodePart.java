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
import blodia.model.SwitchNodeBkgd;
import blodia.model.SwitchNodeSelect;
import blodia.visuals.SwitchBkgdVisual;
import blodia.model.SwitchNode;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class SwitchNodePart extends AbstractContentPart<Group> {    

	private SwitchNodeSelect dot1 = new SwitchNodeSelect();
	private SwitchNodeSelect dot2 = new SwitchNodeSelect();
	private SwitchNodeBkgd bkgd = new SwitchNodeBkgd();
	
	private SwitchBkgdNodePart bkgdPart;
	private SwitchSelectNodePart dot1Part;
	private SwitchSelectNodePart dot2Part;
	
	@Override
	protected void doAddChildVisual(IVisualPart<? extends Node> child, int index) {
		getVisual().getChildren().add(child.getVisual());
		SwitchNode content = getContent();
		Point position = content.getPosition();
		switch( index ) {
		case 0: 
			bkgdPart = (SwitchBkgdNodePart) child;
			setTranslate(position, bkgdPart.getVisual());
			bkgdPart.getVisual().setOnMouseClicked( event -> {
	        	System.out.printf("mouse bkgd %s%n", event );
	        	content.setSelection( content.getSelection() + 1 );
			});
			break;
		case 1: 
			dot1Part = (SwitchSelectNodePart) child;
			setTranslate(position.getTranslated(bkgdPart.getRelPosDot1()).getTranslated(dot1Part.getOffset()), dot1Part.getVisual());
			dot1Part.getVisual().setOnMouseClicked( event -> {
	        	System.out.printf("mouse sel1 %s%n", event );
	        	content.setSelection( 0 );
			});
			break;
		case 2: 
			dot2Part = (SwitchSelectNodePart) child; 
			setTranslate(position.getTranslated(bkgdPart.getRelPosDot2()).getTranslated(dot2Part.getOffset()), dot2Part.getVisual());
			dot2Part.getVisual().setOnMouseClicked( event -> {
	        	System.out.printf("mouse sel2 %s%n", event );
	        	content.setSelection( 1 );
			});
			break;
		default:
			break;
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
		return Lists.newArrayList( bkgd, dot1, dot2 );
	}

	@Override
	protected void doRefreshVisual(Group visual) {
		// no refreshing necessary, just a Group
	}


	private void setTranslate(Point position, Region vis) {
		vis.setTranslateX(position.x);
		vis.setTranslateY(position.y);
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
	public void setContent(Object content) {
		super.setContent(content);
		getContent().addPropertyChangeListener( ev -> {
			if( SwitchNode.PROP_SELECTION.equals(ev.getPropertyName())) {
				bkgd.setSelection( 0 < (int)ev.getNewValue() );
				dot1.setSelection( 0 == ((int)ev.getNewValue()) % 2 );
				dot2.setSelection( 1 == ((int)ev.getNewValue()) % 2 );
				bkgdPart.refreshVisual();
				dot1Part.refreshVisual();
				dot2Part.refreshVisual();
			}
		});
	}
	
	@Override
	public SwitchNode getContent() {
		return (SwitchNode) super.getContent();
	}
}