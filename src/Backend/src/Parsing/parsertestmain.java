package Parsing;

import Models.ModelManager;


public class parsertestmain {
    public static void main (String[] args) {
        ModelManager mm = new ModelManager();
        CommandParser cp = new CommandParser(mm);

//        System.out.println(cp.parseCommand("IFELSE sum 0 1 \n" +
//                "[ sum 2 3 ] \n" +
//                "[ sum 20 30 ]", "English"));

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
        //System.out.println(cp.parseCommand("make :random 2", "English"));

//        System.out.println(cp.parseCommand(
//                "to dash [ :count :size ]\n" +
//                "[\n" +
//                "  repeat :count\n" +
//                "  [\n" +
//                "    pu fd :size pd fd :size\n" +
//                "  ]\n" +
//                "]\ndash 20 8", "English"));
//        System.out.println(cp.parseCommand("to square [ :distance ]\n" +
//                "[\n" +
//                "  repeat 4 [\n" +
//                "    fd :distance\n" +
//                "    rt 90\n" +
//                "  ]\n" +
//                "]\n" +
//                "\n" +
//                "\n" +
//                "to face [ ]\n" +
//                "[\n" +
//                "  pendown square 100\n" +
//                "  penup forward 20\n" +
//                "  right 90 forward 25\n" +
//                "  pendown forward 50\n" +
//                "  penup back 75\n" +
//                "  left 90 forward 65\n" +
//                "  right 90 forward 20\n" +
//                "  pendown square 15\n" +
//                "  penup forward 45\n" +
//                "  pendown square 15           \n" +
//                "  penup back 15\n" +
//                "  right 90 forward 20 left 45\n" +
//                "  pendown square 20\n" +
//                "]\n" +
//                "\n" +
//                "\n" +
//                "face\n", "English"));
//        System.out.println(cp.parseCommand("make :distance 50\n" +
//                "\n" +
//                "fd :distance\n" +
//                "rt 90\n" +
//                "fd :distance\n" +
//                "rt 90\n" +
//                "fd :distance\n" +
//                "rt 90\n" +
//                "fd :distance\n" +
//                "rt 90\n", "English"));



//        System.out.println(cp.parseCommand("repeat 4 [\n" +
//                "  fd 100\n" +
//                "  rt 90\n" +
//                "]", "English"));
//        System.out.println(cp.parseCommand("ifelse equal? 10 10 [ sum 10 0 ] [ sum 1 1 ]", "English"));
//        System.out.println(cp.parseCommand("to example [ :x ] [ ifelse equal? :x 10 [ sum 10 0 ] [ example + :x 1 ] ]", "English"));
//        System.out.println((cp.parseCommand("example 9", "English")));
//        System.out.println(cp.parseCommand("to example [ :x ]\n" +
//                "[\n" +
//                "  if greater? :x 10\n" +
//                "  [\n" +
//                "    example difference :x 10\n" +
//                "  sum 10 10 ]\n" +
//                "  fd 50\n" +
//                "  right 10\n" +
//                "]\n" +
//                "\n" +
//                "example 100", "English"));
//        System.out.println(cp.parseCommand("dotimes [ :t 10 ] [\n" +
//                "      fd 1\n" +
//                "      rt / sin :t 2\n" +
//                "   ]", "English"));
//        System.out.println(cp.parseCommand("ifelse less? random 10 5 [\n" +
//                "    rt 60\n" +
//                "  ] [\n" +
//                "    lt 60\n" +
//                "  ]", "English"));
//        System.out.println(cp.parse("rt for [ :dist 10 40 10 ] [ \n" +
//                "  fd :dist\n" +
//                "]", "English"));


        //OBSERVER
//        Turtle tm = new Turtle(2000, 2000,false, 0, true);
//        TurtleListener tl = new TurtleListener(tm);
//        tm.add(tl);
//        System.out.println(cp.parseCommand("fd 10", "English"));
    }
}
