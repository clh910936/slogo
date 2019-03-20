package Models;

import BackExternal.IllegalCommandException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author christinachen
 * Keeps track of all variables
 */
public class VariablesModel {

    private Map<String, String> myVariables;

    public VariablesModel(VariablesModel vm) {
        this.myVariables = vm.myVariables;
    }

    public VariablesModel() {
        myVariables = new HashMap<>();
    }

    /**
     * Adds a variable to the model
     * @param variable
     * @param value
     */
    public void addVariable(String variable, String value) {
        myVariables.put(variable, value);
    }

    /**
     * @param variable
     * @return the value of a variable
     * @throws IllegalCommandException
     */
    public String getVariable(String variable) throws IllegalCommandException {
        if (myVariables.containsKey(variable)) {
            return myVariables.get(variable);
        } else {
            throw new IllegalCommandException("Variable does not exist");
        }
    }

    /**
     *
     * @return map of all of the variables that exist
     */
    public Map<String, String> getVariables() {
        return Collections.unmodifiableMap(myVariables);
    }


}
