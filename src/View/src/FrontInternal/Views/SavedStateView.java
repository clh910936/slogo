package FrontInternal.Views;

import API.IModelManager;
import javafx.scene.text.Text;

import java.util.List;

public class SavedStateView extends View {


    /**
     * Used to create a template for the Variable,User Defined Commands, and History view
     *
     * @param manager
     */
    public SavedStateView(IModelManager manager) {
        super(manager);
    }

    //TODO: check if this is working
    @Override
    public void update() {
        this.clearLines();
        List<String> list = myManager.getSavedFilesList();
        for(String s : list){
            Text text = createTextLine(s, DEFAULT_COLOR);
            text.setOnMouseClicked(e -> myManager.setStateFromFile(s, DEFAULT_LANGUAGE));
        }
    }
}
