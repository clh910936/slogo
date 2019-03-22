package Commands;

import BackExternal.ModelManager;
import Models.*;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author michaelzhang
 * Top-most abstraction for commands
 */
public abstract class CommandNode {

    private TurtleModel myTurtleModel;
    private VariablesModel myVariablesModel;
    private ModelManager myModelManager;
    private List<Object> myParams;
    private List<CommandNode> myChildren;
    private int MAX_PARAMS;
    private PaletteModel myPaletteModel;
    private BackgroundModel myBackgroundModel;
    private CommandParser cp;
    private UserDefinedCommandsModel myUserDefinedCommandsModel;

    public CommandNode(ModelManager modelManager) {
        myModelManager = modelManager;
        myVariablesModel = modelManager.getVariablesModel();
        myTurtleModel = modelManager.getTurtleModel();
        myPaletteModel = modelManager.getMyPaletteModel();
        myBackgroundModel= modelManager.getMyShapeModel();
        myParams = new ArrayList<>();
        myChildren = new ArrayList<>();
        myUserDefinedCommandsModel = modelManager.getUserDefinedCommandsModel();
        cp = new CommandParser(modelManager);
    }

    protected CommandParser getCp() {
        return cp;
    }

    protected UserDefinedCommandsModel getMyUserDefinedCommandsModel() {
        return myUserDefinedCommandsModel;
    }

    protected TurtleModel getMyTurtleModel() {
        return myTurtleModel;
    }

    protected BackgroundModel getMyBackgroundModel() {
        return myBackgroundModel;
    }

    protected VariablesModel getMyVariablesModel() {
        return myVariablesModel;
    }

    protected ModelManager getMyModelManager() {
        return myModelManager;
    }

    protected PaletteModel getMyPaletteModel() {
        return myPaletteModel;
    }

    /**
     * Gets number of parameters are required for this command
     * @return max number of parameters
     */
    public int getNumParamsNeeded() {
        return MAX_PARAMS;
    }

    /**
     * Adds child to the command
     * @param node
     */
    public void addChild(CommandNode node) {
        myChildren.add(node);
    }

    /**
     * Gets list of children
     * @return list of children
     */
    public List<CommandNode> getChildren() {
        return Collections.unmodifiableList(myChildren);
    }

    /**
     * Clears list of children
     */
    public void clearChildren() {
        myChildren.clear();
    }

    /**
     * Gets the parameters given to this command
     * @return list of parameters
     */
    public List<Object> getMyParams() {
        return Collections.unmodifiableList(myParams);
    }

    /**
     * Adds a parameter to command
     * @param p
     */
    public void addParam(Object p) {
        myParams.add(p);
    }

    /**
     * Clears list of parameters
     */
    public void clearMyParams() {
        myParams.clear();
    }

    /**
     * Gets command name
     * @return command name
     */
    public String getCommandName() {
        return this.getClass().getSimpleName();
    }

    /**
     * If command has all its required children, then returns true
     * @return
     */
    public boolean isCommandReadyToExecute() {
        return myChildren.size()==MAX_PARAMS;
    }

    /**
     * Executes the action for a given command
     * @return always returns a number
     */
    public abstract Object executeCommand();

    protected void setMaxParams(int max) {
        MAX_PARAMS = max;
    }

    protected List<Double> getVariableValues(String[] variablesInfo) {
        List<String> varParams = new ArrayList<>();
        for(int i = 1;i<variablesInfo.length;i++) {
            varParams.add(variablesInfo[i]);
        }
        getCp().parseCommand(String.join(" ",varParams));
        return getCp().getReturnValues();
    }

}


