package Commands;

import Models.ModelManager;
import Models.ModelManager;
import Models.Turtle;

public class Backward extends OneParamCommand {

    public Backward(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.myTurtleModel.getCurrentTurtle();

        myTurtle.moveForward(-1 * Double.valueOf(String.valueOf(myParams.get(0))));
        return (double) myParams.get(0);
    }
}
