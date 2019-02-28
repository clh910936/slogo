package Models;

import BackExternal.IModelManager;
import BackExternal.ITurtle;
import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import Commands.UserDefinedCommand;
import Parsing.CommandParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelManager implements IModelManager {

    public static final double STARTX = 2000;
    public static final double STARTY = 2000;

    private final VariablesModel myVariablesModel;
    private final HistoryModel myHistoryModel;
    private final TurtleModel myTurtleModel;
    private final CommandParser myCommandParser;
    private final UserDefinedCommandsModel myUserDefinedCommandsModel;


    public ModelManager() {
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel(STARTX, STARTY,false, 90, true, 0);
        myUserDefinedCommandsModel = new UserDefinedCommandsModel();
        myCommandParser = new CommandParser(this);
        System.out.println("got here");
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

    public Map<String, String> getVariables() {
        return myVariablesModel.getVariables();
    }

    public List<String> getHistory() {
        return myHistoryModel.getHistory();
    }
    public boolean getWasSuccessfulHistory(int i) {
        return myHistoryModel.wasSuccessful.test(i);
    }


    public List<ITurtle> getTurtleList() {
        return myTurtleModel.getListOfTurtles();
    }

    public List<String> getUserDefinedCommands() {
        List<String> commandsList = new ArrayList<>();
        for(Map.Entry<String,UserDefinedCommand> command : myUserDefinedCommandsModel.getUserCreatedCommands().entrySet()) {
            commandsList.add(command.getValue().toString());
        }
        return commandsList;
    }

    public VariablesModel getVariablesModel() {
        return myVariablesModel;
    }

    public UserDefinedCommandsModel getUserDefinedCommandsModel() {
        return myUserDefinedCommandsModel;
    }

    public HistoryModel getHistoryModel() {
        return myHistoryModel;
    }

    public TurtleModel getTurtleModel() {
        return myTurtleModel;
    }

}
