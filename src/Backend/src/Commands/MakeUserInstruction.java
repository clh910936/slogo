package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;


public class MakeUserInstruction extends ThreeParamCommand {

    public MakeUserInstruction(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
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
