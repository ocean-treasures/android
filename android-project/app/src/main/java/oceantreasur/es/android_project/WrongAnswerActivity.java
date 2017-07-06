package oceantreasur.es.android_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class WrongAnswerActivity extends AppCompatActivity {

    private ImageView image;
    private oceantreasur.es.android_project.CustomTextView word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answer);

        this.image = (ImageView) findViewById(R.id.iv_wrong);
        this.word = (oceantreasur.es.android_project.CustomTextView) findViewById(R.id.tv_wrong_word);

        word.setText(getIntent().getExtras().get("WORD").toString());

        Glide.with(this)
                .load(getIntent().getExtras().get("URL"))
                .fitCenter()
                .into(image);
    }
}
