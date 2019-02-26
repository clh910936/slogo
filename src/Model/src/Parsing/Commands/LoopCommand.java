package Parsing.Commands;

import Exceptions.ParamsExceedLimitException;
import Parsing.CommandsGeneral;
import Turtle.TurtleModel;

public abstract class LoopCommand implements CommandsGeneral {
    public static final int MAX_PARAMS = 2;
    private int numOfInputs;
    protected String[] input1;
    protected String[] input2;
    protected TurtleModel turtle;


    public LoopCommand() {
        numOfInputs = 0;
    }

    @Override
    public void addParameterToCommand(Object listOfParams) throws ParamsExceedLimitException{
        if (numOfInputs >= MAX_PARAMS) throw new ParamsExceedLimitException();
        if (numOfInputs == 0) {
            this.input1 = (String[]) listOfParams;
        }
        if (numOfInputs == 1) {
            this.input2 = (String[]) listOfParams;
        }
        numOfInputs++;
    }

    @Override
    public boolean isCommandReadyToExecute() {
        return (input1 != null && input2 != null);
    }


    @Override
    public String getCommandName() {
        return this.getClass().getSimpleName();
    }


}
