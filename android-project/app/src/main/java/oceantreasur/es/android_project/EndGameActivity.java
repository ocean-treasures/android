package oceantreasur.es.android_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import oceantreasur.es.android_project.network.OceanTreasuresApplication;
import oceantreasur.es.android_project.view.FontManager;

public class EndGameActivity extends AppCompatActivity {

    private ImageView image;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Typeface buttonTypeFace = FontManager.getInstance().getFont("fonts/CoolCrayon.ttf");

        this.image = (ImageView) findViewById(R.id.iv_end_game);
        this.button = (Button) findViewById(R.id.btn_play_again);

        button.setBackgroundColor(Color.TRANSPARENT);
        button.setTypeface(buttonTypeFace);

        Glide.with(OceanTreasuresApplication.getStaticContext())
                .load(R.drawable.treasure)
                .centerCrop()
                .into(image);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                   getBackToPlayActivity();
            }
        });

    }

    private void getBackToPlayActivity() {
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
