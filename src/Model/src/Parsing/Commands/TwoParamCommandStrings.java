package Parsing.Commands;

import Exceptions.ParamsExceedLimitException;
import Parsing.CommandsGeneral;
import Turtle.TurtleModel;

public abstract class TwoParamCommandStrings extends TwoParamCommand implements CommandsGeneral {
    protected String input1;
    protected Double input2;

    @Override
    public void addParameterToCommand(Object val) throws ParamsExceedLimitException {
        if (val instanceof TurtleModel) {
            turtle = (TurtleModel) val;
            return;
        }
        if (numOfInputs >= MAX_PARAMS) throw new ParamsExceedLimitException();

        if (numOfInputs == 0) {
            this.input1 = (String) val;
        }
        if (numOfInputs == 1) {
            this.input2 = (Double) val;
        }
        numOfInputs++;
    }
}
