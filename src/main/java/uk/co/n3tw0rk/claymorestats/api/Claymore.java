package uk.co.n3tw0rk.claymorestats.api;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.joda.time.DateTime;
import uk.co.n3tw0rk.claymorestats.api.domain.Miner;
import uk.co.n3tw0rk.claymorestats.api.domain.Miners;
import uk.co.n3tw0rk.claymorestats.api.domain.Stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Claymore {

    public String queryRaw(String serverHostname, int port) throws IOException {

        Socket echoSocket = new Socket(serverHostname, port);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        out.println("{\"id\":0,\"jsonrpc\":\"2.0\",\"method\":\"miner_getstat1\"}");
        String response = in.readLine();

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();

        return response;
    }

    public Miners query(String rig, String cards, String serverHostname, int port) throws IOException {

        Socket echoSocket = new Socket(serverHostname, port);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        out.println("{\"id\":0,\"jsonrpc\":\"2.0\",\"method\":\"miner_getstat1\"}");
        String response = in.readLine();

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();

        Stats stats = new Gson().fromJson(response, Stats.class);

        Miners miners = new Miners();

        miners.setRig(rig);
        miners.setCards(cards);
        miners.setVersion(stats.getResult().get(0));
        miners.setStarted(new DateTime().minusDays(Integer.parseInt(stats.getResult().get(1))).toString());
        miners.setUptime(Integer.parseInt(stats.getResult().get(1)));

        String[] summary = stats.getResult().get(2).split(";");

        miners.setHashrate(Integer.parseInt(summary[0]));
        miners.setShares(Integer.parseInt(summary[1]));
        miners.setRejected(Integer.parseInt(summary[2]));

        String[] minerRates = stats.getResult().get(3).split(";");
        String[] minerTemps = stats.getResult().get(6).split(";");

        List<Miner> minerList = Lists.newArrayList();

        for (int i = 0, j = 0; i < minerRates.length; i++, j += 2) {
            minerList.add(new Miner(
                    Integer.parseInt(minerTemps[j]),
                    Integer.parseInt(minerTemps[j + 1]),
                    Integer.parseInt(minerRates[i])
            ));
        }

        miners.setMiners(minerList);
        miners.setPool(stats.getResult().get(7));

        return miners;
    }
}
