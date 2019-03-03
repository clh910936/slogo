package FrontInternal.Views;

import javafx.scene.layout.Pane;

/**
 * Implemented by any view that has a corresponding backend model
 */
public interface ViewAPI {
    /**
     * this method needs to be implemented such that the view
     * updates its information based on its backend model
     */
    public void update();

    /**
     * this method needs to be implememted so reflection
     * can be used to create the AllUserView
     * @return Pane that is going to be displayed
     */
    public Pane getPane();
}
