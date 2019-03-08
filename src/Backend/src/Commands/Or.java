package Commands;

import BackExternal.ModelManager;

public class Or extends TwoParamCommand {

    public Or(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public Object executeCommand() throws ClassCastException  {
        return ((double) getMyParams().get(0) != 0 || (double) getMyParams().get(1) != 0)? 1 : 0;
    }
}
