package FrontInternal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Console {
    private Stage myStage;
    private BorderPane myBorderPane;
    private HBox myTextHBox;
    private GridPane myButtonGridPane;
    private HBox myErrorBox;
    private ComboBox myLanguageDropDown;

    private ResourceBundle myResourcesBundle;

    private Button myRunButton;
    private List<Button> myButtonList;
    private List<String> myLanguages;
    private TextArea myUserInputField;

    private final Paint myBorderColor = Color.WHITE;
    private final int CONSOLE_WIDTH = 500;
    private final int CONSOLE_HEIGHT = 300;
    private final int BUTTON_WIDTH = 60;
    private final int BUTTON_INSET = 5;
    private final int BUTTON_PANE_WIDTH = BUTTON_WIDTH + 50;
    private final int BUTTON_VGAP = 10;
    private final String RESOURCE_FILENAME = "Console";
    private final String RUN_BUTTON = "RUN_BUTTON";

    private Insets myButtonInsets;


    public Console(Stage stage){
        myStage = stage;
        initializeInstanceVariables();

        //TODO: format button grid pane
        formatButtonGridPane();
        createRunButton();
        addButtons();

        initializeDropDown();

        myBorderPane.setCenter(myTextHBox);

        myBorderPane.setRight(myButtonGridPane);

        Scene consoleScene = new Scene(myBorderPane, CONSOLE_WIDTH, CONSOLE_HEIGHT);
        myStage.setScene(consoleScene);
    }

    private void initializeInstanceVariables() {
        myButtonList = new ArrayList<>();
        myLanguages = new ArrayList<>();
        myResourcesBundle = ResourceBundle.getBundle(RESOURCE_FILENAME);
        initializeLanguageList();
        myButtonGridPane = new GridPane();
        myBorderPane = new BorderPane();
        myErrorBox = new HBox();
        myLanguageDropDown = new ComboBox();
        myButtonInsets = new Insets(BUTTON_INSET, BUTTON_INSET, BUTTON_INSET, BUTTON_INSET);
        myUserInputField = new TextArea();
        myTextHBox = new HBox(myUserInputField);

    }

    private void initializeLanguageList(){
        String languages = myResourcesBundle.getString("POSSIBLE_LANGUAGES");
        String[] temp = languages.split(" ");
        for(String s : temp){
            myLanguages.add(s);
        }
    }

    //TODO: fix magic values
    //TODO: bind language to variable input into parse
    private void initializeDropDown(){
        myLanguageDropDown.getItems().addAll(myLanguages);
        myButtonGridPane.add(myLanguageDropDown, 0, 1);
    }

    private void formatButtonGridPane() {
        myButtonGridPane.setPrefWidth(BUTTON_PANE_WIDTH);
        myButtonGridPane.setAlignment(Pos.TOP_CENTER);
        myButtonGridPane.setVgap(BUTTON_VGAP);
    }

    private void createRunButton() {
        myRunButton = createAndFormatButton(myResourcesBundle.getString(RUN_BUTTON));
        myRunButton.setOnMouseClicked(e -> readText());
    }

    private void readText() {
        String input = myUserInputField.getText();
        String language = (String) myLanguageDropDown.getValue();
        System.out.println(language);
        //TODO: Parse
        System.out.println(input);
    }

    private void addButtons(){
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
