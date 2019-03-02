package FrontInternal.Components;

import BackExternal.IllegalCommandException;
import FrontInternal.Util.Operator;
import FrontInternal.Views.ErrorView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Console extends Stage {
    //private CommandParser myParser;
    private BorderPane myBorderPane;
    private HBox myTextHBox;
    private GridPane myButtonGridPane;
    private ComboBox myLanguageDropDown;
    private Operator myOperator;
    private ReferencePage myReferencePage;

    private ResourceBundle myResourcesBundle;

    private Button myRunButton;
    private Button myReferencePageButton;
    private List<Button> myButtonList;
    private List<String> myLanguages;
    private TextArea myUserInputField;
    private ErrorView myErrorView;

    private final int CONSOLE_WIDTH = 500;
    private final int CONSOLE_HEIGHT = 300;

    private final int BUTTON_WIDTH = 80;
    private final int BUTTON_INSET = 5;
    private final int BUTTON_PANE_WIDTH = BUTTON_WIDTH + 50;
    private final int BUTTON_VGAP = 10;
    private final String RESOURCE_FILENAME = "Console";
    private final String RUN_BUTTON = "RUN_BUTTON";
    private Insets myButtonInsets;
    private boolean displaying = true;


    //public Console(Stage stage, CommandParser parser){
    public Console (Operator operator){
        myOperator = operator;

        initializeInstanceVariables();
        initializeLanguageList();

        createRunButton();
        createReferencePageButton();
        addButtons();
        initializeDropDown();
        formatButtonGridPane();

        myBorderPane.setCenter(myTextHBox);
        myBorderPane.setRight(myButtonGridPane);
        myBorderPane.setBottom(myErrorView);

        Scene consoleScene = new Scene(myBorderPane, CONSOLE_WIDTH, CONSOLE_HEIGHT);
        this.setScene(consoleScene);
        setOnCloseRequest(e -> displaying = false);
        this.show();
    }

    private void initializeInstanceVariables() {
        myButtonList = new ArrayList<>();
        myLanguages = new ArrayList<>();
        myResourcesBundle = ResourceBundle.getBundle(RESOURCE_FILENAME);

        myButtonGridPane = new GridPane();
        myBorderPane = new BorderPane();
        myLanguageDropDown = new ComboBox();
        myErrorView = myOperator.getErrorPane();
        myButtonInsets = new Insets(BUTTON_INSET, BUTTON_INSET, BUTTON_INSET, BUTTON_INSET);
        myUserInputField = new TextArea();
        myTextHBox = new HBox(myUserInputField);
        myReferencePage = new ReferencePage();
    }

    private void initializeLanguageList(){
        String languages = myResourcesBundle.getString("POSSIBLE_LANGUAGES");
        String[] temp = languages.split(" ");
        for(String s : temp){
            myLanguages.add(s);
        }
    }

    private void initializeDropDown(){
        myLanguageDropDown.setPrefWidth(BUTTON_WIDTH);
        myLanguageDropDown.getItems().addAll(myLanguages);
        myLanguageDropDown.setValue(myResourcesBundle.getString("DEFAULT_LANGUAGE"));
        myButtonGridPane.add(myLanguageDropDown, 0, myButtonList.size());
    }

    private void formatButtonGridPane() {
        myButtonGridPane.setPrefWidth(BUTTON_PANE_WIDTH);

        myButtonGridPane.setVgap(BUTTON_VGAP);
        myButtonGridPane.setAlignment(Pos.TOP_CENTER);
    }

    private void createRunButton() {
        myRunButton = createAndFormatButton(myResourcesBundle.getString(RUN_BUTTON));
        myRunButton.setOnMouseClicked(e -> readText());
    }

    private void createReferencePageButton(){
        myReferencePageButton = createAndFormatButton(myResourcesBundle.getString("REFERENCE_BUTTON"));
        myReferencePageButton.setOnMouseClicked(e -> myReferencePage.show());
    }

    private void readText() {
        myErrorView.clearError();
        String input = myUserInputField.getText();
        String language = String.valueOf(myLanguageDropDown.getValue());
        myOperator.parse(input, language);
    }

    /**
     * Displays input text to the user on the bottom of the Console
     * @param s Text displayed to user
     */
    public void showError(String s){
        myErrorView.displayError(s);
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

    public boolean getDisplaying(){
        return displaying;
    }
}
