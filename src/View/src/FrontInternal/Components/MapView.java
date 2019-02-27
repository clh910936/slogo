package FrontInternal.Components;

import javafx.scene.layout.Pane;

/**
 * @author Carrie Hunner
 *
 * This class implements MapChangeListener and then displays the changes in a formatted
 * pane with a title and a scrollable view.
 */
public class MapView {

    private ViewTemplate myViewTemplate;
    private String FORMAT = ":\t";

    /**
     * Creates a MapView object that can be linked as a listener to an ObserveableMap
     * @param title String of the desired name to be displayed at the top of the pane.
     */
    public MapView(String title){
        myViewTemplate = new ViewTemplate(title);
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

    //TODO: write update method
    public void update() {
        myViewTemplate.addFinalLine("temp");
    }
}
