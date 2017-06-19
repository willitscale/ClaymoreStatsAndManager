package uk.co.n3tw0rk.claymorestats.remote.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.co.n3tw0rk.claymorestats.remote.domain.ServerCommand;

public interface ServerCommands {
    @GET("/")
    Call<ServerCommand> get(@Query("key") String key, @Query("rig") String rig);
}
