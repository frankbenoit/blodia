package blodia.parts;


import java.util.Map;

import org.eclipse.gef.mvc.fx.parts.IContentPart;
import org.eclipse.gef.mvc.fx.parts.IContentPartFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;

import blodia.model.Model;
import blodia.model.SelectableDotNode;
import blodia.model.SwitchNode;
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

        if (content instanceof Model) {
        	System.out.println("Model");
            return injector.getInstance(ModelPart.class);
        }
        else if (content instanceof SwitchNode) {
        	System.out.println("SwitchNode");
        	return injector.getInstance(SwitchNodePart.class);
        }
        else if (content instanceof SelectableDotNode) {
        	System.out.println("SelectableDotNode");
        	return injector.getInstance(SwitchNodeSelectableDotPart.class);
        }
        else {
            throw new IllegalArgumentException("Unknown content type <" + content.getClass().getName() + ">");
        }
    }
}
