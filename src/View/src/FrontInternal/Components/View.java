package FrontInternal.Components;

import BackExternal.IModelManager;
import BackExternal.ViewAPI;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public abstract class View implements ViewAPI {
    protected ViewTemplate myViewTemplate;
    protected ResourceBundle myBundle;
    protected IModelManager myManager;

    View(IModelManager manager){
        myManager = manager;
    }

    public Pane getPane(){
        return myViewTemplate;
    }
    public abstract void update();

}
