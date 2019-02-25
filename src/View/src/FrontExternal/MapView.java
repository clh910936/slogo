package FrontExternal;

import FrontInternal.ViewTemplate;
import javafx.collections.MapChangeListener;
import javafx.scene.layout.Pane;

import java.util.Map;

/**
 * @author Carrie Hunner
 *
 * This class implements MapChangeListener and then displays the changes in a formatted
 * pane with a title and a scrollable view.
 */
public class MapView implements MapChangeListener {

    private ViewTemplate myViewTemplate;
    private String FORMAT = ":\t";

    /**
     * Creates a MapView object that can be linked as a listener to an ObserveableMap
     * @param title String of the desired name to be displayed at the top of the pane.
     */
    MapView(String title){
        myViewTemplate = new ViewTemplate(title);
    }

    /**
     * Updates the graphics of the pane as the ObserveableMap it is linked to is changed.
     * @param change object representing the change that was made
     */
    @Override
    public void onChanged(Change change) {
        if(change.wasAdded()){
            handleAdded(change);
        }
        if(change.wasRemoved()){
            myViewTemplate.clearLines();
        }
    }

    private void handleAdded(Change change) {
        Map<String, Integer> changeMap = change.getMap();
        for(String s : changeMap.keySet()){
            String print = formatString(s, changeMap.get(s));
            myViewTemplate.addFinalLine(print);
        }
    }

    //String is the variable name
    //value is the int associated with it
    private String formatString(String s, Integer value){
        return (s + FORMAT + value);
    }

    /**
     * @return Pane associated with the MapView
     */
    public Pane getPane(){
        return myViewTemplate;
    }
}
