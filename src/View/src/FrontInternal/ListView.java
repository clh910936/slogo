package FrontInternal;

import FrontInternal.ViewTemplate;
import javafx.collections.ListChangeListener;

public class ListView implements ListChangeListener {
    private ViewTemplate myViewPane;

    ListView(String viewTitle){
        myViewPane = new ViewTemplate(viewTitle);
    }


    @Override
    public void onChanged(Change change) {

    }
}
