package FrontInternal.Views;

import API.IModelManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;

public class PenView extends View{
    private VBox myVBox;
    private final static int MAX_PEN_SIZE = 20;
    private final static int SLIDER_WIDTH = 150;
    private ResourceBundle myResources;

    public PenView(IModelManager manager){
        super(manager);
        System.out.println("\t made it to constructor");
        myVBox = new VBox();
        setContents(myVBox);
        myResources = ResourceBundle.getBundle("ViewBackgroundCommands");

        initializeSliderLabel();
        formatSlider();
    }



    private void initializeSliderLabel() {
        Label label = new Label(myResources.getString("SliderLabel"));
        myVBox.getChildren().add(label);
    }

    private void formatSlider() {
        Slider slider = new Slider();
        myVBox.getChildren().add(slider);
        slider.setMax(MAX_PEN_SIZE);
        slider.setMajorTickUnit(5);
        slider.setMinorTickCount(4);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setPrefWidth(SLIDER_WIDTH);
        slider.setMaxWidth(SLIDER_WIDTH);
        slider.setShowTickLabels(true);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String command = myResources.getString("setPenSize") + " " + t1.toString();
                myManager.parseCommand(command, DEFAULT_LANGUAGE);
            }
        });
    }

    @Override
    public void update() {
        //Doesn't need to update
    }
}
