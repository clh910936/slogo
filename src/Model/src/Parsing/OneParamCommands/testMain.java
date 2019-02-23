package Parsing.OneParamCommands;

public class testMain {

    public static void main (String[] args) {
        Not n = new Not();
        n.addParameterToCommand(0);
        System.out.println(n.executeCommand());
    }
}
