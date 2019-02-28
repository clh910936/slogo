package FrontInternal.Components;

import BackExternal.IModelManager;
import BackExternal.ViewAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class VariableView implements ViewAPI {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;
    private IModelManager myManager;

    public VariableView(IModelManager manager){
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("VariableTitle"));
    }

    @Override
    public void update() {
        Map<String, Boolean> map = myManager.getHistory();
        for(String s : map.keySet()){

        }
    }
}
