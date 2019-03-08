package Commands;

import BackExternal.ModelManager;


public class MakeUserInstruction extends ThreeParamCommand {

    public MakeUserInstruction(String language, ModelManager modelManager) {
        super(language, modelManager);
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
            UserDefinedCommand userCommand = new UserDefinedCommand(myLanguage, myModelManager, name, commandString, variables);
            myUserDefinedCommandsModel.addUserCreatedCommand(userCommand);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
