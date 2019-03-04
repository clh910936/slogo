package BackExternal;

import API.FrontExternalAPI;
import API.IModelManager;
import BackExternal.ITurtle;
import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import Commands.UserDefinedCommand;
import Models.*;
import Parsing.CommandParser;

import java.util.ArrayList;
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
    private final CommandParser myCommandParser;
    private final UserDefinedCommandsModel myUserDefinedCommandsModel;
    private final CurrentStateFileModel myCurrentStateFileModel;


    public ModelManager(FrontExternalAPI frontend) {
        myFrontEnd = frontend;
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel();
        myUserDefinedCommandsModel = new UserDefinedCommandsModel();
        myCommandParser = new CommandParser(this);
        myCurrentStateFileModel = new CurrentStateFileModel(myVariablesModel,myUserDefinedCommandsModel,this);
    }

    public void parseCommand(String inputString, String language) throws IllegalCommandException, IllegalParametersException {
        try {
            myCommandParser.parseCommand(inputString, language);
        }
        catch (Exception e) {
            myHistoryModel.addHistoryEntry(inputString, false);
            throw e;
        }
        myHistoryModel.addHistoryEntry(inputString, true);
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

    public boolean getWasSuccessfulHistory(int i) {
        return myHistoryModel.wasSuccessful.test(i);
    }


    public Map<Integer,ITurtle> getTurtleList() {
        return myTurtleModel.getAllTurtles();
    }

    public Map<String,String> getUserDefinedCommands() {
        Map<String,String> commandsList = new HashMap<>();
        for(Map.Entry<String,UserDefinedCommand> command : myUserDefinedCommandsModel.getUserCreatedCommands().entrySet()) {
            commandsList.put(command.getKey(),command.getValue().toString());
        }
        return commandsList;
    }

    public VariablesModel getVariablesModel() {
        return myVariablesModel;
    }

    public UserDefinedCommandsModel getUserDefinedCommandsModel() {
        return myUserDefinedCommandsModel;
    }

//    public HistoryModel getHistoryModel() {
//        return myHistoryModel;
//    }

    public TurtleModel getTurtleModel() {
        return myTurtleModel;
    }

    public void saveCurrentState(String fileName) {
        myCurrentStateFileModel.save(fileName);
    }

    public void setStateFromFile(String fileName, String language) {
        myCurrentStateFileModel.setStateFromFile(fileName,language);
    }

    public void changeVariable(String variableName, String value) {
        myVariablesModel.addVariable(variableName, value);
    }

    public List<String> getSavedFilesList() {
        return myCurrentStateFileModel.getSavedFilesList();
    }

}
