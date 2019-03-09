package API;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IModelManager {

    Map<String,String> getUserDefinedCommands();
    Map<String,String> getVariables();
    List<String> getHistory();
    void parseCommand(String input, String language);
    boolean getSuccessOfHistoryEntry(int index);
    void saveCurrentState(String fileName);
    void setStateFromFile(String fileName, String language);
    List<String> getSavedFilesList();
    void changeVariable(String variableName, String value);
    void addPalette(int index, int r, int g, int b);
    int getCurrentActiveTurtle();


    // also this method: void makeCurrentActiveTurtle(int index);
    void makeCurrentActiveTurtle(int index);


    //TODO: add an image index to each turtle, 0-5, initially starts at 0
    // this way we don't have to have users upload their own images, ill just look
    // at the index to determine which image to use


    // implement this, just so i can read the index from the model later
    void setTurtleImage(int turtleid, int shapeIndex);

    // tells me which turtle image to render
    int getTurtleImage(int turtleid);

    // I need to know all the turtle IDs to query
    List<Integer> getTurtles();
    Set<Integer> getColors();

    //TODO: ALSO, in order to track the turtles in real time, I need
    // u to add these private variables to each Turtle (no need to initialize them)
    // private SimpleDoubleProperty xPos;
    // private SimpleDoubleProperty yPos;
    // private SimpleBooleanProperty PenUp;
    // private SimpleDoubleProperty PenThickness;

    // these next ones are RGB for color

    // private SimpleIntegerProperty R;
    // private SimpleIntegerProperty G;
    // private SimpleIntegerProperty B;

    void setXPos(int turtleId, SimpleDoubleProperty xpos);
    void setYPos(int turtleId, SimpleDoubleProperty ypos);
    void setPenUp(int turtleId, SimpleBooleanProperty penUp);
    void setPenThickness(int turtleId, SimpleDoubleProperty thickness);
    void setR(int turtleId, SimpleIntegerProperty r);
    void setG(int turtleId, SimpleIntegerProperty g);
    void setB(int turtleId, SimpleIntegerProperty b);

    SimpleDoubleProperty getXPos(int turtleId);
    SimpleDoubleProperty getYPos(int turtleId);
    SimpleBooleanProperty getPenUp(int turtleId);
    SimpleDoubleProperty getPenThickness(int turtleId);
    SimpleIntegerProperty getR(int turtleId);
    SimpleIntegerProperty getG(int turtleId);
    SimpleIntegerProperty getB(int turtleId);

    void populateBoard();






}
