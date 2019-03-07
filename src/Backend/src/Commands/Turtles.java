package Commands;

import BackExternal.ModelManager;

public class Turtles extends ZeroParamCommand {

    public Turtles(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        return myTurtleModel.getAllTurtles().size();
    }
}
