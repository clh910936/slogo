package Commands;

import Models.ModelManager;

public class ShowTurtle extends ZeroParamCommand {

    public ShowTurtle(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        myTurtle.setShowTurtle();
        return 1;
    }
}
