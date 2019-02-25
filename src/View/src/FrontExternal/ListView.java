package FrontExternal;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * This class implements ListChangeListener and then displays the changes in a formatted
 * pane with a title and a scrollable view.
 */
public class ListView implements ListChangeListener {
    private ViewTemplate myViewPane;
    private String myTitle;

    /**
     * Creates a ListView object that can be linked as a listener to an ObserveableList
     * @param viewTitle String of the desired name to be displayed at the top of the pane.
     */
    public ListView(String viewTitle){
        myViewPane = new ViewTemplate(viewTitle);
        myTitle = viewTitle;
    }


    /**
     * Updates the graphics of the pane as the ObserveableList it is linked to is changed.
     * @param change object representing the change that was made
     */
    @Override
    public void onChanged(Change change) {
        change.next();
        if(change.wasAdded()){
            handleAddingChanges(change);
        }
        if(change.wasRemoved()){
            myViewPane.clearLines();
        }
    }

    private void handleAddingChanges(Change change) {
        List<String> tempList = change.getAddedSubList();
        for(int k = 0; k < tempList.size(); k++){
            myViewPane.addFinalLine(tempList.get(k));
        }
    }

    /**
     * @return Pane associated with the MapView
     */
    public Pane getPane(){
        return myViewPane;
    }
}
