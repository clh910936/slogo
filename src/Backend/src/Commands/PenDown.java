package Commands;

import Models.ModelManager;

public class PenDown extends ZeroParamCommand {

    public PenDown(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenDown();
        return 1;
    }
}
