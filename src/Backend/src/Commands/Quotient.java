package Commands;

public class Quotient extends TwoParamCommandDoubles {

    public Quotient() {
        super();
    }

    @Override
    public double executeCommand() {
        return input1 / input2;
    }
}
