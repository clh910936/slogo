package FrontInternal.Components;

import BackExternal.IModelManager;
import BackExternal.ViewAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class HistoryView extends View {


    public HistoryView(IModelManager manager){
        super(manager);
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("HistoryTitle"));
    }

    @Override
    public void update() {
        myViewTemplate.clearLines();
        Map<String, Boolean> history = myManager.getHistory();
        for(String s: history.keySet()){
            myViewTemplate.addFinalLine(s, history.get(s));
        }
    }
}
