package Commands;

import Models.ModelManager;
import Models.ModelManager;

public abstract class ZeroParamCommand extends CommandsGeneral {

    public ZeroParamCommand(String language, ModelManager modelManager) {
        super(language, modelManager);
    }
    @Override
    public boolean isCommandReadyToExecute() {
        return true;
    }


}
