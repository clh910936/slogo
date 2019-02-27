package Commands;

public class Heading extends ZeroParamCommand {
    public Heading(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getHeadingAngle();
    }
}
