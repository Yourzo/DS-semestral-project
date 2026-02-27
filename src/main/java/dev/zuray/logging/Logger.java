package dev.zuray.logging;

public abstract class Logger {

    private static void putMessage(String decoratedMessage) {
        System.out.println(decoratedMessage + " [THREAD ID]: " + Thread.currentThread().threadId());
    }

    public static void info(String message) {
        putMessage("[INFO]: " + message);
    }

    public static void warn(String message) {
        putMessage("[WARN]: " + message);
    }

    public static void fatal(String message) {
        putMessage("[FATAL]: " + message);
    }

    public static void debug(String message) {
        putMessage("[DEBUG]: " + message);
    }
}
