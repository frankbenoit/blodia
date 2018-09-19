package blodia.model;

public class SwitchNodeSelect extends AbstractModelItem {

    public static final String PROP_SELECTION = "selection";
    public static final String PROP_POSITION = "position";

    private int selection = -1;
	private final int selectionValue;
    

	public SwitchNodeSelect(int selectionValue) {
		this.selectionValue = selectionValue;
	}

	public boolean getSelected() {
        return selection == selectionValue;
    }

    public void setSelection(int newValue) {
    	int oldValue = selection;
    	selection = newValue;
        pcs.firePropertyChange(PROP_SELECTION, oldValue, newValue );
    }

} 