package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import FrontExternal.GUI;

public class Main extends Application {
    public static final String TITLE = "SLOGO";

    @Override
    public void start(Stage stage) throws Exception {
        //var model = new model();
        // should pass model into this maybe
        var display = new GUI();
        stage.setTitle(TITLE);
        stage.setScene(display.getScene());
        stage.show();

       // new Console();

    }

    public static void main(String[] args){
        launch(args);
    }


}

