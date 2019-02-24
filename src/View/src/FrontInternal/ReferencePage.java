package FrontInternal;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ResourceBundle;


public class ReferencePage {

    private Stage myStage;
    private TabPane myTabPane;

    private ResourceBundle myResourceBundle;

    private static final String RESOURCE_NAME = "ReferencePage";
    private static final String TXT = ".txt";
    private static final String FILE_SOURCE_PREFIX = "src/View/ReferencePageFiles/sprint1/";
    private static final String NLN = "\n";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;


    ReferencePage(Stage stage) {
        myStage = stage;
        myStage.setTitle("Command References");
        myResourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);

        myTabPane = new TabPane();

        createAndAddTab("Turtle Commands", "Turtle_Files");
        createAndAddTab("Turtle Queries", "TurtleQ_Files");
        createAndAddTab("Math Operations", "Math_Files");
        createAndAddTab("Boolean Operations", "Boolean_Files");
        createAndAddTab("Other", "Other_Files");
        //Tab basicTab = new Tab(myResourceBundle.getString("BASIC"));
        //basicTab.setContent(myBasicAccordion);

        //File f = new File("and.txt");

        //Text text = new Text(readFile(f.getAbsolutePath()));
        //TitledPane temp = new TitledPane();
        //temp.setContent(text);
        //myBasicAccordion.getPanes().add(temp);

        /**
         *
         */

        //myTabPane.getTabs().add(basicTab);



//        for(int k = 0; k < 3; k++){
//            TitledPane temp = new TitledPane();
//            Text text = new Text(Integer.toString(k));
//            temp.setContent(text);
//            myBasicAccordion.getPanes().add(temp);
//        }
//        myTabPane.getTabs().add(basicTab);
//
//        Tab turtleTab = new Tab("Turtle Commands");
//        turtleTab.setContent(myTurtleAccordion);
//        for(int k = 0; k < 3; k++){
//            TitledPane temp = new TitledPane();
//            Text text = new Text(Integer.toString(k));
//            temp.setContent(text);
//            myTurtleAccordion.getPanes().add(temp);
//        }
//        myTabPane

        Scene scene = new Scene(myTabPane, WIDTH, HEIGHT);
        stage.setScene(scene);
        //stage.show();
    }

    /**
     *
     * @param tabName desired name for the tab
     * @param propertyKey name to list of file names in ReferencePage.properties
     * @return
     */
    private void createAndAddTab(String tabName, String propertyKey) {
        Tab tempTab = new Tab();
        myTabPane.getTabs().add(tempTab);
        ScrollPane outerScrollPane = new ScrollPane();

        Accordion tempAccordion = new Accordion();
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

    //TODO: Deal with possible IOException
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
