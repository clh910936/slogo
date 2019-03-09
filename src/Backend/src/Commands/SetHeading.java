package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Models.Turtle;

public class SetHeading extends OneParamCommand {

    public SetHeading(ModelManager modelManager
) {
        super(modelManager
);
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        Turtle myTurtle =(Turtle) this.getMyTurtleModel().getCurrentTurtle();

        double difference = myTurtle.getDegreesDifference(Double.parseDouble(getMyParams().get(0).toString()));
        myTurtle.setHeadingAngle(Double.parseDouble(getMyParams().get(0).toString()));
        return difference;
    }
}
