package oceantreasur.es.android_project;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity {

    private String selectedPictureUrl;

    private ProgressBar progressBar;

    private Progress responseProgress;

    private int[] ids = {R.id.iv_1,
                         R.id.iv_2,
                         R.id.iv_3,
                         R.id.iv_4};

    private ImageView[] imageViews = new ImageView[ids.length];

    private NextWordResponse nextWord;

    private TextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupActivity();
        getResponse();
    }

    public void setupActivity() {
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.word = (TextView) findViewById(R.id.tv_word);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(ids[i]);
        }
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
        progressBar.setMax(max * 10);
        progressBar.setProgress(cur * 10);
    }

    public void loadImages(NextWordResponse nextWord) {
        for (int i = 0; i < imageViews.length; i++) {
            Glide.with(this)
                    .load(nextWord.getPictures()[i].getResolvedUrl())
                    .fitCenter()
                    .into(imageViews[i]);

            imageViews[i].setTag(nextWord.getPictures()[i]);
        }
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

        if(choice) {
            intent = new Intent(GameActivity.this, CorrectAnswerActivity.class);
        }
        else {
            intent = new Intent(GameActivity.this, WrongAnswerActivity.class);
        }

        intent.putExtra(BaseAnswerActivity.EXTRA_WORD, word);
        intent.putExtra(BaseAnswerActivity.EXTRA_URL, selectedPictureUrl);
        intent.putExtra(BaseAnswerActivity.EXTRA_PROGRESS_CUR, responseProgress.getCurrent());
        intent.putExtra(BaseAnswerActivity.EXTRA_PROGRESS_MAX, responseProgress.getMax());

        startActivity(intent);
        finish();
    }
}

