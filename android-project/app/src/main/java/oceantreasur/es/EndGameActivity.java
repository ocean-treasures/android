package oceantreasur.es;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.view.CustomButton;
import oceantreasur.es.view.ViewUtils;

public class EndGameActivity extends BaseActivity {

    private ImageView image;
    private CustomButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        this.image = (ImageView) findViewById(R.id.iv_end_game);
        this.button = (CustomButton) findViewById(R.id.btn_play_again);

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

        if (ViewUtils.isTablet(this)) {
            ImageView logo = (ImageView) findViewById(R.id.iv_logo_end);
            final ImageView fish1 = (ImageView) findViewById(R.id.fish1);
            final ImageView fish2 = (ImageView) findViewById(R.id.fish2);
            final ImageView chest = (ImageView) findViewById(R.id.chest_end);

            View.OnClickListener animate = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveAnimation(fish1, fish2);
                }
            };

            logo.setOnClickListener(animate);
            chest.setOnClickListener(animate);
        }
    }

    private void moveAnimation(ImageView fish1, ImageView fish2) {
        Animation animation = new TranslateAnimation(Animation.ABSOLUTE, 300, Animation.ABSOLUTE, Animation.ABSOLUTE);
        animation.setDuration(2500);
        animation.setFillAfter(true);

        fish1.startAnimation(animation);
        fish2.startAnimation(animation);
    }

    private void getBackToPlayActivity() {
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
