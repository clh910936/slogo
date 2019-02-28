package Commands;

import Models.ModelManager;
import Models.ModelManager;


public class MakeUserInstruction extends ThreeParamCommand {

    public MakeUserInstruction(String language, ModelManager modelManager) {
        super(language, modelManager);
    }

    //input1 : name
    //input2 : variables
    //input3 : commands

    @Override
    public double executeCommand() throws ClassCastException {
        try {
            String name = (String) input1;
            String[] variables = (String[]) input2;
            String[] commands = (String[]) input3;
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
