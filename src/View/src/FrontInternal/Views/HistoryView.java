package FrontInternal.Views;

import FrontInternal.Util.Operator;

import java.util.List;
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
     * @param operator Handles parsing and updating
     */
    public HistoryView(Operator operator){
        super(operator);
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
        List<String> history = myManager.getHistory();
        for(int k =0; k <history.size(); k++){
            myViewTemplate.addFinalLine(history.get(k), myManager.getWasSuccessfulHistory(k));
        }
    }


}
