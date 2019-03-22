package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

import java.util.Arrays;


public class MakeUserInstruction extends ThreeParamCommand {
    public MakeUserInstruction(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        try {
            String name = String.valueOf(getMyParams().get(0));
            String[] variables = (String[]) getMyParams().get(1);
            String[] commands = (String[]) getMyParams().get(2);
            String commandString = String.join(" ", commands);
            UserDefinedCommand userCommand = new UserDefinedCommand(getMyModelManager(), name, commandString, variables);
            getMyUserDefinedCommandsModel().addUserCreatedCommand(userCommand);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
