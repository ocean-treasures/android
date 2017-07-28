package oceantreasur.es;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.view.CustomButton;
import oceantreasur.es.view.CustomTextView;
import oceantreasur.es.view.ViewUtils;

public abstract class BaseAnswerActivity extends BaseActivity {

    public static final String EXTRA_URL = "oceantreasur.es.EXTRA_URL";
    public static final String EXTRA_WORD = "oceantreasur.es.EXTRA_WORD";
    public static final String EXTRA_PROGRESS_CUR = "oceantreasur.es.EXTRA_PROGRESS_CUR";
    public static final String EXTRA_PROGRESS_MAX = "oceantreasur.es.EXTRA_PROGRESS_MAX";

    private ImageView image;
    private ImageView indicator;
    private CustomTextView answerMessage;
    private CustomTextView answerWord;
    private ProgressBar progressBar;
    private CustomButton nextButton;

    public void checkExtras() throws IllegalArgumentException {
        if (!(getIntent().hasExtra(EXTRA_URL) &&
                getIntent().hasExtra(EXTRA_PROGRESS_CUR) &&
                getIntent().hasExtra(EXTRA_PROGRESS_MAX) &&
                getIntent().hasExtra(EXTRA_WORD))) {
            throw new IllegalArgumentException("Missing extra");
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(oceantreasur.es.R.layout.activity_answer);

        setupActivity();
        checkExtras();
        playMusic();

        Bundle intentData = getIntent().getExtras();
        loadImage(intentData.getString(EXTRA_URL));
        setupProgress(intentData.getInt(EXTRA_PROGRESS_CUR), intentData.getInt(EXTRA_PROGRESS_MAX));
        answerWord.setText(intentData.getString(EXTRA_WORD));
        
        answerMessage.setText(getMessage());

        if (!ViewUtils.isTablet(this)) {
            image.setBackgroundColor(ContextCompat.getColor(this, getColor()));
        }
    }

    public abstract String getMessage();

    public abstract int getColor();

    public abstract int getIndicatorImage();

    View.OnClickListener nextClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            disableNextClick();
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
    };

    public void disableNextClick(){
        nextButton.setClickable(false);
        nextButton.setEnabled(false);
        image.setClickable(false);
        image.setEnabled(false);
    }

    public void setupActivity() {
        image = (ImageView) findViewById(R.id.iv_answer_pic);
        answerMessage = (CustomTextView) findViewById(R.id.tv_answer_msg);
        answerWord = (CustomTextView) findViewById(R.id.tv_answer_word);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        if (ViewUtils.isTablet(this)) {
            indicator = (ImageView) findViewById(R.id.iv_indicator);
            nextButton = (CustomButton) findViewById(R.id.btn_next);

            nextButton.setOnClickListener(nextClick);

            indicator.setImageResource(getIndicatorImage());
        }
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

        image.setOnClickListener(nextClick);
    }

    abstract void playMusic();

}
