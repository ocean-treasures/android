package oceantreasur.es;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import oceantreasur.es.R;
import oceantreasur.es.view.FontManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = (Button) findViewById(R.id.test1);
        Typeface playButtonTypeFace = FontManager.getInstance().getFont("fonts/CoolCrayon.ttf");

        playButton.setTypeface(playButtonTypeFace);

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });
    }

    void startGame() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
