package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Components.Console;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

/**
 * Extends Accordion and implements ViewAPI.
 * It creates an Accordion of all the Views listed in the
 * ViewDropDown.properties file.
 * In this file, the key is the name of the class
 * and the value is the text displayed to the User
 */
public class  AllUserViews extends VBox implements ViewAPI  {
    private ResourceBundle myViewClassResources;
    private ResourceBundle myErrorResources;
    private IModelManager myManager;
    private Console myConsole;
    private List<View> myViews;
    private ScrollPane myScrollPane;
    private static final int WIDTH = 200;

    public AllUserViews(IModelManager manager, Console console){
        myViewClassResources = ResourceBundle.getBundle("ViewDropDown");
        myErrorResources = ResourceBundle.getBundle("Errors");
        myConsole = console;
        myManager = manager;
        myViews = new ArrayList<>();
        myScrollPane = new ScrollPane();
        myScrollPane.setPrefWidth(WIDTH);
        initializeViews();
    }

    public List<View> getViews() {
        return myViews;
    }

    //Initializes all views in the ViewDropDown.properties file
    private void initializeViews(){
        TreeSet<String> set =new TreeSet<>(myViewClassResources.keySet());
        VBox allDropDowns = new VBox();
        for(String s : set){
            View view = makeView(s);
            myViews.add(view);
            TitledPane pane = new TitledPane();
            pane.setText(s);
            pane.setContent(view.getPane());
            allDropDowns.getChildren().add(pane);
        }
        myScrollPane.setContent(allDropDowns);
        this.getChildren().add(myScrollPane);
    }


    private View makeView(String s) {
        System.out.println(s);
        try {
            Class c = Class.forName("FrontInternal.Views." + s);
            //TODO: potential problem cuz IModelManger technically isn't a class?
            var constructor = c.getConstructor(IModelManager.class);
            return (View) constructor.newInstance(myManager);
        } catch (NoSuchMethodException e) {
            try {
                System.out.println("\tAttempted to check for Console");
                Class c2 = Class.forName("FrontInternal.Views." + s);
                var constructor2 = c2.getConstructor(IModelManager.class, Console.class);
                System.out.println("\t made constructor");
                return (View) constructor2.newInstance(myManager, myConsole);
            } catch (Exception e1) {
                //e1.printStackTrace();
               return makeUnknownView();
            }
        } catch (Exception e){
            //e.printStackTrace();
            return makeUnknownView();
        }
    }

    /**
     * Updates all the views that are displayed in the Accordion of User Views
     */
    @Override
    public void update() {
        for(View v : myViews){
            v.update();
        }
    }

    /**
     * @return Pane containing the accordion off all
     *      user views
     */
    @Override
    public Pane getPane() {
        return this;
    }

    //View displayed in place of any of the views that failed to be added correctly
    private View makeUnknownView(){
        View temp = new View(myManager) {
            @Override
            public void update() {
            }
            @Override
            public HBox getPane() {
                HBox temp = new HBox();
                Text text = new Text();
                text.setText(myErrorResources.getString("UNKNOWN_CLASS"));
                temp.getChildren().add(text);
                return temp;
            }
        };
        return temp;
    }
}
