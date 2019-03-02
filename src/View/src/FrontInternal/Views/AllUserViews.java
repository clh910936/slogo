package FrontInternal.Views;

import FrontInternal.Util.Operator;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Extends Accordion and implements ViewAPI.
 * It creates an Accordion of all the Views listed in the
 * ViewDropDown.properties file.
 * In this file, the key is the name of the class
 * and the value is the text displayed to the User
 */
public class  AllUserViews extends Accordion implements ViewAPI  {
    private ResourceBundle myResources;
    private Operator myOperator;
    private Alert myAlertBox;
    private List<ViewAPI> myViews;
    private Pane myPane;

    public AllUserViews(Operator operator){
        myResources = ResourceBundle.getBundle("ViewDropDown");
        myOperator = operator;
        myAlertBox = new Alert(Alert.AlertType.ERROR);
        myViews = new ArrayList<>();
        myPane = new Pane();
        myPane.getChildren().add(this);
        initializeViews();
    }

    private void initializeViews(){
        TreeSet<String> set =new TreeSet<>(myResources.keySet());
        for(String s : set){
            ViewAPI view = makeView(s);
            myViews.add(view);
            TitledPane pane = new TitledPane();
            pane.setText(s);
            pane.setContent(view.getPane());
            this.getPanes().add(pane);
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

    @Override
    public void update() {
        for(ViewAPI v : myViews){
            v.update();
        }
    }

    @Override
    public Pane getPane() {
        return myPane;
    }


//    private void displayAlert(){
//        this.myAlertBox = new Alert(Alert.AlertType.ERROR);
//        this.myAlertBox.setTitle(title);
//        this.myAlertBox.setHeaderText(header);
//        this.myAlertBox.setContentText(content);
//    }
}
