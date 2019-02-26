package Parsing.Commands;

import Exceptions.ParamsExceedLimitException;
import Parsing.CommandsGeneral;

public abstract class TwoParamCommandDoubles extends TwoParamCommand implements CommandsGeneral {
    protected double input1;
    protected double input2;

    @Override
    public void addParameterToCommand(Object val) throws ParamsExceedLimitException {
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