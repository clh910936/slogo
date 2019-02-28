package FrontInternal.Components;

import BackExternal.IModelManager;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of IModelManager to get
 * the History. It then displays these on a pane
 * that is created in View.
 */
public class HistoryView extends View {

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager implementation of IModelManager
     */
    public HistoryView(IModelManager manager){
        super(manager);
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("HistoryTitle"));
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        myViewTemplate.clearLines();
        Map<String, Boolean> history = myManager.getHistory();
        for(String s: history.keySet()){
            myViewTemplate.addFinalLine(s, history.get(s));
        }
    }
}
