package Main;

import BackExternal.Creator;
import FrontInternal.Components.Console;
import FrontInternal.Util.Operator;
import javafx.application.Application;
import javafx.stage.Stage;
import FrontExternal.GUI;

public class Main extends Application {
    public static final String TITLE = "SLOGO";

    @Override
    public void start(Stage stage) throws Exception {
        //var model = new model();
        // should pass model into this maybe
        Operator operator = new Operator();
        var display = new GUI(operator);


        stage.setTitle(TITLE);
        stage.setScene(display.getScene());
        stage.show();

        new Console(operator);


    }

    public static void main(String[] args){
        launch(args);
    }


}

