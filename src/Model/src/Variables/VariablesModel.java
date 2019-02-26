package Variables;

import Exceptions.IllegalCommandException;
import Parsing.CommandParser;
import Parsing.Regex;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class VariablesModel {

    private Map<String, String> myVariables;

    public VariablesModel() {
        myVariables = new HashMap<>();
    }

    public void addVariable(String variable, String value) {
        myVariables.put(variable, value);
    }

    public String getVariable(String variable) throws IllegalCommandException {
        if (myVariables.containsKey(variable)) {
            return myVariables.get(variable);
        } else {
            throw new IllegalCommandException("Variable does not exist");
        }
    }

    public Map<String, String> getVariables() {
        return Collections.unmodifiableMap(myVariables);
    }

//    public String replaceAllVariables(String commandInput, List<Map.Entry<String, Pattern>> mySymbols) {
//        String[] inputArray = commandInput.split(CommandParser.WHITESPACE);
//        for(int i = 0; i<inputArray.length;i++) {
//            String rawInput = inputArray[i];
//            if(Regex.getRegexSymbol(rawInput,mySymbols).equals(CommandParser.VARIABLE_SYMBOL)) {
//                String variableInput = rawInput.substring(1);
//                if(i==0) throw new IllegalCommandException("No command detected for variable " + variableInput);
//                if(!inputArray[i=1].equals(CommandParser.MAKE_VARIABLE)) {
//                    //TODO: make a illegal variables exception?
//                    if(!myVariables.containsKey(variableInput)) throw new IllegalCommandException("Variable " + variableInput + " does not exist");
//                    inputArray[i] = myVariables.get(variableInput);
//                }
//
//            }
//
//        }
//        return String.join(" ", inputArray);
//    }
}
