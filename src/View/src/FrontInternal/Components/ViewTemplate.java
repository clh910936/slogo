package FrontInternal.Components;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
 * This is used in both ListView and MapView to format the HistoryView, VariablesView,
 * and UserDefinedCommandsView
 */
class ViewTemplate extends BorderPane {
    private GridPane myGridPane;
    private HBox myTitleBox;
    private ScrollPane myVariableScroll;
    private String myTitle;
    private int myGridIndex;
    private static final Paint PARSED = Color.GREEN;
    private static final Paint NOT_PARSED = Color.RED;

    /**
     * Creates a ListView object with the Title set and no content text
     * @param title String of the name to appear at the top of the pane
     */
    ViewTemplate(String title){
        myGridPane = new GridPane();
        myTitleBox = new HBox();
        myVariableScroll = new ScrollPane();
        myGridIndex = 0;

        myTitle = title;
        initializeTitleBox();
        myVariableScroll.setContent(myGridPane);
        this.setCenter(myVariableScroll);
    }

    private void initializeTitleBox(){
        Text title = new Text(myTitle);
        myTitleBox.getChildren().add(title);
        myTitleBox.setAlignment(Pos.TOP_CENTER);
        this.setTop(myTitleBox);
    }

    /**
     * Adds a line of text to the bottom of the existing text
     * This line cannot be edited by the user
     * @param s String of text to be added
     */
    protected void addFinalLine(String s){
        createTextLine(s);
    }
    protected void addFinalLine(String s, Boolean bool){
        Text temp = createTextLine(s);
        if(bool){
            temp.setFill(PARSED);
        }
        else{
            temp.setFill(NOT_PARSED);
        }
    }

    private Text createTextLine(String s) {
        HBox tempHBox = new HBox();
        Text tempText = new Text(s);
        tempHBox.getChildren().add(tempText);
        myGridPane.add(tempHBox, 0, myGridIndex);
        myGridIndex++;
        return tempText;
    }



    /**
     * Removes all lines of text from the pane
     */
    protected void clearLines(){
        myGridPane.getChildren().clear();
        myGridIndex = 0;
    }


}
