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



        return 0;
    }
}
