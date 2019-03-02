package FrontInternal.Views;

import FrontInternal.Util.Operator;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Extends Accordion and implements ViewAPI.
 * It creates an Accordion of all the Views listed in the
 * ViewDropDown.properties file.
 * In this file, the key is the name of the class
 * and the value is the text displayed to the User
 */
public class  AllUserViews extends VBox implements ViewAPI  {
    private ResourceBundle myResources;
    private Operator myOperator;
    private Alert myAlertBox;
    private List<ViewAPI> myViews;

    public AllUserViews(Operator operator){
        myResources = ResourceBundle.getBundle("ViewDropDown");
        myOperator = operator;
        myAlertBox = new Alert(Alert.AlertType.ERROR);
        myViews = new ArrayList<>();
        initializeViews();
    }

    //Initializes all views in the ViewDropDown.properties file
    private void initializeViews(){
        TreeSet<String> set =new TreeSet<>(myResources.keySet());
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
        try {
            Class c = Class.forName("FrontInternal.Views." + s);
            var constructor = c.getConstructor(Operator.class);

            return (ViewAPI) constructor.newInstance(myOperator);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //TODO: This needs to go
        HistoryView temp = new HistoryView(myOperator);
        return temp;

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
            public Pane getPane() {
                Pane temp = new Pane();
                Text text = new Text();
                //text.setText();
                return temp;
            }
        };
        return temp;
    }
}
