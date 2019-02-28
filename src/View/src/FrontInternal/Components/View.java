package FrontInternal.Components;

import BackExternal.ViewAPI;

import java.util.ResourceBundle;

public abstract class View implements ViewAPI {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;

    View(){
    }

    public abstract void update();

}
