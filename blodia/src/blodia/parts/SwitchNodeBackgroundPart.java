package blodia.parts;

import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.geometry.planar.Rectangle;
import org.eclipse.gef.mvc.fx.parts.AbstractVisualPart;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;

import blodia.model.SwitchNode;
import blodia.visuals.SwitchNodeVisual;
import javafx.scene.Node;

public class SwitchNodeBackgroundPart extends AbstractVisualPart<SwitchNodeVisual> {

	
    private Point position = new Point( 0, 0 );
    private int selection = 0;

	@Override
    protected SwitchNodeVisual doCreateVisual() {
        return new SwitchNodeVisual();
    }

    @Override
    protected void doAddChildVisual(IVisualPart<? extends Node> child, int index) {
    	System.out.println("SwitchNodePart.doAddChildVisual() "+index);
        //getVisual().getChildren().add(child.getVisual());
    }
    
    @Override
    protected void doRemoveChildVisual(IVisualPart<? extends Node> child, int index) {
        //getVisual().getChildren().remove(child.getVisual());
    }
    
    @Override
    protected void doRefreshVisual(SwitchNodeVisual visual) {
        // updating the visuals texts and position

        Rectangle rec = new Rectangle( position.x, position.y, 10, 10 );

        visual.setSelection(selection);
//        visual.setDescription(node.getDescription());
//        visual.setColor(node.getColor());

//        visual.setPrefSize(rec.getWidth(), rec.getHeight());
//        // perform layout pass so that visual is resized to its preferred size
//        visual.getParent().layout();

        visual.setTranslateX(rec.getX());
        visual.setTranslateY(rec.getY());
        
    }

	public void setPosition(Point position) {
		this.position = position;
		refreshVisual();
	}

	public void setSelection(int selection) {
		this.selection = selection;
		refreshVisual();
	}

	public Point getRelPosDot1() {
		return getVisual().getRelPosDot1();
	}

	public Point getRelPosDot2() {
		return getVisual().getRelPosDot2();
	}
	
}