package BackExternal;

import API.FrontExternalAPI;
import API.IModelManager;
import Commands.UserDefinedCommand;
import Models.*;
import Parsing.CommandParser;

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
    private TurtleModel myTurtleModel;
    private final UserDefinedCommandsModel myUserDefinedCommandsModel;
    private final CurrentStateFileModel myCurrentStateFileModel;
    private final CommandParser myCommandParser;
    private final PaletteModel myPaletteModel;
    private final BackgroundModel myBackgroundModel;

    public ModelManager(FrontExternalAPI frontend) {
        myFrontEnd = frontend;
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel(myFrontEnd);
        myUserDefinedCommandsModel = new UserDefinedCommandsModel();
        myCurrentStateFileModel = new CurrentStateFileModel(myVariablesModel,myUserDefinedCommandsModel,this);
        myCommandParser = new CommandParser(this);
        myPaletteModel = new PaletteModel();
        myBackgroundModel = new BackgroundModel(myFrontEnd);
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

    public VariablesModel getVariablesModel() {
        return myVariablesModel;
    }

    public UserDefinedCommandsModel getUserDefinedCommandsModel() {
        return myUserDefinedCommandsModel;
    }

    public TurtleModel getTurtleModel() {
        return myTurtleModel;
    }

    public PaletteModel getMyPaletteModel() {
        return myPaletteModel;
    }

    public BackgroundModel getMyShapeModel() {
        return myBackgroundModel;
    }

    public void saveCurrentState(String fileName) {
        myCurrentStateFileModel.saveStateIntoFile(fileName);
        myFrontEnd.updateViews();
    }

    public void setStateFromFile(String fileName, String language) {
        myCurrentStateFileModel.setStateFromFile(fileName,language);
        myFrontEnd.updateViews();
    }

    public void changeVariable(String variableName, String value) {
        myVariablesModel.addVariable(variableName, value);
    }

    @Override
    public void addPalette(int index, int r, int g, int b) {
        myPaletteModel.addPalette(index, r, g, b);
    }

    @Override
    public int getCurrentActiveTurtle() {
        return 0;
    }

    public void resetTurtle() {
        myTurtleModel = new TurtleModel(myFrontEnd);
    }

}
