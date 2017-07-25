package oceantreasur.es;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

import oceantreasur.es.network.OceanTreasuresApplication;
import oceantreasur.es.view.CustomButton;
import oceantreasur.es.view.ViewUtils;

import static oceantreasur.es.R.layout.fish;
import static oceantreasur.es.view.AnimationConstants.*;
import static oceantreasur.es.view.ScreenUtils.*;


public class EndGameActivity extends BaseActivity {

    private ImageView image;
    private CustomButton button;
    private ArrayList<View> fishViews = new ArrayList<>();

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
            inflateFishViews();
            moveAnimation();
        }
    }

    private void inflateFishViews() {
        RelativeLayout mainlayout = (RelativeLayout) findViewById(R.id.rl_end);

        LayoutInflater Li = LayoutInflater.from(getApplicationContext());

        for(int i = 0; i < MAX_FISH; i++) {
            fishViews.add(Li.inflate(fish,null));
            mainlayout.addView(fishViews.get(i));
        }
    }

    private void moveAnimation() {
        for(int i = 0; i < fishViews.size(); i++) {
            setupAnimation(fishViews.get(i), generateRandomIntegerInRange(MIN_DURATION, MAX_DURATION));
        }
    }

    private int choseRandomYPosition() {
        int height = getScreenHeight();

        int yPosition = generateRandomIntegerInRange(1, height);

        if(yPosition < height * MIN_SPAWNING_POINT_IN_PERCENTS) {
            yPosition += FISH_MARGIN_BOTTOM;
        } else {
            if(yPosition > height * MAX_SPAWNING_POINT_IN_PERCENTS) {
                yPosition -= FISH_MARGIN_TOP;
            }
        }

        return yPosition;
    }

    private void setupAnimation(final View fish, final int durationTime) {
        int width = getScreenWidth();
        int height = choseRandomYPosition();

        Animation anim = new TranslateAnimation(-300, width + 400, height, height);
        anim.setDuration(durationTime);
        anim.setStartOffset(generateRandomIntegerInRange(MIN_TIME_OFFSET, MAX_TIME_OFFSET));
        anim.setFillAfter(true);


        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                setupAnimation(fish, durationTime);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fish.startAnimation(anim);
    }


    private int generateRandomIntegerInRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }

    private void getBackToPlayActivity() {
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
