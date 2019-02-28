package FrontInternal.Components;

import BackExternal.IModelManager;
import BackExternal.ViewAPI;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

/**
 * This Behaves as a superclass for the History, Variable, and UserDefinedCommand view.
 * It outlines the variables and methods that each concrete implementation will need.
 */
public abstract class View implements ViewAPI {
    protected ViewTemplate myViewTemplate;
    protected ResourceBundle myBundle;
    protected IModelManager myManager;

    /**
     * @param manager IModelManager concrete implementation that was instantiated in the
     *                Backend
     */
    View(IModelManager manager){
        myManager = manager;
    }

    /**
     * Returns the visual associated with the View object
     * @return Pane object
     */
    public Pane getPane(){
        return myViewTemplate;
    }

    /**
     * Called to update the View based on the backend Model
     */
    public abstract void update();

}
