package FrontInternal.Components;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ResourceBundle;

/**
 * @author Carrie Hunner
 * This class creates a reference page of all the different Slogo commands.
 * In creating an instanve of ReferencePage, it will initialize all the necessary variables
 * and display the screen. It is coompletely self-contained.
 */
public class ReferencePage {
    private Stage myStage;
    private TabPane myTabPane;
    private ResourceBundle myResourceBundle;
    private Scene myScene;

    private static final String RESOURCE_NAME = "ReferencePage";
    private static final String TXT = ".txt";
    private static final String FILE_SOURCE_PREFIX = "src/View/ReferencePageFiles/sprint1/";
    private static final String NLN = "\n";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    ReferencePage(Stage stage) {
        myStage = stage;
        initializeVariables();
        stage.setScene(myScene);
        createAllTabs();
        myStage.show();
    }

    private void initializeVariables() {
        myTabPane = new TabPane();
        myScene = new Scene(myTabPane, WIDTH, HEIGHT);
        myResourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
        myStage.setTitle(myResourceBundle.getString("Title"));
    }

    private void createAllTabs() {
        createAndAddTab("Turtle Commands", "Turtle_Files");
        createAndAddTab("Turtle Queries", "TurtleQ_Files");
        createAndAddTab("Math Operations", "Math_Files");
        createAndAddTab("Boolean Operations", "Boolean_Files");
        createAndAddTab("Other", "Other_Files");
    }

    /**
     *
     * @param tabName desired name for the tab
     * @param propertyKey name to list of file names in ReferencePage.properties
     * @return
     */
    private void createAndAddTab(String tabName, String propertyKey) {
        Tab tempTab = new Tab();
        Accordion tempAccordion = new Accordion();
        ScrollPane outerScrollPane = new ScrollPane();

        myTabPane.getTabs().add(tempTab);

        tempAccordion.setPrefWidth(WIDTH);
        outerScrollPane.setContent(tempAccordion);
        tempTab.setContent(outerScrollPane);
        tempTab.setText(tabName);

        String fileNamesString = myResourceBundle.getString(propertyKey);
        String[] fileNames = fileNamesString.split(" ");
        for (String s : fileNames){
            String stringContents = readFile(s);
            Text text = new Text(stringContents);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(text);
            TitledPane pane = new TitledPane();
            pane.setText(s);
            pane.setContent(scrollPane);
            tempAccordion.getPanes().add(pane);

        }
    }

    private String readFile(String name) {
        String fileName = FILE_SOURCE_PREFIX + name + TXT;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
            String text = "";
            while(in.ready()) {
                String line = in.readLine();
                text = text + NLN + line;
            }
            return text;
        } catch (FileNotFoundException e) {
            return myResourceBundle.getString("REFERENCE_FILE_NOT_FOUND");
        } catch (IOException e) {
            return myResourceBundle.getString("REFERENCE_FILE_IO_PROBLEM");
        }

    }
}