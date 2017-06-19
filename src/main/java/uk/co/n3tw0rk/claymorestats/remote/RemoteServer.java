package uk.co.n3tw0rk.claymorestats.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.n3tw0rk.claymorestats.api.domain.Miners;
import uk.co.n3tw0rk.claymorestats.remote.Services.Ethereum;
import uk.co.n3tw0rk.claymorestats.remote.Services.ServerCommands;
import uk.co.n3tw0rk.claymorestats.remote.domain.ServerCommand;

import java.io.IOException;

public class RemoteServer {

    public void update(String server, String key, Miners miners) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Ethereum ethereum = retrofit.create(Ethereum.class);

        try {
            ethereum.update(key, miners).execute();
        } catch (IOException e) {
        }
    }

    public ServerCommand getEvent(String server, String key, String rig) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ServerCommands serverCommands = retrofit.create(ServerCommands.class);

        try {
            return serverCommands
                    .get(key, rig)
                    .execute()
                    .body();
        } catch (IOException e) {
        }
        return null;
    }
}
