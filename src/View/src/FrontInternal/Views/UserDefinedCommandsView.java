package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Components.Console;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private Console myConsole;
    private Accordion myAccordion;
    private VBox myVBox;

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager used for updating
     */
    public UserDefinedCommandsView(IModelManager manager, Console console){
        super(manager);
        myConsole = console;
        myVBox = new VBox();
        myAccordion = new Accordion();
        myVBox.getChildren().add(myAccordion);
        setContents(myVBox);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        myAccordion.getPanes().clear();
        Map<String, String> map = myManager.getUserDefinedCommands();
        for(Map.Entry<String, String> entry : map.entrySet()){
            addCommand(entry.getKey(), entry.getValue());
        }
    }

    private void clear(){

    }

    @Override
    public Pane getPane() {
        return myVBox;
    }

    //TODO: fix this javadoc comment
    private void addCommand(String name, String contents){
        TitledPane dropDown = new TitledPane();
        dropDown.setText(name);

        Text text = new Text();
        text.setText(contents);
        ScrollPane pane = new ScrollPane();
        pane.setContent(text);

        dropDown.setContent(pane);
        myAccordion.getPanes().add(dropDown);
    }
}
