package FrontInternal.Views;

import API.IModelManager;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.List;

public class SavedStateView extends View {
    private VBox myVBox;


    /**
     * Used to create a template for the Variable,User Defined Commands, and History view
     *
     * @param manager
     */
    public SavedStateView(IModelManager manager) {
        super(manager);
        myVBox = new VBox();
        setContents(myVBox);
        update();
    }

    //TODO: check if this is working
    @Override
    public void update() {
        myVBox.getChildren().clear();
        List<String> list = myManager.getSavedFilesList();
        for(String s : list){
            Text text = createTextLine(s, DEFAULT_COLOR);
            text.setOnMouseClicked(e -> handleMouseClicked(s));
        }
    }

    private void handleMouseClicked(String name){
        myManager.setStateFromFile(name, DEFAULT_LANGUAGE);
    }
    private Text createTextLine(String s, Paint color){
        Text text = new Text();
        text.setText(s);
        text.setFill(color);
        myVBox.getChildren().add(text);
        return text;
    }
}
