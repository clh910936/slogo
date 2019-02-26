package Commands;

public class LessThan extends TwoParamCommandDoubles {

    public LessThan() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 < input2)? 1 : 0;
    }
}
