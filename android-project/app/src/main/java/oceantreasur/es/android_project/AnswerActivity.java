package oceantreasur.es.android_project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import oceantreasur.es.android_project.view.CustomTextView;

public class AnswerActivity extends AppCompatActivity {

    private boolean isCorrect;
    private ImageView image;
    private CustomTextView msgToDisplay;
    private CustomTextView word;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        this.image = (ImageView) findViewById(R.id.iv_pic_to_display);
        this.msgToDisplay = (CustomTextView) findViewById(R.id.tv_answer_msg);
        this.word = (CustomTextView) findViewById(R.id.tv_word);
        this.progressBar = (ProgressBar) findViewById(R.id.pb_answer);

        Bundle intentData = getIntent().getExtras();

        isCorrect = intentData.getBoolean("IS_CORRECT");
        word.setText(intentData.getString("WORD"));
        progressBar.setMax(intentData.getInt("PROGRESS_MAX"));
        progressBar.setProgress(intentData.getInt("PROGRESS_CUR"));

        if(isCorrect) {
            image.setBackgroundColor(Color.GREEN);
            msgToDisplay.setText("Correct answer!");
        }
        else {
            image.setBackgroundColor(Color.RED);
            msgToDisplay.setText("Wrong answer!");
        }

        Glide.with(this)
                .load(getIntent().getExtras().get("URL"))
                .fitCenter()
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (progressBar.getProgress() != progressBar.getMax()) {
                    intent = new Intent(AnswerActivity.this, GameActivity.class);
                }
                else {
                    intent = new Intent(AnswerActivity.this, EndGameActivity.class);
                }

                startActivity(intent);
                finish();
            }
        });
    }


}
