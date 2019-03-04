package Commands;

import Models.ModelManager;
import Models.ModelManager;


public class MakeUserInstruction extends ThreeParamCommand {

    public MakeUserInstruction(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    //myParams.get(0) : name
    //myParams.get(1) : variables
    //input3 : commands

    @Override
    public Object executeCommand() throws ClassCastException {
        try {
            String name = String.valueOf(myParams.get(0));
            String[] variables = (String[]) myParams.get(1);
            String[] commands = (String[]) myParams.get(2);
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
