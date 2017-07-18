package oceantreasur.es;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import oceantreasur.es.network.model.CheckAnswerResponse;
import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.network.model.CheckAnswerRequest;
import oceantreasur.es.network.model.NextWordResponse;
import oceantreasur.es.network.model.Picture;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends BaseActivity {

    private static final int STEP_SIZE = 10;
    private boolean isActivityAlive = true;
    private long mLastClickTime = 0;

    private String selectedPictureUrl;

    private ProgressBar progressBar;

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
        isActivityAlive = true;

        setupActivity();
        getNextWord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActivityAlive = false;
    }

    public void setupActivity() {
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.word = (TextView) findViewById(R.id.tv_word);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = (ImageView) findViewById(ids[i]);
        }
    }

      public void getNextWord() {
        Call<NextWordResponse> call = OceanTreasuresApplication.getApi().getNextWord();

        call.enqueue(new Callback<NextWordResponse>() {
            @Override
            public void onResponse(Call<NextWordResponse> call, Response<NextWordResponse> response) {
                nextWord = response.body();

                if (nextWord.getProgress().getCurrent() == nextWord.getProgress().getMax()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);

                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(GameActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    final AlertDialog dialog = builder.create();
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogLayout = inflater.inflate(R.layout.alert_dialog, null);
                    dialog.setView(dialogLayout);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.show();

                    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface d) {
                            Context context = OceanTreasuresApplication.getStaticContext();
                            ImageView image = (ImageView) dialog.findViewById(R.id.iv_dialog);

                            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                                    R.drawable.fish);

                            float imageWidthInPX = (float)image.getWidth();

                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round(imageWidthInPX),
                                    Math.round(imageWidthInPX * (float)icon.getHeight() / (float)icon.getWidth()));

                            image.setLayoutParams(layoutParams);
                        }
                    });
                } else {
                    if(isActivityAlive) {
                        loadImages(nextWord);
                        setupProgressBar(nextWord.getProgress().getCurrent(), nextWord.getProgress().getMax());
                        word.setText(nextWord.getWord().getWord().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<NextWordResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");
            }
        });
    }

    public void checkAnswer(int wordId, int picId) {
        CheckAnswerRequest req = new CheckAnswerRequest(wordId, picId);
        Call<CheckAnswerResponse> call = OceanTreasuresApplication.getApi().checkAnswer(req);

        call.enqueue(new Callback<CheckAnswerResponse>() {
            @Override
            public void onResponse(Call<CheckAnswerResponse> call, Response<CheckAnswerResponse> response) {
                CheckAnswerResponse serverResponse = response.body();

                chooseNextActivity(serverResponse);

                Log.d("ZAX", serverResponse.toString());
            }

            @Override
            public void onFailure(Call<CheckAnswerResponse> call, Throwable t) {
                Log.d("ZAX", "ERROR");
            }
        });
    }

    public void setupProgressBar(int cur, int max) {
        progressBar.setMax(max * STEP_SIZE);
        progressBar.setProgress(cur * STEP_SIZE);
    }

    public void loadImages(final NextWordResponse nextWord) {
        View.OnClickListener imageOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Picture pic = (Picture) v.getTag();
                selectedPictureUrl = pic.getResolvedUrl();
                checkAnswer(nextWord.getWord().getId(), pic.getId());
            }
        };


        for (int i = 0; i < imageViews.length; i++) {
            Glide.with(this)
                    .load(nextWord.getPictures()[i].getResolvedUrl())
                    .fitCenter()
                    .into(imageViews[i]);

            imageViews[i].setTag(nextWord.getPictures()[i]);
            imageViews[i].setOnClickListener(imageOnClickListener);
        }
    }

    private void chooseNextActivity(CheckAnswerResponse response) {
        Intent intent;

        if(response.isCorrect()) {
            intent = new Intent(GameActivity.this, CorrectAnswerActivity.class);
        }
        else {
            intent = new Intent(GameActivity.this, WrongAnswerActivity.class);
        }

        intent.putExtra(BaseAnswerActivity.EXTRA_WORD, response.getWord());
        intent.putExtra(BaseAnswerActivity.EXTRA_URL, selectedPictureUrl);
        intent.putExtra(BaseAnswerActivity.EXTRA_PROGRESS_CUR, response.getProgress().getCurrent());
        intent.putExtra(BaseAnswerActivity.EXTRA_PROGRESS_MAX, response.getProgress().getMax());

        startActivity(intent);
        finish();
    }
}

