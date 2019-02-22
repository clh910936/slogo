import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Console {
    private Stage myStage;
    private BorderPane myBorderPane;
    private HBox myTextHBox;
    private GridPane myButtonGridPane;

    private Button myRunButton;
    private List<Button> myButtonList;
    private TextArea myUserInputField;

    private Background myBackground;
    private Border myBorder;

    private final Paint myBorderColor = Color.WHITE;
    private final int CONSOLE_WIDTH = 500;
    private final int CONSOLE_HEIGHT = 300;
    private final int BUTTON_WIDTH = 60;
    private final int BUTTON_INSET = 5;
    private final int BUTTON_PANE_WIDTH = BUTTON_WIDTH + 50;
    private final int BUTTON_VGAP = 10;

    private Insets myButtonInsets;


    Console(Stage stage){
        myStage = stage;
        myButtonList = new ArrayList<>();
        myButtonGridPane = new GridPane();

        //TODO: initialize Pane method
        myBorderPane = new BorderPane();

        //TODO: initialize common graphics method CHECK IF CORNER RADII
        myBackground = new Background(new BackgroundFill(myBorderColor, CornerRadii.EMPTY, Insets.EMPTY));
        myBorder = new Border(new BorderStroke(myBorderColor, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1)));
        myButtonInsets = new Insets(BUTTON_INSET, BUTTON_INSET, BUTTON_INSET, BUTTON_INSET);

        //TODO: initialize user field
        myUserInputField = new TextArea();
        myTextHBox = new HBox(myUserInputField);

        myRunButton = createAndFormatButton("Run");
        myRunButton.setOnMouseClicked(e -> readText());


        initializeButtonGridPane();

        myBorderPane.setCenter(myTextHBox);
        myBorderPane.setRight(myButtonGridPane);

        Scene consoleScene = new Scene(myBorderPane, CONSOLE_WIDTH, CONSOLE_HEIGHT);
        myStage.setScene(consoleScene);
    }

    private void readText() {
        String input = myUserInputField.getText();
    }

    private void initializeButtonGridPane(){
        myButtonGridPane.setPrefWidth(BUTTON_PANE_WIDTH);
        myButtonGridPane.setAlignment(Pos.TOP_CENTER);
        myButtonGridPane.setVgap(BUTTON_VGAP);
        for(int k = 0; k < myButtonList.size(); k++){
            myButtonGridPane.add(myButtonList.get(k), 0, k);
        }
    }

    private Button createAndFormatButton(String s){
        Button temp = new Button(s);
        temp.setPrefWidth(BUTTON_WIDTH);
        temp.setPadding(myButtonInsets);
        myButtonList.add(temp);
        return temp;
    }



}
