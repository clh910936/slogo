package FrontInternal.Views;

import FrontInternal.Util.Operator;

import java.util.List;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of IModelManager to get
 * the UserDefinedCommands. It then displays these on a pane
 * that is created in View.
 */
public class UserDefinedCommandsView extends View {


    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param operator used for updating
     */
    public UserDefinedCommandsView(Operator operator){
        super(operator);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        this.clearLines();
        List<String> list = myManager.getUserDefinedCommands();
        for(String s : list){
            this.addFinalLine(s);
        }
    }
}
