package blodia.model;

public class SwitchNode extends AbstractModelItem {

    public static final String PROP_SELECTION = "selection";
    public static final String PROP_POSITION = "position";

    private int selection;
	private int position;
    
    public SwitchNode(int selection, int position) {
    	this.selection = selection;
		this.position = position;
	}

	public int getSelection() {
        return selection;
    }

    public void setBounds(int newValue) {
    	int oldValue = selection;
    	selection = newValue;
        pcs.firePropertyChange(PROP_SELECTION, oldValue, newValue );
    }

    public int getPosition() {
    	return position;
    }
    
    public void setPosition(int newValue) {
    	int oldValue = position;
    	position = newValue;
    	pcs.firePropertyChange(PROP_POSITION, oldValue, newValue );
    }
    
} 