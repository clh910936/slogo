package Parsing;

import Variables.VariablesModel;

public class parsertestmain {
    public static void main (String[] args) {
        CommandParser cp = new CommandParser(new VariablesModel());
        System.out.println(cp.parse("fd - Sum 3 4 9", "English"));
    }

}
