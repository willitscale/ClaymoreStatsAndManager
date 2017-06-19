package uk.co.n3tw0rk.claymorestats.remote.Services;

import retrofit2.Call;
import retrofit2.http.*;
import uk.co.n3tw0rk.claymorestats.api.domain.Miners;

public interface Ethereum {
    @POST("/")
    Call<Void> update(@Query("key") String key, @Body Miners miners);
}
