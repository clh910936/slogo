package Models;

import BackExternal.IModelManager;
import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import Commands.UserDefinedCommand;
import Models.HistoryModel;
import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Parsing.CommandParser;
import Models.VariablesModel;

import java.util.List;
import java.util.Map;

public class ModelManager implements IModelManager {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    private final VariablesModel myVariablesModel;
    private final HistoryModel myHistoryModel;
    private final Turtle myTurtle;
    private final CommandParser myCommandParser;
    private final UserCreatedCommandsModel myUserCreatedCommandsModel;

    public ModelManager() {
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtle = new Turtle(STARTX, STARTY,false, 0, true);
        myUserCreatedCommandsModel = new UserCreatedCommandsModel();
        myCommandParser = new CommandParser(myVariablesModel, myTurtle, myUserCreatedCommandsModel);
    }

    public void parseCommand(String inputString, String language) throws IllegalCommandException, IllegalParametersException {
        try {
            myCommandParser.parseCommand(inputString, language);
        }
        catch (IllegalCommandException e) {
            myHistoryModel.addHistoryEntry(inputString, false);
            throw e;
        }
        catch (IllegalParametersException e) {
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

    public Turtle getTurtle() {
        return myTurtle;
    }

    public Map<String,UserDefinedCommand> getUserCreatedCommands() {
        return myUserCreatedCommandsModel.getUserCreatedCommands();
    }

}
