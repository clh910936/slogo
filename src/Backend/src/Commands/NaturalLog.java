package Commands;

public class NaturalLog extends OneParamCommand {
    public NaturalLog() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.log(input);
    }

}
