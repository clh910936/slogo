package Commands;

import BackExternal.ModelManager;

public class And extends TwoParamCommand {

    public And(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException {
        return (Double.valueOf(String.valueOf(getMyParams().get(0))) != 0 && Double.valueOf(String.valueOf(getMyParams().get(1))) != 0)? 1 : 0;
    }

}
