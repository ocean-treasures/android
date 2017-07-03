package oceantreasur.es.android_project;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Typeface playButtonTypeFace;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.playButton = (Button) findViewById(R.id.test1);
        this.playButtonTypeFace = Typeface.createFromAsset(getAssets(), "fonts/CoolCrayon.ttf");

        playButton.setTypeface(playButtonTypeFace);

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

    }
}
