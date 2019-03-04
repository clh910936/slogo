package Commands;

import Models.ModelManager;
import Models.ModelManager;

public class Backward extends OneParamCommand {

    public Backward(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        this.myTurtle.moveForward(-(double) input);
        return (double) input;
    }
}
