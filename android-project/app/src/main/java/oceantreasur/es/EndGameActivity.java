package oceantreasur.es;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Random;

import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.view.CustomButton;
import oceantreasur.es.view.ViewUtils;

public class EndGameActivity extends BaseActivity {

    private ImageView image;
    private CustomButton button;
    private boolean isAnimationActive = true;

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

            moveAnimation(fish1, fish2);
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private int choseRandomYPosition() {
        int height = getScreenHeight();
        Random rand = new Random();

        int yPosition = rand.nextInt(height) + 1;

        if(yPosition < height * (5/100)) {
            yPosition += 40;
        } else {
            if(yPosition > height * (95/100)) {
                yPosition -= 20;
            }
        }


        return yPosition;
    }

    private void moveAnimation(ImageView fish1, ImageView fish2) {
        int width = getScreenWidth();
        int heightFishOne = choseRandomYPosition();
        int heightFishTwo = choseRandomYPosition();

        Animation animationFishOne = new TranslateAnimation(Animation.ABSOLUTE, width + 400, heightFishOne, heightFishOne);
        animationFishOne.setDuration(7000);
        animationFishOne.setRepeatMode(Animation.RESTART);
        animationFishOne.setRepeatCount(Animation.INFINITE);
        animationFishOne.setFillAfter(true);

        fish1.startAnimation(animationFishOne);

        Animation animationFishTwo = new TranslateAnimation(Animation.ABSOLUTE - 300, width + 400, heightFishTwo, heightFishTwo);
        animationFishTwo.setDuration(7000);
        animationFishTwo.setRepeatMode(Animation.RESTART);
        animationFishTwo.setRepeatCount(Animation.INFINITE);
        animationFishTwo.setFillAfter(true);
        
        fish2.startAnimation(animationFishTwo);

    }

    private void getBackToPlayActivity() {
        isAnimationActive = false;
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
