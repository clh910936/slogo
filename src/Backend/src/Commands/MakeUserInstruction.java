package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;

import java.util.Arrays;


public class MakeUserInstruction extends ThreeParamCommand {
    private SyntaxHandlerFactory syntaxHandlerFactory;
    public MakeUserInstruction(ModelManager modelManager
) {
        super(modelManager
);
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
            System.out.println("*^*^*^*^*^*^*^");
            System.out.println(name);
            System.out.println(Arrays.toString(variables));
            System.out.println(Arrays.toString(commands));
            System.out.println("*********");

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
