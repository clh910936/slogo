package Parsing.Commands;

public class Product extends TwoParamCommandDoubles {

    public Product() {
        super();
    }

    @Override
    public double executeCommand() {
        return input1 * input2;
    }
}
