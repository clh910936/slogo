package FrontInternal.Components;

import FrontExternal.Observer;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * This class implements ListChangeListener and then displays the changes in a formatted
 * pane with a title and a scrollable view.
 */
public class ListView implements Observer {
    private ViewTemplate myViewPane;

    /**
     * Creates a ListView object that can be linked as a listener to an ObserveableList
     * @param viewTitle String of the desired name to be displayed at the top of the pane.
     */
    public ListView(String viewTitle){
        myViewPane = new ViewTemplate(viewTitle);
    }



    /**
     * @return Pane associated with the MapView
     */
    public Pane getPane(){
        return myViewPane;
    }

    //TODO: write this method
    /**
     * Called when the corresponding backend model updates the list.
     * This updates the list seen by the user to match the model's list.
     */
    @Override
    public void update() {

    }
}
