package Variables;

import Exceptions.IllegalCommandException;

import java.util.HashMap;
import java.util.Map;

public class VariablesModel {

    private Map<String, String> myVariables;

    public VariablesModel() {
        myVariables = new HashMap<>();
    }

    public void addVariable(String variable, String value) {
        myVariables.put(variable, value);
    }
//TODO: DONT NEED THIS?
//    public void removeVariable(String variable) throws IllegalCommandException {
//        if(myVariables.containsKey(variable)) {
//            myVariables.remove(variable);
//        }
//        else {
//            throw new IllegalCommandException("Variable does not exist");
//        }
//    }

    public String getVariable(String variable) throws IllegalCommandException {
        if (myVariables.containsKey(variable)) {
            return myVariables.get(variable);
        } else {
            throw new IllegalCommandException("Variable does not exist");
        }
    }
}
