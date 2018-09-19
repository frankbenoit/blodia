package blodia.parts;

import java.util.List;

import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.SwitchNodeBkgd;
import blodia.model.SwitchNodeSelect;
import blodia.visuals.SwitchBkgdVisual;
import javafx.scene.Node;

public class SwitchBkgdNodePart extends AbstractContentPart<SwitchBkgdVisual> {

	@Override
    protected SwitchBkgdVisual doCreateVisual() {
        return new SwitchBkgdVisual();
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
    protected void doAddChildVisual(IVisualPart<? extends Node> child, int index) {
    	System.out.println("SwitchNodePart.doAddChildVisual() "+index);
    }
    
    @Override
    protected void doRemoveChildVisual(IVisualPart<? extends Node> child, int index) {
    }
    
    @Override
    protected void doRefreshVisual(SwitchBkgdVisual visual) {
        int selection = getContent().getSelection();
		visual.setSelection(selection);
    }

	public Point getRelPosDot(int idx) {
		return getVisual().getRelPosDotX(idx);
	}

	@Override
	public void setContent(Object content) {
		super.setContent(content);
		getContent().addPropertyChangeListener( ev -> {
			if( SwitchNodeSelect.PROP_SELECTION.equals(ev.getPropertyName())) {
				refreshVisual();
				getRoot().refreshVisualProperty();
			}
		});
	}
	
	@Override
	public SwitchNodeBkgd getContent() {
		return (SwitchNodeBkgd) super.getContent();
	}
	

}