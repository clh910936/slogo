package FrontInternal.Views;

import BackExternal.IModelManager;
import FrontInternal.Util.Operator;
import javafx.scene.layout.Pane;


/**
 * This Behaves as a superclass for the History, Variable, and UserDefinedCommand view.
 * It outlines the variables and methods that each concrete implementation will need.
 */
public abstract class View implements ViewAPI  {
    protected ViewTemplate myViewTemplate;
    protected Operator myOperator;
    protected IModelManager myManager;

    /**
     * @param operator used for updating
     */
    View(Operator operator){
        myOperator = operator;
        myManager = operator.getManager();
        myViewTemplate = new ViewTemplate();
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
