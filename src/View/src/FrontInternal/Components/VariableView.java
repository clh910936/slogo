package FrontInternal.Components;

import BackExternal.IModelManager;

import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of IModelManager to get
 * the Variable. It then displays these on a pane
 * that is created in View.
 */
public class VariableView extends View {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager implementation of IModelManager
     */
    VariableView(IModelManager manager){
        super(manager);
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("VariableTitle"));
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        myViewTemplate.clearLines();
        Map<String, String> map = myManager.getVariables();
        for(String s : map.keySet()){
            String line = s + "\t" + map.get(s);
            myViewTemplate.addFinalLine(line);
        }
    }
}