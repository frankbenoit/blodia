package blodia;

import org.eclipse.gef.common.adapt.AdapterKey;
import org.eclipse.gef.geometry.planar.Point;
import org.eclipse.gef.mvc.fx.domain.HistoricizingDomain;
import org.eclipse.gef.mvc.fx.domain.IDomain;
import org.eclipse.gef.mvc.fx.viewer.IViewer;

import com.google.inject.Guice;

import blodia.model.Model;
import blodia.model.SwitchNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BlockDiagram extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    private Stage primaryStage;
    private HistoricizingDomain domain;

    /**
     * Returns the content viewer of the domain
     *
     * @return
     */
    private IViewer getContentViewer() {
        return domain.getAdapter(AdapterKey.get(IViewer.class, IDomain.CONTENT_VIEWER_ROLE));
    }

    /**
     * Creating JavaFX widgets and set them to the stage.
     */
    private void hookViewers() {
        Scene scene = new Scene(getContentViewer().getCanvas());
        primaryStage.setScene(scene);
    }

    /**
     * Creates the example mind map and sets it as content to the viewer.
     */
    private void populateViewerContents() {
        Model model = new Model();
        model.addChildElement(new SwitchNode(0, new Point( 20, 20)));
        model.addChildElement(new SwitchNode(1, new Point( 20, 90)));

        IViewer viewer = getContentViewer();
        viewer.getContents().setAll(model);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BlockDiagramModule module = new BlockDiagramModule();
        this.primaryStage = primaryStage;
        // create domain using guice
        this.domain = (HistoricizingDomain) Guice.createInjector(module).getInstance(IDomain.class);

        // create viewers
        hookViewers();

        // activate domain
        domain.activate();

        // load contents
        populateViewerContents();

        // set-up stage
        primaryStage.setResizable(true);
        primaryStage.setTitle("Block Diagram");
        primaryStage.setHeight(300);
        primaryStage.setWidth(300);
        primaryStage.show();
    }
}
