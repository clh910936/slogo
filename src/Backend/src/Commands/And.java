package Commands;

import Models.ModelManager;

public class And extends NonTurtleCommand {

    public And(String language, ModelManager modelManager) {
        super(language, modelManager);
        myMaxParams = 2;
    }
    @Override
    public double executeCommand() throws ClassCastException {
        return ((double) myParams.get(0) != 0 && (double) myParams.get(1) != 0)? 1 : 0;
    }

}
