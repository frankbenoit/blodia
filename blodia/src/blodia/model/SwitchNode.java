package blodia.model;

import org.eclipse.gef.geometry.planar.Point;

public class SwitchNode extends AbstractModelItem {

    public static final String PROP_SELECTION = "selection";
    public static final String PROP_POSITION = "position";

    private int selection;
	private Point position;
	
	private final Anchorage anchorageSelector = new Anchorage( this, "SEL" );
	private final Anchorage anchorageSel0 = new Anchorage( this, "SEL_0" );
	private final Anchorage anchorageSel1 = new Anchorage( this, "SEL_1" );
    
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

	public Anchorage getConnectorSelector() {
		return anchorageSelector;
	}

	public Anchorage getConnector(int index) {
		if( index == 0 )
			return anchorageSel0;
		if( index == 1 )
			return anchorageSel1;
		return null;
	}

} 