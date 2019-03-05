package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Components.Console;
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
    private List<ViewAPI> myViews;

    public AllUserViews(IModelManager operator, Console console){
        myViewClassResources = ResourceBundle.getBundle("ViewDropDown");
        myErrorResources = ResourceBundle.getBundle("Errors");
        myConsole = console;
        myManager = operator;
        myViews = new ArrayList<>();
        initializeViews();
    }

    //Initializes all views in the ViewDropDown.properties file
    private void initializeViews(){
        TreeSet<String> set =new TreeSet<>(myViewClassResources.keySet());
        for(String s : set){
            ViewAPI view = makeView(s);
            myViews.add(view);
            TitledPane pane = new TitledPane();
            pane.setText(s);
            pane.setContent(view.getPane());
            this.getChildren().add(pane);
        }
    }


    private ViewAPI makeView(String s) {
        System.out.println(s);
        try {
            Class c = Class.forName("FrontInternal.Views." + s);
            //TODO: potential problem cuz IModelManger technically isn't a class?
            var constructor = c.getConstructor(IModelManager.class);
            return (ViewAPI) constructor.newInstance(myManager);
        } catch (NoSuchMethodException e) {
            try {
                Class c = Class.forName("FrontInternal.Views." + s);
                var constructor = c.getConstructor(IModelManager.class, Console.class);
                return (ViewAPI) constructor.newInstance(myManager, myConsole);
            } catch (Exception e1) {
                return makeUnkownView();
            }
        } catch (Exception e){
            return makeUnkownView();
        }
    }

    /**
     * Updates all the views that are displayed in the Accordion of User Views
     */
    @Override
    public void update() {
        for(ViewAPI v : myViews){
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
    private ViewAPI makeUnkownView(){
        ViewAPI temp = new ViewAPI() {
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
