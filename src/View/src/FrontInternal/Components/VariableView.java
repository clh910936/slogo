package FrontInternal.Components;

import BackExternal.ViewAPI;

import java.util.HashMap;
import java.util.ResourceBundle;

public class VariableView implements ViewAPI {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;
    VariableView(){
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("VariableTitle"));
    }

    @Override
    public void update() {

    }
}
