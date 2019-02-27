package Commands;

import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Home extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    public Home(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
    }
    @Override
    public double executeCommand() throws ClassCastException {
        myTurtle.setPenUp();
        double dist = myTurtle.getDistToPoint(STARTX,STARTY);
        myTurtle.updatePoints(STARTX,STARTY);
        myTurtle.setPenDown();
        return dist;
    }
}
