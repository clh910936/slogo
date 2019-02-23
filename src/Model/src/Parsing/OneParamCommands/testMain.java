package Parsing.OneParamCommands;

import Parsing.TwoParamCommands.Maths.Sum;

public class testMain {

    public static void main (String[] args) {
        Sum s = new Sum();
        System.out.println(s.getCommandName());
        s.addParameterToCommand(3);
        //System.out.println(s.executeCommand());
        System.out.println(s.isCommandReadyToRemove());
        s.addParameterToCommand(8);
        System.out.println(s.executeCommand());
        System.out.println(s.isCommandReadyToRemove());
    }
}
