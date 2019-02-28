package Main;

import BackExternal.Creator;
import FrontInternal.Components.Console;
import javafx.application.Application;
import javafx.stage.Stage;
import FrontExternal.GUI;

public class Main extends Application {
    public static final String TITLE = "SLOGO";

    @Override
    public void start(Stage stage) throws Exception {
        //var model = new model();
        // should pass model into this maybe
        var creator = new Creator();
        var display = new GUI(creator.getModelManager());


        stage.setTitle(TITLE);
        stage.setScene(display.getScene());
        stage.show();

        var console = new Console(display, creator.getModelManager());


    }

    public static void main(String[] args){
        launch(args);
    }


}

