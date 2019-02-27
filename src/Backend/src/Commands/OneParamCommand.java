package Commands;

import Models.TurtleModel;

public abstract class OneParamCommand extends CommandsGeneral {
    private static final int MAX_PARAMS = 1;
    protected Object input;
    protected TurtleModel turtle;
    public OneParamCommand() {

    }


    @Override
    public boolean isCommandReadyToExecute() {
        if(myParams.size() == MAX_PARAMS) {
            input = myParams.get(0);
            return true;
        }
        return false;
    }


}
