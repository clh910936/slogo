package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Components.Console;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of API.IModelManager to get
 * the UserDefinedCommands. It then displays these on a pane
 * that is created in View.
 */
public class UserDefinedCommandsView implements ViewAPI {
    private Console myConsole;
    private Accordion myAccordion;
    private IModelManager myManager;
    private Pane myPane;

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager used for updating
     */
    public UserDefinedCommandsView(IModelManager manager, Console console){
        myManager = manager;
        myConsole = console;
        myPane = new Pane();
        myAccordion = new Accordion();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(myAccordion);
        myPane.getChildren().add(scrollPane);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    @Override
    public void update() {
        //TODO: fix this so it has the tabs you were thinking
        myAccordion.getPanes().clear();
        Map<String, String> map = myManager.getUserDefinedCommands();
        for(String s : map.keySet()){
            addCommand(s, map.get(s));
        }
    }

    private void clear(){

    }

    @Override
    public Pane getPane() {
        return myPane;
    }

    //TODO: fix this javadoc comment
    /**
     * Adds a line of text to the bottom of the existing text
     * This line cannot be edited by the user
     * @param s String of text to be added
     */
    private void addCommand(String name, String contents){
        TitledPane dropDown = new TitledPane();
        dropDown.setText(name);

        Text text = new Text();
        text.setText(contents);
        Pane pane = new Pane();
        pane.getChildren().add(text);

        dropDown.setContent(pane);
        text.setOnMouseClicked(e -> handleMouseClicked(text));
        myAccordion.getPanes().add(dropDown);
    }

    private void handleMouseClicked(Text text){
        myConsole.display(text.getText());
    }
}
