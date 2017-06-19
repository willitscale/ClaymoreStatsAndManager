package uk.co.n3tw0rk.claymorestats.utils;

import java.io.IOException;

public class SystemEvents {

    public static void shutdown(boolean restart) throws RuntimeException, IOException {
        String shutdownCommand;
        String operatingSystem = System.getProperty("os.name");

        if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
            shutdownCommand = "shutdown " + ( restart ? "-r" : "-h" ) + " now";
        } else if ("Windows".equals(operatingSystem)) {
            shutdownCommand = "shutdown.exe " + ( restart ? "/r" : "/s" ) + " /f /t 0";
        } else {
            throw new RuntimeException("Unsupported operating system.");
        }

        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0);
    }
}
