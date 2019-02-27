package Commands;

public class Product extends TwoParamCommand {

    public Product() {
        super();
    }

    @Override
    public double executeCommand() throws ClassCastException {
        return (double) input1 * (double) input2;
    }
}
