package Commands;

import Models.ModelManager;

public class XCoordinate extends ZeroParamCommand {
    public XCoordinate(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getUpdatedX().get(myTurtle.getUpdatedX().size() - 1);
    }
}
