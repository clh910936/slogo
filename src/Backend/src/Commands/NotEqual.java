package Commands;

public class NotEqual extends TwoParamCommandDoubles {

    public NotEqual() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 != input2)? 1 : 0;
    }
}
