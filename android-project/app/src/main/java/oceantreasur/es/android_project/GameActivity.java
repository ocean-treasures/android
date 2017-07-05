package oceantreasur.es.android_project;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private static final int PROGRESS_MAX = 100;

    private ProgressBar progressBar;
    private ImageView topLeft;
    private ImageView topRight;
    private ImageView bottomLeft;
    private ImageView bottomRight;
    private Typeface wordTypeFace;
    private TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.word = (TextView) findViewById(R.id.tv_word);
        this.progressBar.setMax(PROGRESS_MAX);

        this.topLeft = (ImageView) findViewById(R.id.iv_1);
        this.topRight = (ImageView) findViewById(R.id.iv_2);
        this.bottomLeft = (ImageView) findViewById(R.id.iv_3);
        this.bottomRight = (ImageView) findViewById(R.id.iv_4);

        progressBar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressBar.setProgress((progressBar.getProgress() + PROGRESS_MAX / 10) % PROGRESS_MAX);
            }
        });

        Call<NextWordResponse> call = OceanTreasuresApplication.getApi().getNextWord();

        call.enqueue(new Callback<NextWordResponse>() {
            @Override
            public void onResponse(Call<NextWordResponse> call, Response<NextWordResponse> response) {
                NextWordResponse nextWord = response.body();

                Glide.with(OceanTreasuresApplication.getStaticContext())
                        .load(nextWord.getPictures()[0].getResolvedUrl())
                        .fitCenter()
                        .into(topLeft);

                Glide.with(OceanTreasuresApplication.getStaticContext())
                        .load(nextWord.getPictures()[1].getResolvedUrl())
                        .fitCenter()
                        .into(topRight);

                Glide.with(OceanTreasuresApplication.getStaticContext())
                        .load(nextWord.getPictures()[2].getResolvedUrl())
                        .fitCenter()
                        .into(bottomLeft);

                Glide.with(OceanTreasuresApplication.getStaticContext())
                        .load(nextWord.getPictures()[3].getResolvedUrl())
                        .fitCenter()
                        .into(bottomRight);

                word.setText(nextWord.getWord().getWord().toString());

                Log.d("NXW", nextWord.toString());
            }

            @Override
            public void onFailure(Call<NextWordResponse> call, Throwable t) {
                Log.d("NXW", "ERROR");
            }
        });
    }

    public void onClick(View v) {
        Intent intent = new Intent(GameActivity.this, CorrectAnswerActivity.class);
        startActivity(intent);
    }

    private void ChooseNextActivity(boolean choice) {
        if(choice) {
            Intent intent = new Intent(GameActivity.this, CorrectAnswerActivity.class);
         //   intent.putExtra("url", );
        }
    }
}

