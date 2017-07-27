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

import static oceantreasur.es.R.dimen.small_fish_height;
import static oceantreasur.es.R.dimen.small_fish_width;
import static oceantreasur.es.R.layout.big_fish;
import static oceantreasur.es.R.layout.small_fish;
import static oceantreasur.es.view.AnimationConstants.*;
import static oceantreasur.es.view.ScreenUtils.*;


public class EndGameActivity extends BaseActivity {

    private ImageView image;
    private CustomButton button;
    private ArrayList<View> smallFishViews = new ArrayList<>();
    private ArrayList<View> bigFishViews = new ArrayList<>();
    private int smallFishBeginOffset;
    private int bigFishBeginOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        smallFishBeginOffset = getDimensionOfFishInPx(small_fish_width);
        bigFishBeginOffset = getDimensionOfFishInPx(R.dimen.big_fish_width);


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
        RelativeLayout background = (RelativeLayout) findViewById(R.id.background);
        RelativeLayout foreground = (RelativeLayout) findViewById(R.id.foreground);

        RelativeLayout [] relativeLayouts = {background, foreground};

        LayoutInflater Li = LayoutInflater.from(getApplicationContext());

        for(int i = 0; i < MAX_SMALL_FISH; i++) {
            smallFishViews.add(Li.inflate(small_fish, null));
            relativeLayouts[generateRandomIntegerInRange(0,relativeLayouts.length)].addView(smallFishViews.get(i));
        }

        for(int i = 0; i < MAX_BIG_FISHES; i++) {
            bigFishViews.add(Li.inflate(big_fish, null));
            relativeLayouts[0].addView(bigFishViews.get(i));
        }
    }

    private void moveAnimation() {
        setupAnimation(smallFishViews, smallFishBeginOffset);
        setupAnimation(bigFishViews, bigFishBeginOffset);
    }

    private void setupAnimation(ArrayList<View> views, int offset) {
        for(int i = 0; i < views.size(); i++) {
            startAnimation(views.get(i), generateRandomIntegerInRange(MIN_DURATION, MAX_DURATION), offset);
        }
    }

    private int choseRandomYPosition() {
        int height = getScreenHeight();

        int yPosition = generateRandomIntegerInRange(1, height);

        if(yPosition > height * MAX_SPAWNING_POINT_IN_PERCENTS) {
            yPosition -= (getDimensionOfFishInPx(small_fish_height) + FISH_MARGIN_BOTTOM);
        } else {
            if(yPosition < height * MIN_SPAWNING_POINT_IN_PERCENTS) {
                yPosition += (getDimensionOfFishInPx(small_fish_height) - FISH_MARGIN_TOP);
            }
        }

        return yPosition;
    }

    private void startAnimation(final View fish, final int durationTime, final int beginOffset) {
        int width = getScreenWidth();
        int height = 0;
        boolean isSmallFish = true;

        if(smallFishBeginOffset ==  beginOffset) {
            height = choseRandomYPosition();
        } else {
            isSmallFish = false;
        }

        Animation anim = new TranslateAnimation(-beginOffset, width + beginOffset, height, height);
        anim.setDuration(durationTime);
        anim.setFillAfter(true);

        if(isSmallFish) {
            anim.setStartOffset(generateRandomIntegerInRange(MIN_TIME_OFFSET_SMALL_FISH, MAX_TIME_OFFSET_SMALL_FISH));
        } else {
            anim.setStartOffset(generateRandomIntegerInRange(MIN_TIME_OFFSET_BIG_FISH, MAX_TIME_OFFSET_BIG_FISH));
        }

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                startAnimation(fish, durationTime, beginOffset);
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
