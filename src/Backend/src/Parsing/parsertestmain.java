package Parsing;

import Models.TurtleListener;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;

public class parsertestmain {
    public static void main (String[] args) {

//        System.out.println(cp.parseCommand("# same as fd 100\n" +
//                "fd fd 50\n" +
//                "\n" +
//                "# same as rt 100 fd 100\n" +
//                "fd rt 100\n" +
//                "\n" +
//                "# same as fd 100\n" +
//                "fd * greater? 5 3 100\n" +
//                "\n" +
//                "# same as fd 10 fd 20 fd 30 fd 40 rt 40\n" +
//                "rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]\n", "English"));
//        System.out.println(cp.parseCommand("sum 10 10", "English"));
//        System.out.println(cp.parseCommand("repeat 20\n" +
//                "  [\n" +
//                "    pu fd 8 pd fd 8\n" +
//                "  ]\n", "English"));
//        System.out.println(cp.parseCommand(
//                "to dash [ :count :size ]\n" +
//                "[\n" +
//                "  repeat :count\n" +
//                "  [\n" +
//                "    pu fd :size pd fd :size\n" +
//                "  ]\n" +
//                "]\ndash 20 8", "English"));
//        System.out.println(cp.parseCommand("setxy 3 4", "English"));
        //System.out.println(cp.parseCommand("seth -90", "English"));


//        System.out.println(cp.parseCommand("repeat 4 [\n" +
//                "  fd 100\n" +
//                "  rt 90\n" +
//                "]", "English"));
//        System.out.println(cp.parseCommand("left -360", "English"));
        //System.out.println(cp.parseCommand("sin 180", "English"));
//        System.out.println(cp.parseCommand("dotimes [ :t 10 ] [\n" +
//                "      fd 1\n" +
//                "      rt / sin :t 2\n" +
//                "   ]", "English"));
//        System.out.println(cp.parseCommand("rt for [ :x 10 40 10 ] [ sum :x 10 ]", "English"));
//        System.out.println(cp.parse("rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]", "English"));

        TurtleModel tm = new TurtleModel(2000, 2000,false, 0, true);
        CommandParser cp = new CommandParser(new VariablesModel(), tm, new UserCreatedCommandsModel());
        TurtleListener tl = new TurtleListener(tm);
        tm.add(tl);
        System.out.println(cp.parseCommand("fd 10", "English"));
    }
}
