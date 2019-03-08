package FrontInternal.Components;

import API.IModelManager;
import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
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
import javafx.scene.layout.VBox;
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
    private VBox myVBox;
    private ComboBox myLanguageDropDown;
    private IModelManager myManager;
    private ReferencePage myReferencePage;
    private TextField myStateNameField;

    private ResourceBundle myGeneralResourceBundle;
    private ResourceBundle myButtonsResourceBundle;
    private ResourceBundle myErrorResourceBundle;

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
    private static final String ERROR_RESOURCE_FILENAME = "Errors";
    private static final String RUN_BUTTON = "RUN_BUTTON";
    private Insets myButtonInsets;
    private boolean isDisplaying = true;


    //public Console(Stage stage, CommandParser parser){
    public Console (){
        initializeInstanceVariables();
        initializeLanguageList();

        createAllButtons();
        addButtons();
        initializeDropDown();
        initializeSaveStatePane();

        myVBox.getChildren().add(mySaveStatePane);
        formatSidePane();

        myBorderPane.setCenter(myTextHBox);
        myBorderPane.setRight(myVBox);
        myBorderPane.setBottom(myErrorView);

        Scene consoleScene = new Scene(myBorderPane, CONSOLE_WIDTH, CONSOLE_HEIGHT);
        this.setScene(consoleScene);
        setOnCloseRequest(e -> isDisplaying = false);
        this.show();
    }

    public void setModelManager(IModelManager manager){
        myManager = manager;
    }
    private void createAllButtons() {
        for(String s : myButtonsResourceBundle.keySet()){
            String info[] = myButtonsResourceBundle.getString(s).split(",");
            String name = info[0];
            String methodName = info[1];
            Button b = makeButton(methodName);
            b.setText(name);
            b.setPrefWidth(BUTTON_WIDTH);
            b.setPadding(myButtonInsets);
            myButtonList.add(b);
        }
    }

    private void initializeSaveStatePane() {
        myStateNameField.setPromptText(myGeneralResourceBundle.getString("NAME_PROMPT"));
        myStateNameField.setPrefWidth(BUTTON_WIDTH);
        Button saveState = createAndFormatButton(myGeneralResourceBundle.getString("STATE_BUTTON"));
        saveState.setOnMouseClicked(e -> saveState());
        saveState.setPrefWidth(BUTTON_WIDTH);
        mySaveStatePane.add(myStateNameField, 0, 0);
        mySaveStatePane.add(saveState, 0, 1);
    }

    private void saveState() {
        if(!myStateNameField.getText().equals("")){
            myManager.saveCurrentState(myStateNameField.getText());
            myStateNameField.clear();
        }
        else{
            showError(myErrorResourceBundle.getString("NO_FILENAME"));
        }
    }

    private void loadFile() {
        File file = getFile();
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder fileContents = new StringBuilder();
            while(scanner.hasNext()){
                fileContents.append(scanner.nextLine());
                fileContents.append(myGeneralResourceBundle.getString("NLN"));
            }
            myUserInputField.setText(fileContents.toString());
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
        myErrorResourceBundle = ResourceBundle.getBundle(ERROR_RESOURCE_FILENAME);
        mySaveStatePane = new GridPane();
        myStateNameField = new TextField();

        myVBox = new VBox();
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
        myVBox.getChildren().add(myLanguageDropDown);
    }

    private void formatSidePane() {
        myVBox.setPrefWidth(BUTTON_PANE_WIDTH);
        myVBox.setSpacing(BUTTON_VGAP);
        myVBox.setAlignment(Pos.TOP_CENTER);
    }

    private Button makeButton(String s){
        System.out.println("Making button");
        Button temp = new Button();
        temp.setOnMouseClicked(e -> {
        try {
            Method method = this.getClass().getDeclaredMethod(s);
            method.invoke(this);
        } catch (Exception e1) {
            myErrorView.displayError(myErrorResourceBundle.getString("BUTTON_ERROR"));
        }});
        return temp;
    }

    private void showReferencePage(){
        myReferencePage.show();
    }

    private void readText() {
        myErrorView.clearError();
        String input = myUserInputField.getText();
        String language = String.valueOf(myLanguageDropDown.getValue());
        try {
            myManager.parseCommand(input, language);
        }
        catch (IllegalCommandException e){
            showError(myErrorResourceBundle.getString("COMMAND"));
        }
        catch (IllegalParametersException e){
            showError(myErrorResourceBundle.getString("PARAMS"));
        }
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
            myVBox.getChildren().add(myButtonList.get(k));
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
        return isDisplaying;
    }

    /**
     * Can be used to write to the Console
     * Allows user-defined commands to be written to the console
     * @param s String of the desired text to be displayed
     */
    public void display(String s){
        myUserInputField.setText(s);
    }
}
