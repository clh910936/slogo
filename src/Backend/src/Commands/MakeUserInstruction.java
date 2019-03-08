package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;


public class MakeUserInstruction extends ThreeParamCommand {
    private SyntaxHandlerFactory syntaxHandlerFactory;
    public MakeUserInstruction(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager) {
        super(syntaxHandlerFactory, modelManager);
        this.syntaxHandlerFactory = syntaxHandlerFactory;
    }

    //getMyParams().get(0) : name
    //getMyParams().get(1) : variables
    //input3 : commands

    @Override
    public Object executeCommand() throws ClassCastException {
        try {
            String name = String.valueOf(getMyParams().get(0));
            String[] variables = (String[]) getMyParams().get(1);
            String[] commands = (String[]) getMyParams().get(2);
            String commandString = String.join(" ", commands);
            UserDefinedCommand userCommand = new UserDefinedCommand(syntaxHandlerFactory, getMyModelManager(), name, commandString, variables);
            getMyUserDefinedCommandsModel().addUserCreatedCommand(userCommand);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
