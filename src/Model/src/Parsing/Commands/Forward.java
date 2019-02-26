package Parsing.Commands;

import Turtle.TurtleModel;

public class Forward extends OneParamCommand {

    public Forward(TurtleModel turtleModel) {
        super();
        this.turtle = turtleModel;
    }

    @Override
    public double executeCommand() {
        this.turtle.moveForward(input);
        this.turtle.printTurtleStatus();
        return input;
    }
}
