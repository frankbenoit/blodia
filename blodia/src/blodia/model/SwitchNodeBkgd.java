package blodia.model;

public class SwitchNodeBkgd extends AbstractModelItem {

    public static final String PROP_SELECTION = "selection";
    public static final String PROP_POSITION = "position";

    private int selection;
    

	public int getSelection() {
        return selection;
    }

    public void setSelection(int newValue) {
    	int oldValue = selection;
    	selection = newValue;
        pcs.firePropertyChange(PROP_SELECTION, oldValue, newValue );
    }

} 