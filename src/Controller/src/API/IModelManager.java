package API;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * IModelManager is the backend API which the front-end calls upon to parse commands and update states in the model.
 * @author everyone
 */
public interface IModelManager {
    /**
     * @return The state of the current user defined commands
     */
    Map<String,String> getUserDefinedCommands();

    /**
     * @return The state of current variables
     */
    Map<String,String> getVariables();

    /**
     * @return The state of the current history
     */
    List<String> getHistory();

    /**
     * Used by the frontend to send a command to the backend
     * @param input the input string of the command
     * @param language the language the command is written in
     */
    void parseCommand(String input, String language);

    /**
     * Determines whether a given command from previous was valid or not.
     * @param index the index of the command in the list of commands
     * @return true if successful, false otherwise
     */
    boolean getSuccessOfHistoryEntry(int index);

    /**
     * Saves the state models to a given filename
     * @param fileName filename to be written to
     */
    void saveCurrentState(String fileName);

    /**
     * Sets the model according to a file
     * @param fileName name of the file
     * @param language language the file is written in
     */
    void setStateFromFile(String fileName, String language);

    /**
     * Returns all the saved files
     * @return list of the saved state files
     */
    List<String> getSavedFilesList();

    /**
     * Updates the variable model
     * @param variableName name of the variable
     * @param value new variable value
     */
    void changeVariable(String variableName, String value);

    /**
     * Adds a color to the palette, specified by its index and rgb values
     * @param index new color index
     * @param r new color r-value
     * @param g new color g-value
     * @param b new color b-value
     */
    void addPalette(int index, int r, int g, int b);

    /**
     * @return the index of the currently active turtle
     */
    int getCurrentActiveTurtle();

    /**
     * Sets the currently active turtle
     * @param index index of turtle in the list of turtles
     */
    void makeCurrentActiveTurtle(int index);

    /**
     * Sets the image of a turtle given its id
     * @param turtleid the id of the turtle
     * @param shapeIndex shape number in array of shapes (images)
     */
    void setTurtleImage(int turtleid, int shapeIndex);

    /**
     * Returns the current image of a turtle
     * @param turtleid id of the turtle
     * @return image index in array of images
     */
    int getTurtleImage(int turtleid);

    /**
     * @return a list of all current turtle id's
     */
    List<Integer> getTurtles();

    /**
     * @return a list of all current color id's
     */
    Set<Integer> getColors();

    /**
     * The following methods are used to link the front-end turtle status variables with the back-end model variables
     * using properties. Each takes the id of a turtle and a Property, which is initially set by the front-end. When
     * the backend makes updates to the property, the front-end knows and can update automatically.
     */
    void setXPos(int turtleId, SimpleDoubleProperty xpos);
    void setYPos(int turtleId, SimpleDoubleProperty ypos);
    void setPenUp(int turtleId, SimpleBooleanProperty penUp);
    void setPenThickness(int turtleId, SimpleDoubleProperty thickness);
    void setR(int turtleId, SimpleIntegerProperty r);
    void setG(int turtleId, SimpleIntegerProperty g);
    void setB(int turtleId, SimpleIntegerProperty b);

    /**
     * The following methods are the getters to the setters used above, made to retrieve the properties initialized
     * when a turtle is create on the front-end.
     */
    SimpleDoubleProperty getXPos(int turtleId);
    SimpleDoubleProperty getYPos(int turtleId);
    SimpleBooleanProperty getPenUp(int turtleId);
    SimpleDoubleProperty getPenThickness(int turtleId);
    SimpleIntegerProperty getR(int turtleId);
    SimpleIntegerProperty getG(int turtleId);
    SimpleIntegerProperty getB(int turtleId);

    /**
     * Creates the first turtle on the board after everything else is set up.
     */
    void populateBoard();
}
