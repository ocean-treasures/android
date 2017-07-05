package oceantreasur.es.android_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Typeface playButtonTypeFace;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.playButton = (Button) findViewById(R.id.test1);
        this.playButtonTypeFace = FontManager.getInstance().getFont("fonts/CoolCrayon.ttf");

        this.playButton.setTypeface(playButtonTypeFace);

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });

//        CustomClient client = retrofit.create(CustomClient.class);
//        Call<CustomPOJO> call = client.getPerson(1);
//
//        call.enqueue(new Callback<CustomPOJO>() {
//            @Override
//            public void onResponse(Call<CustomPOJO> call, Response<CustomPOJO> response) {
//                CustomPOJO person = response.body();
//
//                Log.d("pkm", person.toString());
//            }
//
//            @Override
//            public void onFailure(Call<CustomPOJO> call, Throwable t) {
//                Log.d("pkm", "ERROR");
//            }
//        });

    }

    void startGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
