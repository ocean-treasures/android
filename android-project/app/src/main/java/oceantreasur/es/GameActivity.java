package oceantreasur.es;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.network.model.CheckAnswerRequest;
import oceantreasur.es.network.model.NextWordResponse;
import oceantreasur.es.network.model.Picture;
import oceantreasur.es.network.model.Progress;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private static final int PROGRESS_MAX = 100;

    private String selectedPictureUrl;

    private ProgressBar progressBar;

    private Progress responseProgress;

    private ImageView topLeft;
    private ImageView topRight;
    private ImageView bottomLeft;
    private ImageView bottomRight;

    private NextWordResponse nextWord;

    private TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.word = (TextView) findViewById(R.id.tv_word);
        this.topLeft = (ImageView) findViewById(R.id.iv_1);
        this.topRight = (ImageView) findViewById(R.id.iv_2);
        this.bottomLeft = (ImageView) findViewById(R.id.iv_3);
        this.bottomRight = (ImageView) findViewById(R.id.iv_4);

        getResponse();
//        sendRequest();
    }

    public void getResponse() {
        Call<NextWordResponse> call = OceanTreasuresApplication.getApi().getNextWord();

        call.enqueue(new Callback<NextWordResponse>() {
            @Override
            public void onResponse(Call<NextWordResponse> call, Response<NextWordResponse> response) {
                nextWord = response.body();

                setupProgressBar(nextWord.getProgress().getCurrent(), nextWord.getProgress().getMax());
                loadImages(nextWord);
                loadText(nextWord);

                Log.d("ZAX", nextWord.toString());
            }

            @Override
            public void onFailure(Call<NextWordResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");
            }
        });
    }

    public void sendRequest(int wordId, int picId) {
        CheckAnswerRequest req = new CheckAnswerRequest(wordId, picId);
        Call<CheckAnswerResponse> call = OceanTreasuresApplication.getApi().checkAnswer(req);

        call.enqueue(new Callback<CheckAnswerResponse>() {
            @Override
            public void onResponse(Call<CheckAnswerResponse> call, Response<CheckAnswerResponse> response) {
                CheckAnswerResponse serverResponse = response.body();

                responseProgress = serverResponse.getProgress();

                chooseNextActivity(serverResponse.isCorrect(), serverResponse.getWord());

                Log.d("ZAX", serverResponse.toString());
            }

            @Override
            public void onFailure(Call<CheckAnswerResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");
            }
        });
    }

    public void setupProgressBar(int cur, int max) {
        progressBar.setMax(max * 10); // 50
        progressBar.setProgress(cur * 10);
    }

    public void loadImages(NextWordResponse nextWord) {

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[0].getResolvedUrl())
                .fitCenter()
                .into(topLeft);
        topLeft.setTag(nextWord.getPictures()[0]);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[1].getResolvedUrl())
                .fitCenter()
                .into(topRight);
        topRight.setTag(nextWord.getPictures()[1]);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[2].getResolvedUrl())
                .fitCenter()
                .into(bottomLeft);
        bottomLeft.setTag(nextWord.getPictures()[2]);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(nextWord.getPictures()[3].getResolvedUrl())
                .fitCenter()
                .into(bottomRight);
        bottomRight.setTag(nextWord.getPictures()[3]);
    }

    public void loadText(NextWordResponse nextWord) {
        word.setText(nextWord.getWord().getWord().toString());
    }

    public void onClick(View v) {
        Picture pic = (Picture) v.getTag();
        this.selectedPictureUrl = pic.getResolvedUrl();
        sendRequest(nextWord.getWord().getId(), pic.getId());
    }

    private void chooseNextActivity(boolean choice, String word) {
        Intent intent;
        String msgToDisplay;
        boolean isCorrect = false;

        intent = new Intent(GameActivity.this, AnswerActivity.class);

        if(choice) {
            isCorrect = true;
        }

        intent.putExtra("IS_CORRECT", isCorrect);
        intent.putExtra("WORD", word);
        intent.putExtra("URL", selectedPictureUrl);
        intent.putExtra("PROGRESS_CUR", responseProgress.getCurrent());
        intent.putExtra("PROGRESS_MAX", responseProgress.getMax());

        startActivity(intent);
        finish();
    }
}

