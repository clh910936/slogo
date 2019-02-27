package Commands;

public class XCoordinate extends ZeroParamCommand {
    public XCoordinate(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getNextPointX();
    }
}
