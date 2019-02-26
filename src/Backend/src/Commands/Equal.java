package Commands;

public class Equal extends TwoParamCommandDoubles {

    public Equal() {
        super();
    }

    @Override
    public double executeCommand() {
        return (input1 == input2)? 1 : 0;
    }
}
