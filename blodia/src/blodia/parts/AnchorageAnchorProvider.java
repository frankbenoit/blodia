package blodia.parts;

import org.eclipse.gef.fx.anchors.IAnchor;
import org.eclipse.gef.fx.anchors.StaticAnchor;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.parts.IVisualPart;
import org.eclipse.gef.mvc.fx.providers.IAnchorProvider;

import javafx.geometry.Bounds;
import javafx.scene.Node;

/**
 * The {@link AnchorageAnchorProvider} create an anchor for a
 * {@link MindMapConnectionVisual}.
 *
 * It is bound to an {@link MindMapNodePart} and will be called in the
 * {@link MindMapConnectionPart}.
 *
 */
public class AnchorageAnchorProvider implements IAnchorProvider {

	@Override
	public IAnchor get(IVisualPart<? extends Node> anchoredPart, String role) {
		
		if( anchoredPart instanceof WirePointPart ) {
			Bounds bounds = anchoredPart.getVisual().getBoundsInParent();
			//Point offset = node.getRoleConnectionAnchorage( role );
			//Point point = new Point( bounds.getMinX() + offset.x, bounds.getMinY() + offset.y );
			Point point = new Point( (bounds.getMinX()+bounds.getMaxX())/2, (bounds.getMinY()+bounds.getMaxY())/2 );
			System.out.println("pos: "+bounds);
			return new StaticAnchor(point);
			
		}

		
		AnchoragePart node = (AnchoragePart) anchoredPart;
		
		Node anchorage = node.getVisual();
		
		Bounds bounds = anchorage.getBoundsInParent();
		//Point offset = node.getRoleConnectionAnchorage( role );
		//Point point = new Point( bounds.getMinX() + offset.x, bounds.getMinY() + offset.y );
		Point point = new Point( bounds.getMinX(), bounds.getMinY() );
		System.out.println("pos: "+bounds);
		return new StaticAnchor(point);
		
//		if (anchor == null) {
//			Node anchorage = getAdaptable().getVisual();
//			// get the visual from the host (MindMapNodePart)
//			// create a new anchor instance
//			anchor = new DynamicAnchor(anchorage);
//
//			// binding the anchor reference to an object binding, which
//			// recalculates the geometry when the layout bounds of
//			// the anchorage are changing
//			anchor.getComputationParameter(AnchorageReferenceGeometry.class).bind(new ObjectBinding<IGeometry>() {
//				{
//					bind(anchorage.layoutBoundsProperty());
//				}
//
//				@Override
//				protected IGeometry computeValue() {
//					@SuppressWarnings("serial")
//					// get the registered geometry provider from the host
//					Provider<IGeometry> geomProvider = getAdaptable().getAdapter(new TypeToken<Provider<IGeometry>>() {
//					});
//					return geomProvider.get();
//				}
//			});
//		}
//		return anchor;
	}
}