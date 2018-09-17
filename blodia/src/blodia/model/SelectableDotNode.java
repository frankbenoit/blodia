package blodia.model;

import org.eclipse.gef.geometry.planar.Point;

public class SelectableDotNode extends AbstractModelItem {

    public static final String PROP_SELECTION = "selection";
    public static final String PROP_POSITION = "position";

    private boolean selection;
	private Point position;
    
    public SelectableDotNode(boolean selection, Point position) {
    	this.selection = selection;
		this.position = position;
	}

	public boolean getSelection() {
        return selection;
    }

    public void setBounds(boolean newValue) {
    	boolean oldValue = selection;
    	selection = newValue;
        pcs.firePropertyChange(PROP_SELECTION, oldValue, newValue );
    }

    public Point getPosition() {
    	return position;
    }
    
    public void setPosition(Point newValue) {
    	Point oldValue = position;
    	position = newValue;
    	pcs.firePropertyChange(PROP_POSITION, oldValue, newValue );
    }
    
} 