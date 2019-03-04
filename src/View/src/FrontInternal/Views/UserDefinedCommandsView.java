package FrontInternal.Views;

import FrontInternal.Util.Operator;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.List;

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
        myOperator.parse(command);
    }
}
