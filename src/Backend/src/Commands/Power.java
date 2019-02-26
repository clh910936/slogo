package Commands;

public class Power extends TwoParamCommandDoubles {
    public Power() {
        super();
    }

    @Override
    public double executeCommand() {
        return Math.pow(input1, input2);
    }
}
