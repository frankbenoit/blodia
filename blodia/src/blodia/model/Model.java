package blodia.model;

import java.util.List;

import com.google.common.collect.Lists;

public class Model extends AbstractModelItem {

    public static final String PROP_CHILD_ELEMENTS = "childElements";

    private List<AbstractModelItem> childElements = Lists.newArrayList();

    public void addChildElement(AbstractModelItem node) {
        childElements.add(node);
        pcs.firePropertyChange(PROP_CHILD_ELEMENTS, null, node);
    }

    public void addChildElement(AbstractModelItem node, int idx) {
        childElements.add(idx, node);
        pcs.firePropertyChange(PROP_CHILD_ELEMENTS, null, node);
    }

    public List<AbstractModelItem> getChildElements() {
        return childElements;
    }

    public void removeChildElement(AbstractModelItem node) {
        childElements.remove(node);
        pcs.firePropertyChange(PROP_CHILD_ELEMENTS, node, null);
    }
}
