package Main;

import FrontInternal.Components.Console;
import javafx.application.Application;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import FrontExternal.GUI;

import java.awt.*;

public class Main extends Application {
    public static final String TITLE = "SLOGO";
    private static final int HEIGHT = 600;
    private static final int WIDTH = 500;

    @Override
    public void start(Stage stage) throws Exception {
        //var model = new model();
        // should pass model into this maybe
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        var display = new GUI();

        TitledPane tPane = new TitledPane();

        stage.setTitle(TITLE);
        stage.setScene(display.getScene());
        stage.show();



    }

    public static void main(String[] args){
        launch(args);
    }


}

