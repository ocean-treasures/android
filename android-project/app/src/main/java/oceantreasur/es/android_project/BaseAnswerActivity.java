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

    private ImageView image;
    private oceantreasur.es.android_project.CustomTextView answerMessage;
    private oceantreasur.es.android_project.CustomTextView answerWord;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Bundle intentData = getIntent().getExtras();

        final String imageUrl = intentData.getString("URL");
        final String word =     intentData.getString("WORD");
        final int curProgress = intentData.getInt("PROGRESS_CUR");
        final int maxProgress = intentData.getInt("PROGRESS_MAX");

        setupActivity();
        loadImage(imageUrl);
        setupProgress(curProgress, maxProgress);
        setupAnswerWord(word);
        setupAnswerMessage(getMessage());
        setupImageBackgroundColor(getColor());
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

    public void setupAnswerMessage(String msg) {
        answerMessage.setText(msg);
    }

    public void setupAnswerWord(String word) {
        answerWord.setText(word);
    }

    public void setupImageBackgroundColor(int color) {
        image.setBackgroundColor(ContextCompat.getColor(this, color));
    }


}
