package oceantreasur.es.android_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.Console;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Typeface playButtonTypeFace;
    private Button playButton;
    private ImageButton testButton;
    private ImageView testImageView;

    private static final String BASE_URL = "https://swapi.co/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.playButton = (Button) findViewById(R.id.test1);
        this.testButton = (ImageButton) findViewById(R.id.testButton);
        this.testImageView = (ImageView) findViewById(R.id.testImageView);
        this.playButtonTypeFace = Typeface.createFromAsset(getAssets(), "fonts/CoolCrayon.ttf");

        playButton.setTypeface(playButtonTypeFace);

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });

        Glide.with(this)
                .load(R.drawable.chest)
                .fitCenter()
                .into(testImageView);

        Glide.with(this)
                .load(R.drawable.chest)
                .fitCenter()
                .into(testButton);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        CustomClient client = retrofit.create(CustomClient.class);
        Call<CustomPOJO> call = client.getPerson(1);

        call.enqueue(new Callback<CustomPOJO>() {
            @Override
            public void onResponse(Call<CustomPOJO> call, Response<CustomPOJO> response) {
                CustomPOJO person = response.body();

                Log.d("pkm", person.toString());
            }

            @Override
            public void onFailure(Call<CustomPOJO> call, Throwable t) {
                Log.d("pkm", "ERROR");
            }
        });

    }

    void startGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
