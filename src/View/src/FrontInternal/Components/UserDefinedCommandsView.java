package FrontInternal.Components;

import BackExternal.ViewAPI;

import java.util.ResourceBundle;

public class UserDefinedCommandsView implements ViewAPI {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;

    //TODO: take in modelManager
    UserDefinedCommandsView(){
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("UserCommandsTitle"));
    }
    @Override
    public void update() {

    }
}
