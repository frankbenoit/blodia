package blodia.model;

import org.eclipse.gef.geometry.planar.Point;

public class SwitchNode extends AbstractModelItem {

    public static final String PROP_SELECTION = "selection";
    public static final String PROP_POSITION = "position";

    private int selection;
	private Point position;
    
    public SwitchNode(int selection, Point position) {
    	this.selection = selection;
		this.position = position;
	}

	public int getSelection() {
        return selection;
    }

    public void setSelection(int newValue) {
    	int oldValue = selection;
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

	public IConnector getConnectorSelector() {
		return null;
	}

	public IConnector getConnector(int index) {
		return null;
	}

} 