package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;


public class MakeUserInstruction extends ThreeParamCommand {

    public MakeUserInstruction(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
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
            UserDefinedCommand userCommand = new UserDefinedCommand(myLanguage, myTurtle, myVariablesModel,
                    myUserCreatedCommandsModel, name, commandString, variables);
            myUserCreatedCommandsModel.addUserCreatedCommand(userCommand);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
