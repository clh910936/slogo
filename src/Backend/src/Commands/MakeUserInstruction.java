package Commands;

import BackExternal.IllegalCommandException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

import java.util.ArrayList;
import java.util.List;

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
                    myUserCreatedCommandsModel, name, variables, commandString);
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
