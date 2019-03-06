package FrontInternal.Views;

import API.IModelManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

    /**
     * Creates a pane that can be updated based on the manager passed through
     * @param manager implementation of API.IModelManager
     */
    public VariableView(IModelManager manager){
        super(manager);
    }

    /**
     * Updates the pane by getting the necessary information from the
     * manager.
     */
    //TODO: fixme
    @Override
    public void update() {
        this.clearLines();
        Map<String, String> map = myManager.getVariables();
        for(String s : map.keySet()){
            Pane p = addEditableVariable(s, map.get(s));
            addToGridPane(p);
        }
    }

    private Pane addEditableVariable(String varName, String variable){
        Text name = new Text();
        name.setText(varName);
        TextField varValue = new TextField();
        varValue.setText(variable);
        varValue.textProperty().addListener(new ChangeListener<String>() {
            //TODO: this seems unlikely to work-need to test it
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                myManager.changeVariable(varName, t1);
            }
        });
        GridPane temp = new GridPane();
        temp.add(name, 0, 0);
        temp.add(varValue, 1, 0);
        return temp;
    }
}