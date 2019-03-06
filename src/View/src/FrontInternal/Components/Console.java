package FrontInternal.Components;

import API.IModelManager;
import FrontInternal.Views.ErrorView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Console extends Stage {
    //private CommandParser myParser;
    private BorderPane myBorderPane;
    private HBox myTextHBox;
    private GridPane myButtonGridPane;
    private ComboBox myLanguageDropDown;
    private IModelManager myManager;
    private ReferencePage myReferencePage;
    private TextField myStateNameField;

    private ResourceBundle myGeneralResourceBundle;
    private ResourceBundle myButtonsResourceBundle;

    private Button myRunButton;
    private Button myReferencePageButton;
    private Button myLoadFileButton;
    private List<Button> myButtonList;
    private List<String> myLanguages;
    private TextArea myUserInputField;
    private ErrorView myErrorView;
    private GridPane mySaveStatePane;

    private static final int CONSOLE_WIDTH = 500;
    private static final int CONSOLE_HEIGHT = 300;

    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_INSET = 5;
    private static final int ERROR_HEIGHT = CONSOLE_HEIGHT/10;
    private static final int BUTTON_PANE_WIDTH = BUTTON_WIDTH + 50;
    private static final int BUTTON_VGAP = 10;
    private static final String GENERAL_RESOURCE_FILENAME = "Console";
    private static final String BUTTON_RESOURCE_FILENAME = "ConsoleButtons";
    private static final String RUN_BUTTON = "RUN_BUTTON";
    private Insets myButtonInsets;
    private boolean isDisplaying = true;


    //public Console(Stage stage, CommandParser parser){
    public Console (IModelManager manager){
        myManager = manager;

        initializeInstanceVariables();
        initializeLanguageList();
        initializeSaveStatePane();

        createRunButton();
        createReferencePageButton();
        createLoadFileButton();
        addButtons();
        initializeDropDown();
        formatButtonGridPane();

        myBorderPane.setCenter(myTextHBox);
        myBorderPane.setRight(myButtonGridPane);
        myBorderPane.setBottom(myErrorView);

        Scene consoleScene = new Scene(myBorderPane, CONSOLE_WIDTH, CONSOLE_HEIGHT);
        this.setScene(consoleScene);
        setOnCloseRequest(e -> isDisplaying = false);
        this.show();
    }

    private void initializeSaveStatePane() {
        myStateNameField.setPromptText(myGeneralResourceBundle.getString("NAME_PROMPT"));
        Button saveState = createAndFormatButton(myGeneralResourceBundle.getString("STATE_BUTTON"));
        saveState.setOnMouseClicked(e -> saveState());
        mySaveStatePane.add(myStateNameField, 0, 0);
        mySaveStatePane.add(saveState, 0, 1);
    }

    private void saveState() {
        if(!myStateNameField.getText().equals("")){
            myManager.saveCurrentState(myStateNameField.getText());
        }
        else{
            showError(myGeneralResourceBundle.getString("NO_FILENAME"));
        }
    }

    private void createLoadFileButton() {
        myLoadFileButton = createAndFormatButton(myGeneralResourceBundle.getString("LOAD_BUTTON"));
        myLoadFileButton.setOnMouseClicked(e -> loadFile());
    }

    private void loadFile() {
        File file = getFile();
        try {
            Scanner scanner = new Scanner(file);
            String fileContents = new String();
            while(scanner.hasNext()){
                //TODO: make sure the NLN character is working in the properties file
                fileContents += scanner.nextLine() + myGeneralResourceBundle.getString("NLN");
            }
            myUserInputField.setText(fileContents);
        } catch (FileNotFoundException e) {
            myErrorView.displayError(myGeneralResourceBundle.getString("FILE_NOT_FOUND"));
        }
    }

    private File getFile() {
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("Slogo files (*.logo)", "*.logo");
        chooser.getExtensionFilters().addAll(extFilter2, extFilter);
        return chooser.showOpenDialog(stage);
    }

    private void initializeInstanceVariables() {
        myButtonList = new ArrayList<>();
        myLanguages = new ArrayList<>();
        myGeneralResourceBundle = ResourceBundle.getBundle(GENERAL_RESOURCE_FILENAME);
        myButtonsResourceBundle = ResourceBundle.getBundle(BUTTON_RESOURCE_FILENAME);
        mySaveStatePane = new GridPane();
        myStateNameField = new TextField();

        myButtonGridPane = new GridPane();
        myBorderPane = new BorderPane();
        myLanguageDropDown = new ComboBox();
        myErrorView = new ErrorView(ERROR_HEIGHT);
        myButtonInsets = new Insets(BUTTON_INSET, BUTTON_INSET, BUTTON_INSET, BUTTON_INSET);
        myUserInputField = new TextArea();
        myTextHBox = new HBox(myUserInputField);
        myReferencePage = new ReferencePage();
    }

    private void initializeLanguageList(){
        String languages = myGeneralResourceBundle.getString("POSSIBLE_LANGUAGES");
        String[] temp = languages.split(" ");
        for(String s : temp){
            myLanguages.add(s);
        }
    }

    private void initializeDropDown(){
        myLanguageDropDown.setPrefWidth(BUTTON_WIDTH);
        myLanguageDropDown.getItems().addAll(myLanguages);
        myLanguageDropDown.setValue(myGeneralResourceBundle.getString("DEFAULT_LANGUAGE"));
        myButtonGridPane.add(myLanguageDropDown, 0, myButtonList.size());
    }

    private void formatButtonGridPane() {
        myButtonGridPane.setPrefWidth(BUTTON_PANE_WIDTH);

        myButtonGridPane.setVgap(BUTTON_VGAP);
        myButtonGridPane.setAlignment(Pos.TOP_CENTER);
    }

    private void createRunButton() {
        myRunButton = createAndFormatButton(myGeneralResourceBundle.getString(RUN_BUTTON));
        myRunButton.setOnMouseClicked(e -> readText());
    }

    private void createReferencePageButton(){
        myReferencePageButton = createAndFormatButton(myGeneralResourceBundle.getString("REFERENCE_BUTTON"));
        myReferencePageButton.setOnMouseClicked(e -> myReferencePage.show());
    }

    //TODO: implement this and test it
    private void makeButton(String s){
        Button temp = new Button();
        String info = myButtonsResourceBundle.getString(s);
        String[] array = info.split(",");
        String buttonName = array[0];
        temp.setText(buttonName);
        String methodName = array[1];
        temp.setOnMouseClicked(e -> {
        try {
            Method method = this.getClass().getDeclaredMethod(methodName);
        } catch (NoSuchMethodException ex) {
            //TODO: get rid of printing stack trace
            ex.printStackTrace();
        }});
    }

    private void showReferencePage(){
        myReferencePage.show();
    }

    private void readText() {
        myErrorView.clearError();
        String input = myUserInputField.getText();
        String language = String.valueOf(myLanguageDropDown.getValue());
        myManager.parseCommand(input, language);
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
        myButtonGridPane.add(mySaveStatePane, 0, myButtonGridPane.getChildren().size());
    }

    private Button createAndFormatButton(String s){
        Button temp = new Button(s);
        temp.setPrefWidth(BUTTON_WIDTH);
        temp.setPadding(myButtonInsets);
        myButtonList.add(temp);
        return temp;
    }

    public boolean getDisplaying(){
        return isDisplaying;
    }
}
