package uk.co.n3tw0rk.claymorestats.utils;

import java.io.IOException;

public class Processes {

    public static void kill(String process) throws IOException {
        Runtime.getRuntime().exec("TASKKILL /F /IM " + process);
    }

    public static void start(String process) throws IOException {
        Runtime.getRuntime().exec("cmd /c start " + process);
    }
}
