package nyc.c4q.leighdouglas.retrofitchucknorris.network;

import nyc.c4q.leighdouglas.retrofitchucknorris.model.ChuckNorrisJoke;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by leighdouglas on 10/23/16.
 */

public interface ChuckNorrisService {

        @GET("jokes/random")
        Call<ChuckNorrisJoke> getRandomJoke();

}
