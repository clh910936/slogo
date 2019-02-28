package FrontInternal.Components;

import BackExternal.ViewAPI;

import java.util.ResourceBundle;

public class HistoryView implements ViewAPI {
    private ViewTemplate myViewTemplate;
    private ResourceBundle myBundle;
    HistoryView(){
        myBundle = ResourceBundle.getBundle("View");
        myViewTemplate = new ViewTemplate(myBundle.getString("HistoryTitle"));
    }

    @Override
    public void update() {

    }
}
