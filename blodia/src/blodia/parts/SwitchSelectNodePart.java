package blodia.parts;

import java.util.List;

import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.SwitchNodeSelect;
import blodia.visuals.SwitchSelectVisual;

public class SwitchSelectNodePart extends AbstractContentPart<SwitchSelectVisual> {

	public SwitchSelectNodePart() {
	}

	@Override
    protected SwitchSelectVisual doCreateVisual() {
        return new SwitchSelectVisual();
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
    protected void doRefreshVisual(SwitchSelectVisual visual) {
    }

	public void setFilled(boolean filled) {
		getVisual().setFilled(filled);
	}

	@Override
	public void setContent(Object content) {
		super.setContent(content);
		getContent().addPropertyChangeListener( ev -> {
			if( SwitchNodeSelect.PROP_SELECTION.equals(ev.getPropertyName())) {
				refreshVisual();
			}
		});
	}
	
	@Override
	public SwitchNodeSelect getContent() {
		return (SwitchNodeSelect) super.getContent();
	}
	
	public Point getOffset() {
		return new Point( -SwitchSelectVisual.RADIUS, -SwitchSelectVisual.RADIUS );
	}

}