package Parsing;

public class parsertestmain {
    public static void main (String[] args) {
        CommandParser cp = new CommandParser();
        System.out.println(cp.parse("Sum Sum 3 4 4", "English"));
    }

}
