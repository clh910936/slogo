package Parsing.OneParamCommands;

public class testMain {

    public static void main (String[] args) {
        Random rn = new Random();
        System.out.println(rn.isCommandReadyToRemove());
        rn.addParameterToCommand(5);
        System.out.println(rn.isCommandReadyToRemove());
        System.out.println(rn.executeCommand());
//        rn.addParameterToCommand(6);
//        System.out.println(rn.isCommandReadyToRemove());
    }
}
