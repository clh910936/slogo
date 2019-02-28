package FrontInternal.Components;

import BackExternal.IModelManager;
import BackExternal.ViewAPI;

import java.util.ResourceBundle;

public class UserDefinedCommandsView extends View {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;

    //TODO: take in modelManager
    UserDefinedCommandsView(IModelManager manager){
        super(manager);
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("UserCommandsTitle"));
    }
    @Override
    public void update() {

    }
}
