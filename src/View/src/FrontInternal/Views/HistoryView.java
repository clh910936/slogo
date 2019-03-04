package FrontInternal.Views;

import FrontInternal.Util.Operator;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.List;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of IModelManager to get
 * the History. It then displays these on a pane
 * that is created in View.
 */
public class HistoryView extends View {
    private static final Paint PARSED = Color.GREEN;
    private static final Paint NOT_PARSED = Color.RED;

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param operator Handles parsing and updating
     */
    public HistoryView(Operator operator){
        super(operator);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        this.clearLines();
        List<String> history = myManager.getHistory();
        for(int k =0; k <history.size(); k++){
            this.addFinalLine(history.get(k), myManager.getWasSuccessfulHistory(k));
        }
    }

    private void addFinalLine(String s, Boolean bool){
        Paint color = determineColor(bool);
        Text text = createTextLine(s, color);
        text.setOnMouseClicked(e -> handleClick(text));
    }

    private void handleClick(Text text) {
        String command = text.getText();
        myOperator.parse(command);
    }

    private Paint determineColor(Boolean bool) {
        Paint color;
        if(bool){
            color = PARSED;
        }
        else{
            color = NOT_PARSED;
        }
        return color;
    }


}
