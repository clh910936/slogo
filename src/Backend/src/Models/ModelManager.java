package Models;

import BackExternal.IModelManager;
import BackExternal.ITurtle;
import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import Commands.UserDefinedCommand;
import Parsing.CommandParser;

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

    public Map<String,Boolean> getHistory() {
        return myHistoryModel.getHistory();
    }

    public List<ITurtle> getTurtleList() {
        return myTurtleModel.getListOfTurtles();
    }

    public Map<String,UserDefinedCommand> getUserDefinedCommands() {
        return myUserDefinedCommandsModel.getUserCreatedCommands();
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
