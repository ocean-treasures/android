package oceantreasur.es.android_project;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AnswerActivity extends AppCompatActivity {

    private boolean isCorrect;
    private ImageView image;
    private oceantreasur.es.android_project.CustomTextView msgToDisplay;
    private oceantreasur.es.android_project.CustomTextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        this.image = (ImageView) findViewById(R.id.iv_pic_to_display);
        this.msgToDisplay = (oceantreasur.es.android_project.CustomTextView) findViewById(R.id.tv_answer_msg);
        this.word = (oceantreasur.es.android_project.CustomTextView) findViewById(R.id.tv_word);

        isCorrect = getIntent().getExtras().getBoolean("ISCORRECT");
        msgToDisplay.setText(getIntent().getExtras().getString("MSG"));
        word.setText(getIntent().getExtras().getString("WORD"));

        if(isCorrect) {
            image.setBackgroundColor(Color.GREEN);
        }
        else {
            image.setBackgroundColor(Color.RED);
        }

        Glide.with(this)
                .load(getIntent().getExtras().get("URL"))
                .fitCenter()
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnswerActivity.this, EndGameActivity.class);
                startActivity(intent);
            }
        });
    }


}
