package oceantreasur.es.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class GameActivity extends AppCompatActivity {

    private static final int PROGRESS_MAX = 100;

    private ProgressBar progressBar;
    private ImageButton topLeft;
    private ImageButton topRight;
    private ImageButton bottomLeft;
    private ImageButton bottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar.setMax(PROGRESS_MAX);

        this.topLeft = (ImageButton) findViewById(R.id.button1);
        this.topRight = (ImageButton) findViewById(R.id.button2);
        this.bottomLeft = (ImageButton) findViewById(R.id.button3);
        this.bottomRight = (ImageButton) findViewById(R.id.button4);

        progressBar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressBar.setProgress((progressBar.getProgress() + PROGRESS_MAX / 10) % PROGRESS_MAX);
            }
        });
    }

    public void onClick(View v) {
            v.getId();
    }

    private void ChooseNextActivity(boolean choice) {
        if(choice) {
            Intent intent = new Intent(GameActivity.this, CorrectAnswerActivity.class);
            intent.putExtra("url", )
        }
    }
}

