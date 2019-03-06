package FrontInternal.Views;

import API.IModelManager;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of API.IModelManager to get
 * the UserDefinedCommands. It then displays these on a pane
 * that is created in View.
 */
public class UserDefinedCommandsView extends View {
    private static final Paint USER_COMMAND = Color.BLACK;


    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager used for updating
     */
    public UserDefinedCommandsView(IModelManager manager){
        super(manager);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        //TODO: fix this so it has the tabs you were thinking
        this.clearLines();
        Map<String, String> map = myManager.getUserDefinedCommands();
        for(String s : map.keySet()){
            this.addFinalLine(s);
        }
    }

    /**
     * Adds a line of text to the bottom of the existing text
     * This line cannot be edited by the user
     * @param s String of text to be added
     */
    private void addFinalLine(String s){
        Text text = createTextLine(s, USER_COMMAND);
        text.setOnMouseClicked(e -> handleMouseClicked(text));
    }

    private void handleMouseClicked(Text text){
        String command = text.getText();
        myManager.parseCommand(command, DEFAULT_LANGUAGE);
    }
}
