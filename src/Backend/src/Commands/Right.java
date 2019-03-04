package Commands;

import Models.ModelManager;

public class Right extends OneParamCommand {

    public Right(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        this.myTurtle.turnCounterClockwise(-1 * (double) myParams.get(0));
        return (double) myParams.get(0);
    }
}
