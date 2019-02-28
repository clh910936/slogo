package FrontInternal.Components;

import BackExternal.IModelManager;

import java.util.Map;
import java.util.ResourceBundle;


public class VariableView extends View {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;


    public VariableView(IModelManager manager){
        super(manager);
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("VariableTitle"));
    }

    @Override
    public void update() {
        myViewTemplate.clearLines();
        Map<String, String> map = myManager.getVariables();
        for(String s : map.keySet()){
            String line = s + "\t" + map.get(s);
            myViewTemplate.addFinalLine(line);
        }
    }
}
