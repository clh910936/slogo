package FrontInternal.Views;

import FrontInternal.Util.Operator;
import javafx.scene.control.Alert;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.lang.reflect.InvocationTargetException;
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
    private Operator myOperator;
    private Alert myAlertBox;
    private List<ViewAPI> myViews;

    public AllUserViews(Operator operator){
        myViewClassResources = ResourceBundle.getBundle("ViewDropDown");
        myErrorResources = ResourceBundle.getBundle("Errors");
        myOperator = operator;
        myAlertBox = new Alert(Alert.AlertType.ERROR);
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
            myOperator.addViewToUpdate(view);
        }
    }


    private ViewAPI makeView(String s) {
        System.out.println(s);
        try {
            Class c = Class.forName("FrontInternal.Views." + s);
            System.out.println("\t found class");
            var constructor = c.getConstructor(Operator.class);
            System.out.println("\t constructor");

            return (ViewAPI) constructor.newInstance(myOperator);
        } catch (Exception e) {
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


//    private void displayAlert(){
//        this.myAlertBox = new Alert(Alert.AlertType.ERROR);
//        this.myAlertBox.setTitle(title);
//        this.myAlertBox.setHeaderText(header);
//        this.myAlertBox.setContentText(content);
//    }

    private ViewAPI makeUnkownView(){
        ViewAPI temp = new ViewAPI() {
            @Override
            public void update() {
            }
            @Override
            public HBox getPane() {
                HBox temp = new HBox();
                Text text = new Text();
                text.setText(myErrorResources.getString("UNKOWN_CLASS"));
                temp.getChildren().add(text);
                return temp;
            }
        };
        return temp;
    }
}
