package oceantreasur.es.android_project;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CorrectAnswerActivity extends AppCompatActivity {

    private ImageView image;
    private oceantreasur.es.android_project.CustomTextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer);

        this.image = (ImageView) findViewById(R.id.iv_correct);
        this.word = (oceantreasur.es.android_project.CustomTextView) findViewById(R.id.tv_wordCorrect);

        word.setText(getIntent().getExtras().get("WORD").toString());

        Glide.with(this)
                .load(getIntent().getExtras().get("URL"))
                .fitCenter()
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CorrectAnswerActivity.this, EndGameActivity.class);
                startActivity(intent);
            }
        });
    }


}
