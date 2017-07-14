package oceantreasur.es;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.view.CustomButton;
import oceantreasur.es.view.FontManager;

public class EndGameActivity extends BaseActivity {

    private ImageView image;
    private CustomButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);


        this.image = (ImageView) findViewById(R.id.iv_end_game);
        this.button = (CustomButton) findViewById(R.id.btn_play_again);

        //button.setBackgroundColor(Color.TRANSPARENT);

        if(image != null) {
            Glide.with(OceanTreasuresApplication.getStaticContext())
                    .load(R.drawable.treasure)
                    .centerCrop()
                    .into(image);
        }
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
