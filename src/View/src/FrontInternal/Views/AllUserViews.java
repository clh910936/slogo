package FrontInternal.Views;

import BackExternal.IModelManager;
import BackExternal.ViewAPI;
import javafx.scene.control.ComboBox;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class  AllUserViews //implements ViewAPI extends ComboBox {
{private ResourceBundle myResources;
    private IModelManager myManager;

    AllUserViews(IModelManager manager){
        myResources = ResourceBundle.getBundle("ViewDropDown");
        myManager = manager;
    }
//    private ViewAPI makeView(String s) {
////        Class c = Class.forName("FrontInternal.Views." + s);
////
////        var constructor = c.getConstructor(String.class, int.class);
////
////        return (Bet) constructor.newInstance(description, odds);
//    }


}
