package BackExternal;

import API.FrontExternalAPI;
import API.IModelManager;
import Commands.UserDefinedCommand;
import Models.*;
import Parsing.CommandParser;
import Parsing.SyntaxHandlerFactory;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
    private TurtleModel myTurtleModel;
    private final UserDefinedCommandsModel myUserDefinedCommandsModel;
    private final CurrentStateFileModel myCurrentStateFileModel;
    private final CommandParser myCommandParser;
    private final PaletteModel myPaletteModel;
    private final BackgroundModel myBackgroundModel;
    private final SyntaxHandlerFactory mySyntaxHandlerFactory;

    public ModelManager(FrontExternalAPI frontend) {
        myFrontEnd = frontend;
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel(myFrontEnd);
        myUserDefinedCommandsModel = new UserDefinedCommandsModel();
        myCurrentStateFileModel = new CurrentStateFileModel(myVariablesModel,myUserDefinedCommandsModel,this);
        mySyntaxHandlerFactory = new SyntaxHandlerFactory(this);
        myCommandParser = new CommandParser(this,mySyntaxHandlerFactory);
        myPaletteModel = new PaletteModel();
        myBackgroundModel = new BackgroundModel(myFrontEnd);
    }

    public void parseCommand(String inputString, String language) throws IllegalCommandException, IllegalParametersException {
        try {
            mySyntaxHandlerFactory.changeLanguage(language);
            myCommandParser.parseCommand(inputString);
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
    public SyntaxHandlerFactory getMySyntaxHandlerFactory() {
        return mySyntaxHandlerFactory;
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
        return (int) myTurtleModel.getCurrentTurtleIndex();
    }

    @Override
    public void makeCurrentActiveTurtle(int index) {
        myTurtleModel.setCurrentTurtle(index);
    }

    @Override
    public void setTurtleImage(int turtleid, int shapeIndex) {
        Turtle t = myTurtleModel.getTurtle(turtleid);
        t.setShapeIndex(shapeIndex);
    }

    @Override
    public int getTurtleImage(int turtleid) {
        Turtle t = myTurtleModel.getTurtle(turtleid);
        return t.getShapeIndex();
    }

    @Override
    public List<Integer> getTurtles() {
        return new ArrayList<>(myTurtleModel.getAllTurtles().keySet());
    }

    @Override
    public void setXPos(int turtleId, SimpleDoubleProperty xpos) {
        Turtle t = myTurtleModel.getTurtle(turtleId);
        t.setxPos(xpos);
    }

    @Override
    public void setYPos(int turtleId, SimpleDoubleProperty ypos) {
        Turtle t = myTurtleModel.getTurtle(turtleId);
        t.setyPos(ypos);
    }

    @Override
    public void setPenUp(int turtleId, SimpleBooleanProperty penUp) {
        Turtle t = myTurtleModel.getTurtle(turtleId);
        t.setPenUp(penUp);
    }

    @Override
    public void setPenThickness(int turtleId, SimpleDoubleProperty thickness) {
        Turtle t = myTurtleModel.getTurtle(turtleId);
        t.setPenThickness(thickness);
    }

    @Override
    public void setR(int turtleId, SimpleIntegerProperty r) {
        Turtle t = myTurtleModel.getTurtle(turtleId);
        t.setR(r);
    }

    @Override
    public void setG(int turtleId, SimpleIntegerProperty g) {
        Turtle t = myTurtleModel.getTurtle(turtleId);
        t.setG(g);
    }

    @Override
    public void setB(int turtleId, SimpleIntegerProperty b) {
        Turtle t = myTurtleModel.getTurtle(turtleId);
        t.setB(b);
    }

    @Override
    public SimpleDoubleProperty getXPos(int turtleId) {
        return myTurtleModel.getTurtle(turtleId).getxPos();
    }

    @Override
    public SimpleDoubleProperty getYPos(int turtleId) {
        return myTurtleModel.getTurtle(turtleId).getyPos();
    }

    @Override
    public SimpleBooleanProperty getPenUp(int turtleId) {
        return myTurtleModel.getTurtle(turtleId).getPenUp();
    }

    @Override
    public SimpleDoubleProperty getPenThickness(int turtleId) {
        return myTurtleModel.getTurtle(turtleId).getPenThickness();
    }

    @Override
    public SimpleIntegerProperty getR(int turtleId) {
        return myTurtleModel.getTurtle(turtleId).getR();
    }

    @Override
    public SimpleIntegerProperty getG(int turtleId) {
        return myTurtleModel.getTurtle(turtleId).getG();
    }

    @Override
    public SimpleIntegerProperty GetB(int turtleId) {
        return myTurtleModel.getTurtle(turtleId).getB();
    }


    public void resetTurtle() {
        myTurtleModel = new TurtleModel(myFrontEnd);
    }

}
