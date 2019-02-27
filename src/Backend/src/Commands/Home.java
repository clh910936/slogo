package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class Home extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    public Home(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
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
