package Commands;

import Models.Turtle;
import Models.UserDefinedCommandsModel;
import Models.VariablesModel;

public class Home extends ZeroParamCommand {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    public Home(String language, Turtle turtle, VariablesModel variablesModel, UserDefinedCommandsModel userDefinedCommandsModel) {
        super(language, turtle, variablesModel, userDefinedCommandsModel);
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
