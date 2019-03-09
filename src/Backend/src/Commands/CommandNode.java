package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.*;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        cp = new CommandParser(modelManager,myModelManager.getMySyntaxHandlerFactory());
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

    public int getNumParamsNeeded() {
        return MAX_PARAMS;
    }

    public void addChild(CommandNode node) {
        myChildren.add(node);
    }

    public List<CommandNode> getChildren() {
        return Collections.unmodifiableList(myChildren);
    }
    public void clearChildren() {
        myChildren.clear();
    }


    public List<Object> getMyParams() {
        return Collections.unmodifiableList(myParams);
    }

    public void addParam(Object p) {
        myParams.add(p);
    }

    public void clearMyParams() {
        myParams.clear();
    }

    public String getCommandName() {
        return this.getClass().getSimpleName();
    }

    public boolean isCommandReadyToExecute() {
        return myChildren.size()==MAX_PARAMS;
    }

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
        System.out.println("returns" + getCp().getReturnValues());
        return getCp().getReturnValues();
    }

}


