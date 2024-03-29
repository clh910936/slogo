package FrontInternal.Views;

import API.IModelManager;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of API.IModelManager to get
 * the History. It then displays these on a pane
 * that is created in View.
 */
public class HistoryView extends View {
    private static final Paint PARSED = Color.GREEN;
    private static final Paint NOT_PARSED = Color.RED;
    private VBox myVBox;

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager Handles parsing and updating
     */
    public HistoryView(IModelManager manager){
        super(manager);
        myVBox = new VBox();
        setContents(myVBox);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        System.out.println("Updating History");
        myVBox.getChildren().clear();
        List<String> history = myManager.getHistory();
        for(int k =0; k <history.size(); k++){
            this.addFinalLine(history.get(k), myManager.getSuccessOfHistoryEntry(k));
        }
    }

    private void addFinalLine(String s, Boolean bool){
        Paint color = determineColor(bool);
        Text text = createTextLine(s, color);
        text.setOnMouseClicked(e -> handleClick(text));
    }

    private Text createTextLine(String s, Paint color) {
        Text text = new Text();
        HBox tempHBox = new HBox();
        text.setFill(color);
        text.setText(s);
        myVBox.getChildren().add(text);
        return text;
    }

    private void handleClick(Text text) {
        String command = text.getText();
        myManager.parseCommand(command, DEFAULT_LANGUAGE);
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
