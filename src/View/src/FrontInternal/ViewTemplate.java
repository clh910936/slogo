package FrontInternal;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ViewTemplate extends BorderPane {
    private GridPane myGridPane;
    private HBox myTitleBox;
    private ScrollPane myVariableScroll;
    private String myTitle;
    private int myGridIndex;

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

    public void addFinalLine(String s){
        HBox tempHBox = new HBox();
        Text tempText = new Text(s);
        tempHBox.getChildren().add(tempText);
        myGridPane.add(tempHBox, 0, myGridIndex);
        myGridIndex++;
    }


}
