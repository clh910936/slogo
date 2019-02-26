package Commands;

public class Difference extends TwoParamCommandDoubles {

    public Difference() {
        super();
    }

    @Override
    public double executeCommand() {
        return input1 - input2;
    }
}
