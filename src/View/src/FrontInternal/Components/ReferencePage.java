package FrontInternal.Components;

import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author Carrie Hunner
 * This class creates a reference page of all the different Slogo commands.
 * In creating an instanve of ReferencePage, it will initialize all the necessary variables
 * and display the screen. It is coompletely self-contained.
 */
public class ReferencePage extends Stage{
    private TabPane myTabPane;
    private ResourceBundle myResourceBundle;
    private Scene myScene;

    private static final String RESOURCE_NAME = "ReferencePage";
    private static final String TXT = ".txt";
    private static final String FILE_SOURCE_PREFIX = "src/View/ReferencePageFiles/sprint1/";
    private static final String NLN = "\n";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    ReferencePage() {
        initializeVariables();
        this.setScene(myScene);
        createAllTabs();
    }

    private void initializeVariables() {
        myTabPane = new TabPane();
        myScene = new Scene(myTabPane, WIDTH, HEIGHT);
        myResourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
        this.setTitle(myResourceBundle.getString("Title"));
    }

    private void createAllTabs() {
        createAndAddTab("Turtle", "Turtle_Files");
        createAndAddTab("TurtleQ", "TurtleQ_Files");
        createAndAddTab("Pen", "Pen_Files");
        createAndAddTab("Math_Ops", "Math_Files");
        createAndAddTab("Boolean", "Boolean_Files");
        createAndAddTab("Other", "Other_Files");
    }

    /**
     *
     * @param tabNameKey desired name for the tab
     * @param propertyFileKey name to list of file names in ReferencePage.properties
     * @return
     */
    private void createAndAddTab(String tabNameKey, String propertyFileKey) {
        Tab tempTab = new Tab();
        Accordion tempAccordion = new Accordion();
        ScrollPane outerScrollPane = new ScrollPane();

        myTabPane.getTabs().add(tempTab);

        tempAccordion.setPrefWidth(WIDTH);
        outerScrollPane.setContent(tempAccordion);
        tempTab.setContent(outerScrollPane);
        tempTab.setText(myResourceBundle.getString(tabNameKey));

        String fileNamesString = myResourceBundle.getString(propertyFileKey);
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
            StringBuilder text = new StringBuilder("");
            while(in.ready()) {
                String line = in.readLine();
                text.append(NLN);
                text.append(line);
            }
            return text.toString();
        } catch (FileNotFoundException e) {
            return myResourceBundle.getString("REFERENCE_FILE_NOT_FOUND");
        } catch (IOException e) {
            return myResourceBundle.getString("REFERENCE_FILE_IO_PROBLEM");
        }

    }
}