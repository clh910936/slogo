package FrontInternal.Components;

import BackExternal.IModelManager;

import java.util.ResourceBundle;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of IModelManager to get
 * the UserDefinedCommands. It then displays these on a pane
 * that is created in View.
 */
public class UserDefinedCommandsView extends View {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager implementation of IModelManager
     */
    UserDefinedCommandsView(IModelManager manager){
        super(manager);
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("UserCommandsTitle"));
    }
    //TODO: Fix this
    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        myViewTemplate.clearLines();
        for(String s : list){

            myViewTemplate.addFinalLine(s);
        }
    }
}
