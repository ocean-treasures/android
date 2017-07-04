package oceantreasur.es.android_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class GameActivity extends AppCompatActivity {

    private static final int PROGRESS_MAX = 100;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.progressBar.setMax(PROGRESS_MAX);

        progressBar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressBar.setProgress((progressBar.getProgress() + PROGRESS_MAX/10) % PROGRESS_MAX);
            }
        });
    }
}
