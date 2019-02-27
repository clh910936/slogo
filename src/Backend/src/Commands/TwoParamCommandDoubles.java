package Commands;

import BackExternal.ParamsExceedLimitException;
import Parsing.CommandsGeneral;
import Models.TurtleModel;

public abstract class TwoParamCommandDoubles extends TwoParamCommand implements CommandsGeneral {
    protected double input1;
    protected double input2;

    @Override
    public void addParameterToCommand(Object val) throws ParamsExceedLimitException {
        if (val instanceof TurtleModel) {
            turtle = (TurtleModel) val;
            return;
        }
        if (numOfInputs >= MAX_PARAMS) throw new ParamsExceedLimitException();

        if (numOfInputs == 0) {
            this.input1 = (double) val;
        }
        if (numOfInputs == 1) {
            this.input2 = (double) val;
        }
        numOfInputs++;
    }
}
