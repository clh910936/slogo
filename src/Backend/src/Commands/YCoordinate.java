package Commands;

public class YCoordinate extends ZeroParamCommand {
    public YCoordinate(String language) {
        super(language);
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return myTurtle.getNextPointY();
    }
}
