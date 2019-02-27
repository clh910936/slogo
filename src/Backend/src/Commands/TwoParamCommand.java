package Commands;

import Models.TurtleModel;

public abstract class TwoParamCommand extends CommandsGeneral {
    private static final int MAX_PARAMS = 2;
    protected TurtleModel turtle;
    protected Object input1;
    protected Object input2;

    public TwoParamCommand() {
    }

    @Override
    public boolean isCommandReadyToExecute() {
        if(myParams.size() == MAX_PARAMS) {
            input1 = myParams.get(0);
            input2 = myParams.get(1);
            return true;
        }
        return false;
    }


}
