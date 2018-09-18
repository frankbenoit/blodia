package blodia.parts;

import java.util.List;

import org.eclipse.gef.geometry.planar.Dimension;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.parts.AbstractContentPart;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import blodia.model.SwitchNodeSelect;
import blodia.visuals.SwitchSelectVisual;

public class SwitchSelectNodePart extends AbstractContentPart<SwitchSelectVisual> {

    private boolean selected;
	private Point position = new Point( 0, 0 );

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
        // updating the visuals texts and position

    	visual.setSelection( selected );
//    	visual.setPosition(new Point( 70, 20 + position*20 ));
//    	visual.setTranslateX( getParent().getVisual().getTranslateX() );
//    	visual.setTranslateY( getParent().getVisual().getTranslateY() );
//    	SelectableDotPart node = getContent();
//        Rectangle rec = new Rectangle(20, 20 + node.getPosition() * 75, 10, 10 );
//
//        visual.setSelection(node.getSelection());
//        visual.setDescription(node.getDescription());
//        visual.setColor(node.getColor());

//        visual.setPrefSize(rec.getWidth(), rec.getHeight());
//        // perform layout pass so that visual is resized to its preferred size
//        visual.getParent().layout();
//
		
//    	visual.setTranslateX(position.x);
//    	visual.setTranslateY(position.y);
//        visual.setTranslateX(getParent().getVisual().getTranslateX());
//        visual.setTranslateY(getParent().getVisual().getTranslateY());
    }

    public void setSelected( boolean selected ) {
		this.selected = selected;
    }

	public void setPosition(Point relPos) {
		position = relPos;
		refreshVisual();
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