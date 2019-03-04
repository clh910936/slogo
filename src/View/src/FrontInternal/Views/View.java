package FrontInternal.Views;

import BackExternal.IModelManager;
import FrontInternal.Util.Operator;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * @author Carrie Hunner
 * This class creates a template for a user view window with a title at the top
 * and the easy ability to add individual lines of text as well as clear the text.
 *
 * It extends BorderPane such that it is a Pane object that can be easily addded to a scene.
 *
 * This is used to format the HistoryView, VariablesView,
 * and UserDefinedCommandsView
 */
public abstract class View implements ViewAPI {
    protected Operator myOperator;
    protected IModelManager myManager;

    private BorderPane myBorderPane;
    private GridPane myGridPane;
    private ScrollPane myVariableScroll;
    protected int myGridIndex;



    /**
     * Used to create a template for the Variable,User Defined Commands, and History view
     */
    public View(Operator operator){
        myOperator = operator;
        myManager = operator.getManager();
        myBorderPane = new BorderPane();

        myBorderPane.setPrefHeight(100);
        myBorderPane.setPrefWidth(100);

        myGridPane = new GridPane();
        myVariableScroll = new ScrollPane();
        myGridIndex = 0;

        myVariableScroll.setContent(myGridPane);
        myBorderPane.setCenter(myVariableScroll);
        myVariableScroll.setBorder(Border.EMPTY);
    }



    protected void createTextLine(HBox tempHBox, Text tempText) {
        tempHBox.getChildren().add(tempText);
        myGridPane.add(tempHBox, 0, myGridIndex);
        myGridIndex++;
    }



    /**
     * Removes all lines of text from the pane
     */
    protected void clearLines(){
        myGridPane.getChildren().clear();
        myGridIndex = 0;
    }


    @Override
    public abstract void update();

    @Override
    public Pane getPane() {
        return myBorderPane;
    }
}
