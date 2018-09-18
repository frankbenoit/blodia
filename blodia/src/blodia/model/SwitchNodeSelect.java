package blodia.model;

public class SwitchNodeSelect extends AbstractModelItem {

    public static final String PROP_SELECTION = "selection";
    public static final String PROP_POSITION = "position";

    private boolean selection;
    

	public boolean getSelection() {
        return selection;
    }

    public void setSelection(boolean newValue) {
    	boolean oldValue = selection;
    	selection = newValue;
        pcs.firePropertyChange(PROP_SELECTION, oldValue, newValue );
    }

} 