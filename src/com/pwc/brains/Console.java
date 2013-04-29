package com.pwc.brains;

public class Console {
    public static void info(String message, Exception e) {
        //write the info to log
    }

    public static void interrupt(String message) {
        System.out.println(message);
    }

    public static void exception(Exception e) {

    }

    public static void exception(String message, Exception e) {
        Console.info(message, e);
        e.printStackTrace();
    }
}
