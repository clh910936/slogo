package FrontInternal.Views;

import BackExternal.ViewAPI;
import FrontInternal.Util.Operator;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class  AllUserViews extends ComboBox implements ViewAPI  {
    private ResourceBundle myResources;
    private Operator myOperator;
    private Alert myAlertBox;
    private List<ViewAPI> myViews;
    private TabPane myTabPane;

    public AllUserViews(Operator operator){
        myResources = ResourceBundle.getBundle("ViewDropDown");
        myOperator = operator;
        myAlertBox = new Alert(Alert.AlertType.ERROR);
        myViews = new ArrayList<>();
        myTabPane = new TabPane();
        initializeViews();
    }

    private void initializeViews() {
        HistoryView history = new HistoryView(myOperator);
        myViews.add(history);
        Tab temp1 = new Tab();
        temp1.setText("History");
        temp1.setContent(history.getPane());
        myTabPane.getTabs().add(temp1);

        UserDefinedCommandsView use = new UserDefinedCommandsView(myOperator);
        myViews.add(use);
        Tab temp2 = new Tab();
        temp2.setText("User Defined Commands");
        temp1.setContent(use.getPane());
        myTabPane.getTabs().add(temp2);

        VariableView var = new VariableView(myOperator);
        myViews.add(var);
        Tab temp3 = new Tab();
        temp3.setText("Variables");
        temp1.setContent(var.getPane());
        myTabPane.getTabs().add(temp3);
    }
//    private ViewAPI makeView(String s) {
//        Class c = null;
//        try {
//            c = Class.forName("FrontInternal.Views." + s);
//            var constructor = c.getConstructor(Operator.class);
//
//            return (ViewAPI) constructor.newInstance(myOperator);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//
//    }

    @Override
    public void update() {
        for(ViewAPI v : myViews){
            v.update();
        }
    }


//    private void displayAlert(){
//        this.myAlertBox = new Alert(Alert.AlertType.ERROR);
//        this.myAlertBox.setTitle(title);
//        this.myAlertBox.setHeaderText(header);
//        this.myAlertBox.setContentText(content);
//    }
}
