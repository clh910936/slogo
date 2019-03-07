package Commands;

import BackExternal.ModelManager;

public class ID extends ZeroParamCommand {

    public ID(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return this.myTurtleModel.getCurrentTurtleIndex();
    }
}
