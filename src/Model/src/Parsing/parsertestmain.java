package Parsing;

import Variables.VariablesModel;

public class parsertestmain {
    public static void main (String[] args) {
        CommandParser cp = new CommandParser(new VariablesModel());
        System.out.println(cp.parseCommand("repeat 10\n" +
                "[\n" +
                "  pd fd 10 pu fd 10\n" +
                "]\n", "English"));
//        System.out.println(cp.parse("rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]", "English"));
    }
}
