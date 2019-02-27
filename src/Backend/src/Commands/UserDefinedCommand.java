package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;

public class UserDefinedCommand extends CommandsGeneral {
    private String commandName;


    public UserDefinedCommand () {

    }

    public boolean isCommandReadyToExecute() {

    }
    public double executeCommand() throws ClassCastException {

    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
