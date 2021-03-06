package blodia.parts;


import java.util.Map;

import org.eclipse.gef.mvc.fx.parts.IContentPart;
import org.eclipse.gef.mvc.fx.parts.IContentPartFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;

import blodia.model.Anchorage;
import blodia.model.Model;
import blodia.model.Port;
import blodia.model.SwitchNode;
import blodia.model.SwitchNodeBkgd;
import blodia.model.SwitchNodeSelect;
import blodia.model.Wire;
import blodia.model.WirePoint;
import javafx.scene.Node;

/**
 * The {@link PartsFactory} creates a Part for the mind map models, based
 * on the type of the model instance.
 *
 */
public class PartsFactory implements IContentPartFactory {

    @Inject
    private Injector injector;

    @Override
    public IContentPart<? extends Node> createContentPart(Object content, Map<Object, Object> contextMap) {
        if (content == null) {
            throw new IllegalArgumentException("Content must not be null!");
        }

    	System.out.println("PartFactory: "+content.getClass().toString());

        if (content instanceof Model) {
            return injector.getInstance(ModelPart.class);
        }
        else if (content instanceof SwitchNode) {
        	return injector.getInstance(SwitchNodePart.class);
        }
        else if (content instanceof SwitchNodeSelect) {
        	return injector.getInstance(SwitchSelectNodePart.class);
        }
        else if (content instanceof SwitchNodeBkgd) {
        	return injector.getInstance(SwitchBkgdNodePart.class);
        }
        else if (content instanceof Wire) {
        	return injector.getInstance(WirePart.class);
        }
        else if (content instanceof Port) {
        	return injector.getInstance(PortPart.class);
        }
        else if (content instanceof WirePoint) {
        	return injector.getInstance(WirePointPart.class);
        }
        else if (content instanceof Anchorage) {
        	return injector.getInstance(AnchoragePart.class);
        }
        else {
            throw new IllegalArgumentException("Unknown content type <" + content.getClass().getName() + ">");
        }
    }
}
