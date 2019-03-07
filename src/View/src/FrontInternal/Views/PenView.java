package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Components.Console;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

public class PenView extends View{
    private VBox myVBox;
    private final static int MAX_PEN_SIZE = 20;
    private final static int INIT_PEN_SIZE = 4;
    private final static int SLIDER_WIDTH = 150;
    private ResourceBundle myResources;
    private static final int BUTTON_WIDTH = 100;
    private Console myConsole;
    private ResourceBundle myErrorResources;
    private ResourceBundle myGeneralResources;

    public PenView(IModelManager manager, Console console){
        super(manager);
        myErrorResources = ResourceBundle.getBundle("Errors");
        myGeneralResources = ResourceBundle.getBundle("ViewBackgroundCommands");
        myVBox = new VBox();
        myConsole = console;
        setContents(myVBox);
        myResources = ResourceBundle.getBundle("ViewBackgroundCommands");

        initializeSliderLabel();
        formatSlider();
        makeAndFormatButton(myGeneralResources.getString("upButton"), myGeneralResources.getString("upMethod"));
        makeAndFormatButton(myGeneralResources.getString("downButton"), myGeneralResources.getString("downMethod"));

        myVBox.setAlignment(Pos.CENTER);
    }


    private void makeAndFormatButton(String name, String methodName){
        Button b = new Button();
        b.setText(name);
        b.setPrefWidth(BUTTON_WIDTH);
        b.setOnMouseClicked(e -> {
            try {
                System.out.println(methodName);
                Method method = this.getClass().getDeclaredMethod(methodName);
                method.invoke(this);
            } catch (Exception e1) {
                myConsole.showError(myErrorResources.getString("BUTTON_ERROR"));
            }});
        myVBox.getChildren().add(b);
    }

    private void setPenUp(){
        myManager.parseCommand(myGeneralResources.getString("penUpCommand"), DEFAULT_LANGUAGE);
    }

    private void setPenDown(){
        myManager.parseCommand(myGeneralResources.getString("penDownCommand"), DEFAULT_LANGUAGE);
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
        slider.setValue(INIT_PEN_SIZE);
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
