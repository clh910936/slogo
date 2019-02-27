package Commands;

import Models.TurtleModel;

public abstract class ZeroParamCommand extends CommandsGeneral {

    public ZeroParamCommand(String language) {
        super(language);

    }

    @Override
    public boolean isCommandReadyToExecute() {
        return true;
    }


}
