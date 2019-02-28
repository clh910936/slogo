package FrontInternal.Components;

import BackExternal.IModelManager;

import java.util.List;
import java.util.ResourceBundle;

public class UserDefinedCommandsView extends View {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;

    public UserDefinedCommandsView(IModelManager manager){
        super(manager);
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("UserCommandsTitle"));
    }
    @Override
    public void update() {
        myViewTemplate.clearLines();
        List<String> list = myManager.getUserDefinedCommands();
        for(String s : list){
            myViewTemplate.addFinalLine(s);
        }
    }
}
