package FrontInternal.Views;

import API.IModelManager;

import java.util.Map;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of API.IModelManager to get
 * the Variable. It then displays these on a pane
 * that is created in View.
 */
public class VariableView extends View {

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager implementation of API.IModelManager
     */
    public VariableView(IModelManager manager){
        super(manager);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    //TODO: fixme
    @Override
    public void update() {
        this.clearLines();
        Map<String, String> map = myManager.getVariables();
        for(String s : map.keySet()){
            String line = s + "\t" + map.get(s);
            //this.addFinalLine(line);
        }
    }
}