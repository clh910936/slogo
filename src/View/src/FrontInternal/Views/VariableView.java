package FrontInternal.Views;

import API.IModelManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * @author Carrie Hunner
 * This class extends the View superclass.
 * It is dependent on a backend implementation of API.IModelManager to get
 * the Variable. It then displays these on a pane
 * that is created in View.
 */
public class VariableView extends View {

    private VBox myVBox;
    private static final int VAR_FIELD_WIDTH=30;
     /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager implementation of API.IModelManager
     */
    public VariableView(IModelManager manager){
        super(manager);
        myVBox = new VBox();
        setContents(myVBox);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    //TODO: fixme
    @Override
    public void update() {
        //this.clearLines();
        myVBox.getChildren().clear();
        Map<String, String> map = myManager.getVariables();
        for(Map.Entry<String, String> entry : map.entrySet()){
            Pane p = addEditableVariable(entry.getKey(), entry.getValue());
            myVBox.getChildren().add(p);
            //addToGridPane(p);
        }
    }

    private Pane addEditableVariable(String varName, String variable){
        Text name = new Text();
        name.setText(varName);
        TextField varValue = new TextField();
        varValue.setPrefWidth(VAR_FIELD_WIDTH);
        varValue.setText(variable);
        varValue.textProperty().addListener(new ChangeListener<String>() {
            //TODO: this seems unlikely to work-need to test it
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println("Changing var: " + varName);
                System.out.println("\t from: " + s + "to: " + t1);
                myManager.changeVariable(varName, t1);
            }
        });
        HBox temp = new HBox();
        temp.getChildren().add(name);
        temp.getChildren().add(varValue);
        return temp;
    }
}