package BackExternal;

import API.FrontExternalAPI;
import API.IModelManager;
import Commands.UserDefinedCommand;
import Models.*;
import Parsing.CommandParser;
import Parsing.SyntaxHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelManager implements IModelManager {
    /*
    For each turtle, call myFrontEnd.move() or whatever
     */
    private FrontExternalAPI myFrontEnd;

    private VariablesModel myVariablesModel;
    private final HistoryModel myHistoryModel;
    private final TurtleModel myTurtleModel;
    private final UserDefinedCommandsModel myUserDefinedCommandsModel;
    private final CurrentStateFileModel myCurrentStateFileModel;
    private final CommandParser myCommandParser;

    public ModelManager(FrontExternalAPI frontend) {
        myFrontEnd = frontend;
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel(myFrontEnd);
        myUserDefinedCommandsModel = new UserDefinedCommandsModel();
        myCurrentStateFileModel = new CurrentStateFileModel(myVariablesModel,myUserDefinedCommandsModel,this);
        myCommandParser = new CommandParser(this);

    }

    public void parseCommand(String inputString, String language) throws IllegalCommandException, IllegalParametersException {
        try {
            myCommandParser.parseCommand(inputString, language);
            myHistoryModel.addHistoryEntry(inputString, true);
            myFrontEnd.updateViews();
        }
        catch (Exception e) {
            myHistoryModel.addHistoryEntry(inputString, false);
            myFrontEnd.updateViews();
            throw e;
        }


    }

    public void setVariablesModel(VariablesModel vm) {
        myVariablesModel = vm;
    }

    public Map<String, String> getVariables() {
        return myVariablesModel.getVariables();
    }

    public List<String> getHistory() {
        return myHistoryModel.getHistory();
    }

    public List<String> getSavedFilesList() {
        return myCurrentStateFileModel.getSavedFilesList();
    }

    public boolean getSuccessOfHistoryEntry(int index) {
        return myHistoryModel.getWasSuccessful(index);
    }

    public Map<String,String> getUserDefinedCommands() {
        Map<String,String> commandsList = new HashMap<>();
        for(Map.Entry<String,UserDefinedCommand> command : myUserDefinedCommandsModel.getUserCreatedCommands().entrySet()) {
            commandsList.put(command.getKey(),command.getValue().toString());
        }
        return commandsList;
    }

    protected VariablesModel getVariablesModel() {
        return myVariablesModel;
    }

    protected UserDefinedCommandsModel getUserDefinedCommandsModel() {
        return myUserDefinedCommandsModel;
    }

    protected TurtleModel getTurtleModel() {
        return myTurtleModel;
    }

    public void saveCurrentState(String fileName) {
        myCurrentStateFileModel.save(fileName);
        myFrontEnd.updateViews();
    }

    public void setStateFromFile(String fileName, String language) {
        myCurrentStateFileModel.setStateFromFile(fileName,language);
    }

    public void changeVariable(String variableName, String value) {
        myVariablesModel.addVariable(variableName, value);
    }


}
