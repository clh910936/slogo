package FrontInternal.Views;

import API.IModelManager;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of API.IModelManager to get
 * the SavedStates. It then displays these on a pane
 * that is created in View.
 * Users can click on a state to load its variables and commands
 */
public class SavedStateView extends View {
    private VBox myVBox;


    /**
     * Creates a pane that displays past saved states
     * @param manager Enables this view to communicate with the backend savedstate model
     */
    public SavedStateView(IModelManager manager) {
        super(manager);
        myVBox = new VBox();
        setContents(myVBox);
        update();
    }


    /**
     * Updates the pane to display all Saved States
     */
    @Override
    public void update() {
        myVBox.getChildren().clear();
        List<String> list = myManager.getSavedFilesList();
        for(String s : list){
            Text text = createTextLine(s, DEFAULT_COLOR);
            text.setOnMouseClicked(e -> handleMouseClicked(s));
        }
    }

    private void handleMouseClicked(String name){
        myManager.setStateFromFile(name, DEFAULT_LANGUAGE);
    }
    private Text createTextLine(String s, Paint color){
        Text text = new Text();
        text.setText(s);
        text.setFill(color);
        myVBox.getChildren().add(text);
        return text;
    }
}
