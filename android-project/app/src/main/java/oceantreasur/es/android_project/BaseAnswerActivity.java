package oceantreasur.es.android_project;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

public abstract class BaseAnswerActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "oceantreasur.es.EXTRA_URL";
    public static final String EXTRA_WORD = "oceantreasur.es.EXTRA_WORD";
    public static final String EXTRA_PROGRESS_CUR = "oceantreasur.es.EXTRA_PROGRESS_CUR";
    public static final String EXTRA_PROGRESS_MAX = "oceantreasur.es.EXTRA_PROGRESS_MAX";

    private ImageView image;
    private oceantreasur.es.android_project.CustomTextView answerMessage;
    private oceantreasur.es.android_project.CustomTextView answerWord;
    private ProgressBar progressBar;

    public boolean checkExtras() {
        if     (getIntent().hasExtra(EXTRA_URL) &&
                getIntent().hasExtra(EXTRA_PROGRESS_CUR) &&
                getIntent().hasExtra(EXTRA_PROGRESS_MAX) &&
                getIntent().hasExtra(EXTRA_WORD)) {
            return true;
        }
        else {
            return false;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        setupActivity();

        if (checkExtras()) {
            Bundle intentData = getIntent().getExtras();
            loadImage(intentData.getString(EXTRA_URL));
            setupProgress(intentData.getInt(EXTRA_PROGRESS_CUR), intentData.getInt(EXTRA_PROGRESS_MAX));
            answerWord.setText(intentData.getString(EXTRA_WORD));
        }
        else {
            throw new IllegalArgumentException();
        }

        answerMessage.setText(getMessage());
        image.setBackgroundColor(ContextCompat.getColor(this, getColor()));
    }

    public abstract String getMessage();

    public abstract int getColor();

    public void setupActivity() {
        image = (ImageView) findViewById(R.id.iv_answer_pic);
        answerMessage = (oceantreasur.es.android_project.CustomTextView) findViewById(R.id.tv_answer_msg);
        answerWord = (oceantreasur.es.android_project.CustomTextView) findViewById(R.id.tv_answer_word);
        progressBar = (ProgressBar) findViewById(R.id.pb_answer);
    }

    public void setupProgress(int cur, int max) {
        progressBar.setMax(max);
        progressBar.setProgress(cur);
    }

    public void loadImage(String url) {
        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(url)
                .fitCenter()
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (progressBar.getProgress() != progressBar.getMax()) {
                    intent = new Intent(BaseAnswerActivity.this, GameActivity.class);
                }
                else {
                    intent = new Intent(BaseAnswerActivity.this, EndGameActivity.class);
                }

                startActivity(intent);
                finish();
            }
        });
    }
}
