package Parsing.Commands;

public class Remainder extends TwoParamCommandDoubles {
    public Remainder() {
        super();
    }

    @Override
    public double executeCommand() {
        return input1 % input2;
    }
}
