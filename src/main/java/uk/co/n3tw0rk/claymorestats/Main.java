package uk.co.n3tw0rk.claymorestats;

import uk.co.n3tw0rk.claymorestats.api.Claymore;
import uk.co.n3tw0rk.claymorestats.remote.RemoteServer;
import uk.co.n3tw0rk.claymorestats.remote.domain.Command;
import uk.co.n3tw0rk.claymorestats.remote.domain.ServerCommand;
import uk.co.n3tw0rk.claymorestats.utils.SystemEvents;

import java.io.IOException;
import java.util.Properties;

public class Main {

    private final Claymore claymore = new Claymore();
    private final RemoteServer remoteServer = new RemoteServer();
    private final Properties properties = new Properties();

    public Main() {
        loadProperties();
        for (int i = 1; i <= Integer.parseInt(properties.getProperty("rigs")); i++) {
            new Thread(new ThreadRunner(
                    properties.getProperty("rig." + String.format("%02d", i) + ".name"),
                    properties.getProperty("rig." + String.format("%02d", i) + ".card"),
                    properties.getProperty("rig." + String.format("%02d", i) + ".host"),
                    Integer.parseInt(properties.getProperty("rig." + String.format("%02d", i) + ".port"))
            )).start();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    private void loadProperties() {
        try {
            properties.load(getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ThreadRunner implements Runnable {

        private final String rig;
        private final String cards;
        private final String hostname;
        private final int port;

        public ThreadRunner(String rig, String cards, String hostname, int port) {
            this.rig = rig;
            this.cards = cards;
            this.hostname = hostname;
            this.port = port;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    remoteServer.update(
                            properties.getProperty("remote.server"),
                            properties.getProperty("remote.key"),
                            claymore.query(
                                    rig,
                                    cards,
                                    hostname,
                                    port
                            )
                    );

                    executeEvent(remoteServer.getEvent(
                            properties.getProperty("remote.server"),
                            properties.getProperty("remote.key"),
                            rig
                            )
                    );

                    Thread.sleep(3000l);
                } catch (InterruptedException | IOException e) {
                }
            }
        }

        private void executeEvent(ServerCommand serverCommand) {
            if (null == serverCommand) {
                return;
            }

            try {
                switch (serverCommand.getCommand()) {
                    case RESTART:
                        SystemEvents.shutdown(true);
                        break;
                    case SHUTDOWN:
                        SystemEvents.shutdown(false);
                        break;
                    case KILL_MINER:
                        break;
                    case START_MINER:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
