package Commands;

import Models.ModelManager;

public class PenUp extends ZeroParamCommand {

    public PenUp(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        myTurtle.setPenUp();
        return 0;
    }
}
