package nyc.c4q.leighdouglas.retrofitchucknorris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import nyc.c4q.leighdouglas.retrofitchucknorris.model.ChuckNorrisJoke;
import nyc.c4q.leighdouglas.retrofitchucknorris.network.ChuckNorrisService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChuckNorrisActivity extends AppCompatActivity {

    private TextView chuckTv;
    public String TAG;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuck_norris);
        initViews();

        TAG = "Tag";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ChuckNorrisService service = retrofit.create(ChuckNorrisService.class);

        Call<ChuckNorrisJoke> call = service.getRandomJoke();

        call.enqueue(new Callback<ChuckNorrisJoke>() {
            @Override
            public void onResponse(Call<ChuckNorrisJoke> call, Response<ChuckNorrisJoke> response) {
                try {
                    if(response.isSuccessful()) {
                        Log.d(TAG, "Success: " + response.body().getValue());
                        chuckTv.setText(response.body().getValue());
                    } else {
                        Log.d(TAG, "Error" + response.errorBody().string());
                    }
                } catch(IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ChuckNorrisJoke> call, Throwable t) {
                // Something went completely wrong (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });

        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(image);

    }
    public void initViews(){
        chuckTv = (TextView) findViewById(R.id.tv_chuck);
        image = (ImageView) findViewById(R.id.image);
    }


}
