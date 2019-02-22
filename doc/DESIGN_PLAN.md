# Design Plan

## Introduction
The primary goal our this project is to implement a MVC structure, with controller classes that co-ordinate the Model with the View. We want to make the code flexible so that we can internally create more commands and different GUI components. The View allows the user to enter commands, then the Controller parses the commands, and updates the Model. The View then queries the Model and displays the changes on-screen.

## Design overview
We have broken our design into an MVC model. The View component will consist of a GUI class that will pull together all the components of the UI. Additionally, there will be classes such as HistoryView, VariableView, and TurtleView which will be observers of their respective models. When a command is passed through the Console, it will be sent to a parser which will act as the controller. This can then update the appropriate models (Turtle, Variables, etc). When these models are updated, the observers will be notified and can update the graphics accordingly.

**Backend Internal API:** This includes all the necessary methods to update the models and their data. The methods in this API will all be called after the command entered by the user has been parsed.

**Backend External API:** This includes the method necessary to pass a command entered into the UI Console to the parser as well as methods necessary for an observer of models (TurtleModel, VariableModel, etc.) to get an object with the information necessary to update the graphics.

**Frontend Internal API:** This includes the methods that are used to update each of the view classes (TurtleView, VariablesView, etc) as well  methods to print to the Console, for example errors or any other message or statement that may need to be printed to the user. All of these will be called either when they are notified that a model is updated or if an error is thrown by the backend when parsing a command.

**FrontEnd External API:** This has methods such that the the backend can interact directly with the Board class, where the drawing and turtle are visible. For example, changing the background color of the Board or clearing the drawings on the Board. These will be called by the backend when commands are entered to change the color or clear the screen.

![](https://i.imgur.com/ovN746k.png)

## User Interface
Our user interface will consist of two windows: a terminal and a display. 
The display will have a window which will contain any drawing that takes place as well as the turtle. Also in the display, smaller windows will be available to show the command history, the variables currently assigned, as well as the names of the user-defiend commands. These smaller windows will have a scroll option, such that once the number of commands or variables is too long for the designated space, the user will be able to scroll through them

The terminal window will currently consist of a space for users to enter commands as well as buttons off to the side. There will be a button to toggle between entering one line of code vs. multiple. There will also be a run code button such that the script or line will be run.

![](https://i.imgur.com/GNWhqEm.png)


## API details

#### Backend internal API
The backend internal API will include all of the necessary methods that will update the models, such as HistoryModel, VariablesModel, and TurtleModel. This will include methods that allow another class to add data, remove data, parse a command, add a new command. It will also allow functionality to get properties from the turtle and change those properties. In our program, these commands are all being called after the console on the front end passes in a command into the parser in the back end.

```
public interface BackInternal {

    /**
     * Defined within the UserSetCommandModel
     * The user will be able to add a command with relevant variables and predefined commands that can be called at any other time
     * @param commandName
     * @param input
     */
    public void addCommand(String commandName, String input);

    /**
     * Defined within the VariablesModel
     * The user can define a variable that can be accessed through any other command
     * @param var
     */
    public void addVariable(Variable var);

    /**
     * Defined within the VariablesModel
     * Removes a variable from the current state
     * @param var
     * @throws InvalidInputException if the variable doesn't exist
     */
    public void removeVariable(Variable var) throws InvalidInputException;

    /**
     * Defined within the TurtleModel
     * @return Java Pair object with x coordinate and y coordinate of the turtle
     */
    public Pair getTurtleCoordinates();

    /**
     * Defined within the TurtleModel
     * Places the turtle at a certain place on the board
     * @param coordinates
     * @throws InvalidInputException if the coordinates are outside the bounds of the dimensions of the board
     */
    public void moveTurtleToCoordinates(Pair coordinates) throws InvalidInputException;

    /**
     * Defined within the TurtleModel
     * Gets the current direction of the turtle on the board
     * @return direction of the turtle
     */
    public double getTurtleDirection();

    /**
     * Defined within the TurtleModel
     * Sets the direction of the turtle to a certain direction
     * Should handle degrees greater than a value of 360
     * @param new direction of the turtle
     */
    public void setTurtleDirection(double direction);

    /**
     * Defined within the TurtleModel
     * Sets the pen up
     */
    public void setPenUp();

    /**
     * Defined within the TurtleModel
     * Sets the pen down
     */
    public void setPenDown();

    /**
     * Defined within the TurtleModel
     * Shows the turtle
     */
    public void showTurtle();

    /**
     * Defined within the TurtleModel
     * Hides the turtle
     */
    public void hideTurtle();

    /**
     * Defined within the HistoryModel
     * Adds a certain input to the history data
     */
    public void addCommandToHistory();

    /**
     * Defined within the HistoryModel
     * Removes a certain input from the history data
     */
    public void removeCommandFromHistory() throws InvalidInputException;

    /**
     * Defined within the HistoryModel
     * Clears all history
     */
    public void clearAllHistory();

    /**
     * Defined within the controller
     * Parses any input to a command
     * @param consoleInput
     */
    public void parseCommand(String consoleInput) throw BackEndInternal.Exceptions.IllegalCommandException;
}


```

#### Backend external API
The back-end external API works to provide the front-end with the necessary updated models of `Turtle`, `History`, and `Variables`, `Commands`, once the back-end has parsed the commands entered from the front-end. When the user enters a command, the back-end allows the front-end to pass the unprocessed commands via `parse()`, which connects the user interface to the back-end. This API will throw `BackEndInternal.Exceptions.IllegalCommandException` is the command that is parsed is invalid.

```
import java.util.List;
import java.util.Map;

public interface BackExternalAPI {

    /**
     * Part of the controller. Front-end calls parse() to give unprocessed commands to back-end.
     * @return a unprocessed text object
     */
    public Object parse(String input) throws BackEndInternal.Exceptions.IllegalCommandException;

    /**
     * When the front-end gets told by TurtleModel that the turtle as been updated, the front-end calls getTurtle()
     * to get the latest turtle with updated parameters
     * @return a turtle object
     */
    public Object getTurtle();

    /**
     * To update the history view, the front-end calls getHistory() to get the latest history of commands
     * @return a history object
     */
    public List<String> getHistory();

    /**
     * To update variables view, the front-end calls getVariables() to get the latest set of variables
     * @return a variable object
     */
    public Map<String, Double> getVariables();

    /**
     * Gets a list of the user defined commands
     * @return
     */
    public List<String> getUserCreatedCommands();

}

```

#### Frontend internal API
The front-end internal API will include the necessary methods to update the windows displayed to the user. This includes methods to update the history, variables, and user-defined commands as well as methods to display errors to the user and to print to the console. Since parts of the view will follow the `Observer` pattern and monitor the state of different models on the backend, methods from the internal API will be invoked once these models indicate changes in their states.

```
public interface FrontInternal.FrontInternalAPI {

    /**
     * Part of GUI class
     * adds an element to the root of the scene
     * it will take in an object to add to the stage
     */
    public void addElement();

    /**
     * Part of board class
     * clears the drawings
     */
    public void clearBoard();

    /**
     * part of board class
     * changes the background color of the void
     * will have an argument for the color TBD
     */
    public void setBackgroundColor();

    /**
     * part of Console class
     * prints an error to be seen by the user
     * will be used to print errors to user when catching thrown by backend
     */
    public void printError(String s);

    /**
     * Part of Console class.
     * prints to the console
     */
    public void printToConsole(String s);

}

```

#### Frontend external API
The Front-end external has methods that allow for the backend to interact directly with the Board Class. The Board Class will be where the drawing occurs. Through this API, the backend will be able to call `setBackgroundColor()` as well as  `clearBoard()`. We may also choose to implement another external API that controls the actions of the Turtle(s) specifically, since the backend/controller classes will be responsible for that as well.

```
public interface FrontExternalAPI {
    /**
     * part of Board class
     * clears the drawings off of the board
     */
    public void clearBoard();

    /**
     * part of board class
     * changes the background color of the void
     * will have an argument for the color TBD
     */
    public void setBackgroundColor(Paint color);
    
    /**
     * part of TurtleView class.
     * updates the turtle based on changes made to the TurtleModel
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void updateTurtle();

    /**
     * part of the HistoryView class.
     * updates the history based on the changes made to the HistoryModel
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void updateHistory();

    /**
     * part of the VariableView class.
     * updates the variables based on the changes made to the VariablesModel
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void updateVariables();    
    
    /**
     * Part of the UserDefinedCommandView class
     * updates the user defined commands based on the changed made to the UserDefinedCommands model
     */
    public void updateUserDefinedCommands();    
    
    
}
```



## API example code
When the user enters `fd 50` in the command window, the front-end will received this text and call `parse()`. Then this command is processed in `TurtleCommands` and `moveToCoordinates()` and `addPath()` are called. Since `TurtleView` is an observer of `TurtleModel`, it will then call `getTurtle()` to render the latest changes in turtle parameters. The controller will also call `addPath()`, which draws the line/shape on the board. Then, `addCommandToHistory()` in the `HistoryModel` is called to add the latest executed command to the `HistoryModel`. Similarly, `HistoryView` responds to this change by calling `getHistory()`.

After this series of API calls, turtle move in the display window, rendering a line, and the command is added to the environment's history.

### Use Cases
#### Feroze
The user presses a button or enters a command to change the background color of the display window. This action is passed to the `Controller`, which access the front-end external API and calls the `setBackgroundColor()` method of the Board class. Alternatively, the `Controller` could enact a state change in a `Model` class after which an observer of that model could call its own internal API method to change the background color. These different control flows depend on the exact design we end up agreeing on.

The user enters a bad command into the `Console`. This command is parsed by the Controller and is recognized as a bad command. The `Controller` calls the `printError()` method of the `Console` to notify the user of the mistake.
#### Carrie
The user presses a button to run the single line of code they wrote into the terminal. The `Console` will then read the line passed by the user and pass it to the `Controller` through the `parse()` method. The `Console` will then clear the line and reset itself such that it is ready for the next command. Once the command has been parsed, the `Controller` will update the `HistoryModel`. This will then alert its receivers and the `HistoryView` class will call `updateHistoryView` to display the command.

The user presses a button to toggle between entering a single line of code to multiple lines. The `Console` will then have change how it interprets the user hitting the Enter key. For a single line, this will behave as though the user had hit run and the `Console` will read the line and call `parse()` to pass it on to the `Controller`. When the button is clicked to switch to multiple lines, hitting enter will move to the next line and will no longer call `parse()`. The `Console` will then only call `parse()` once the Run button is pressed and it will then pass the input to the parser line by line.
#### Christina
The `Controller` is passed the `TO` command, in which a new command is created. From here, the `Controller` will recognize that this command is something the `UserSetCommandModel` should handle. The `Controller` will then pass this new command name, the variables defined, and the commands into the `UserSetCommandModel` through the `addCommand()` method. Then, the model will store all of this data. Therefore, whenever this user-set command is called again, it can be retrieved from the model and it will run the associated commands.

If a `MAKE` command is generated, the `Controller` will get the return value of the expression that the variable is set to from the respective model (`MathCommands`, `BooleanCommands`). Then, the parser will pass the variable name and value into the `VariablesModel` through the `addVariable()` method, which will then map the variable name to the value in the model. Then,`updateVariables()` in the `VariablesView` would be called, which would trigger the front end to change the variables display.

#### Michael
The first use-case I will address is turtle movement. When the user enters a command that affects the `Turtle`, I will take ownership of making sure that the turtle itself is getting the right parameters updated. I will be creating the back-end internal APIs, such as `moveTurtleToCoordinates()`, `showTurtle()`, and so on. Once the `Turtle` is updated with the latest parameters, I will make sure that the `getTurtle()` method provides the front-end with the correct updates.

The second use-case is to allow users to remember the latest commands they entered. The feature for this is a history tool and I will manage the back-end. Every command that is parsed will also be logged in `History` using the `addCommandToHistory()` API. Then the front-end would interact with it using `getHistory()`.

## Design Considerations

#### MVC
We plan to use the MVC design structure in our program. The pros of this design decision is to separate our program into Model, View, and Controller, to divide up responsibilities. However, this means that we created more dependencies between components.

#### Rendering the board
We plan to separate the rendering of the board from the movement of the turtle. The `Board` model deals with sketching the lines and shapes drawn whereas the `Turtle` model manages the update and maintenance of the turtle parameters. The cons of this is that we have separated a single command into independent parts: turtle movement and sketching. If we combine these actions into one--that is, have the turtle do the drawing--then this process is simplified. But, we decided on this implementation because we wanted to make our code as flexible as possible. Since the turtle does not need to handle the drawing mechanics, we move this feature to a different class so make our code more maintainable.

#### Flexibility of front-end components
We envision the repeated use of some JavaFX frameworks including different types of `View` classes on the front-end, so we anticipate creating superclasses that offer templates for these views from which we would be able to subclass and provide the user with different functionalities as desired.

## Team Responsibilities
Carrie - I will handle the Variable/History/User-DefinedCommands View classes as well as the terminal window.
Feroze - I will handle the main GUI class and the `Board` interactions with the `TurtleView`. 
Christina - I will handle the `Controller`, `UserSetCommands` and `Variables`
Michael - I will do the `History` and `Turtle` backend.
