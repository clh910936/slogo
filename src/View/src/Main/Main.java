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
        var display = new GUI();


        stage.setTitle(TITLE);
        stage.setScene(display.getScene());
        stage.show();

        new Console(display);


    }

    public static void main(String[] args){
        launch(args);
    }


}

