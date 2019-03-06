package FrontInternal.Views;

import API.IModelManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

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
    protected IModelManager myManager;
    protected static final Paint DEFAULT_COLOR = Color.BLACK;

    private BorderPane myBorderPane;
    private GridPane myGridPane;
    private ScrollPane myVariableScroll;
    protected int myGridIndex;
    protected static final String DEFAULT_LANGUAGE = "English";



    /**
     * Used to create a template for the Variable,User Defined Commands, and History view
     */
    public View(IModelManager manager){
        myManager = manager;
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



    protected Text createTextLine(String s, Paint color) {
        Text text = new Text();
        HBox tempHBox = new HBox();
        text.setFill(color);
        text.setText(s);
        tempHBox.getChildren().add(text);
        addToGridPane(tempHBox);
        return text;
    }

    //Called in Variable view because VariableView needed to create its own
    //method to add new lines
    protected void addToGridPane(Pane p){
        myGridPane.add(p, 0, myGridIndex);
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
