package Commands;

import Models.VariablesModel;
import Parsing.CommandParser;

import java.util.Arrays;

public class MakeUserInstruction extends ThreeParamCommand {

    public MakeUserInstruction(String language) {
        super(language);
    }

    //input1 : name
    //input2 : variables
    //input3 : commands

    @Override
    public double executeCommand() throws ClassCastException {

        // FIXME: move to UserDef
        String[] variables = (String[]) input2;
        String[] commands = (String[]) input3;
        double out = 0.0;
        for (int i = 0; i < variables.length; i++) {
            String[] newCommandArray = Arrays.copyOf(variables, commands.length);
            for (int j = 0; j < variables.length; j++) {
                if (variables[j].equals(commands[i])) {
                    newCommandArray[j] = variables[i];
                }
            }
            //if(newCommandArray.length==0) return 0;
            String newCommand = String.join(" ", newCommandArray);
            System.out.println(newCommand);
//            CommandParser cp = new CommandParser(new VariablesModel(), myTurtle);
//            out = cp.parseCommand(newCommand, myLanguage);
        }

        return 0;
    }
}
