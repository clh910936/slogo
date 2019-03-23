package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Players.AddElement;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * A HorizontalView is a view that extends horizontally and uses a scrollpane to do so. It may include widgets.
 * @author Feroze
 */
public abstract class HorizontalView extends View {
    protected HorizontalView(IModelManager manager) {
        super(manager);
    }

    protected Pane makeScrollPane(Node root) {
        ScrollPane p = new ScrollPane();
        p.setFitToHeight(true);
        p.setContent(root);

        var y = new HBox(p);
        y.setMinHeight(210);
        return y;
    }

    protected AddElement addPlus(Pane root, EventHandler e) {
        var button = new AddElement(e);
        root.getChildren().add(button);
        return button;
    }
}
