package blodia.parts;

import java.util.List;

import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.AbstractModelItem;
import blodia.model.SwitchNode;
import blodia.model.SwitchNodeBkgd;
import blodia.model.SwitchNodeSelect;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class SwitchNodePart extends AbstractContentPart<Group> {    

	private SwitchNodeSelect dotX = new SwitchNodeSelect(-1);
	private SwitchNodeSelect dot1 = new SwitchNodeSelect(0);
	private SwitchNodeSelect dot2 = new SwitchNodeSelect(1);
	private SwitchNodeBkgd bkgd = new SwitchNodeBkgd();
	
	private SwitchBkgdNodePart bkgdPart;
	private SwitchSelectNodePart dotXPart;
	private SwitchSelectNodePart dot1Part;
	private SwitchSelectNodePart dot2Part;
	
	@Override
	protected void doAddChildVisual(IVisualPart<? extends Node> child, int index) {
		getVisual().getChildren().add(child.getVisual());
		SwitchNode content = getContent();
		Point position = content.getPosition();
		AnchoragePart ap;
		switch( index ) {
		case 0: 
			bkgdPart = (SwitchBkgdNodePart) child;
			setTranslate(position, bkgdPart.getVisual());
			bkgdPart.getVisual().setOnMouseClicked( event -> {
	        	System.out.printf("mouse bkgd %s%n", event );
	        	content.setSelection( ( content.getSelection() + 1 ) % 2 );
			});
			break;
		case 1: 
			dotXPart = configureSelectionDot(child, content, -1);
			dotXPart.setFilled(true);
			break;
		case 2: 
			dot1Part = configureSelectionDot(child, content, 0);
			break;
		case 3: 
			dot2Part = configureSelectionDot(child, content, 1);
			break;
		case 4:
			ap = (AnchoragePart) child;
			ap.getVisual().setTranslateX( 0+position.x);
			ap.getVisual().setTranslateY(30+position.y);
			break;
		case 5:
			ap = (AnchoragePart) child;
			ap.getVisual().setTranslateX(90+position.x);
			ap.getVisual().setTranslateY(20+position.y);
			break;
		case 6:
			ap = (AnchoragePart) child;
			ap.getVisual().setTranslateX(90+position.x);
			ap.getVisual().setTranslateY(40+position.y);
			break;
		default:
			break;
		}
	}

	private SwitchSelectNodePart configureSelectionDot(IVisualPart<? extends Node> child, SwitchNode content, final int idx) {
		Point position = content.getPosition();
		SwitchSelectNodePart dot = (SwitchSelectNodePart) child;
		setTranslate(position.getTranslated(bkgdPart.getRelPosDot(idx)).getTranslated(dot.getOffset()), dot.getVisual());
		dot.getVisual().setOnMouseClicked( event -> {
			System.out.printf("mouse sel%s %s%n", idx, event );
			content.setSelection( idx );
		});
		return dot;
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
		SwitchNode cnt = getContent();
		int selection = cnt.getSelection();
		bkgd.setSelection(selection);
		dotX.setSelection(selection);
		dot1.setSelection(selection);
		dot2.setSelection(selection);
		return Lists.newArrayList( bkgd, dotX, dot1, dot2, cnt.getConnectorSelector(), cnt.getConnector(0), cnt.getConnector(1) );
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
	protected void doAttachToAnchorageVisual(IVisualPart<? extends Node> anchorage, String role) {
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
				int selection = (int)ev.getNewValue();
				bkgd.setSelection( selection );
				dotX.setSelection( selection );
				dot1.setSelection( selection );
				dot2.setSelection( selection );
				bkgdPart.refreshVisual();
				dotXPart.refreshVisual();
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