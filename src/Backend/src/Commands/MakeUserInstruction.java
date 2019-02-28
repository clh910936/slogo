package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;


public class MakeUserInstruction extends ThreeParamCommand {

    public MakeUserInstruction(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
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
                    myUserDefinedCommandsModel, name, commandString, variables);
            myUserDefinedCommandsModel.addUserCreatedCommand(userCommand);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
