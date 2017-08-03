package oceantreasur.es.animations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;

import static oceantreasur.es.R.dimen.small_fish_height;
import static oceantreasur.es.R.dimen.small_fish_width;
import static oceantreasur.es.R.layout.big_fish;
import static oceantreasur.es.R.layout.small_fish;
import static oceantreasur.es.animations.AnimationConstants.DURATION_OF_BIG_FISH_ANIMATION;
import static oceantreasur.es.animations.AnimationConstants.FISH_MARGIN_BOTTOM;
import static oceantreasur.es.animations.AnimationConstants.FISH_MARGIN_TOP;
import static oceantreasur.es.animations.AnimationConstants.MAX_BIG_FISHES;
import static oceantreasur.es.animations.AnimationConstants.MAX_DURATION;
import static oceantreasur.es.animations.AnimationConstants.MAX_SMALL_FISH;
import static oceantreasur.es.animations.AnimationConstants.MAX_SPAWNING_POINT_IN_PERCENTS;
import static oceantreasur.es.animations.AnimationConstants.MAX_TIME_OFFSET_BIG_FISH;
import static oceantreasur.es.animations.AnimationConstants.MAX_TIME_OFFSET_SMALL_FISH;
import static oceantreasur.es.animations.AnimationConstants.MIN_DURATION;
import static oceantreasur.es.animations.AnimationConstants.MIN_SPAWNING_POINT_IN_PERCENTS;
import static oceantreasur.es.animations.AnimationConstants.MIN_TIME_OFFSET_BIG_FISH;
import static oceantreasur.es.animations.AnimationConstants.MIN_TIME_OFFSET_SMALL_FISH;
import static oceantreasur.es.animations.AnimationConstants.SPAWNING_POINT_OF_BIG_FISH;
import static oceantreasur.es.view.ScreenUtils.getDimensionOfFishInPx;
import static oceantreasur.es.view.ScreenUtils.getScreenHeight;
import static oceantreasur.es.view.ScreenUtils.getScreenWidth;


public class AnimationController {

    private ArrayList<View> smallFishViews = new ArrayList<>();
    private ArrayList<View> bigFishViews = new ArrayList<>();
    private int smallFishBeginOffset = getDimensionOfFishInPx(small_fish_width);
    private int bigFishBeginOffset = getDimensionOfFishInPx(R.dimen.big_fish_width);

    public AnimationController(RelativeLayout background) {
        inflateFishViews(background);
        moveAnimation();
    }

    private void inflateFishViews (RelativeLayout background) {

        LayoutInflater Li = LayoutInflater.from(OceanTreasuresApplication.getStaticContext());

        for(int i = 0; i < MAX_SMALL_FISH; i++) {
            smallFishViews.add(Li.inflate(small_fish, null));
            background.addView(smallFishViews.get(i));
        }

        for(int i = 0; i < MAX_BIG_FISHES; i++) {
            bigFishViews.add(Li.inflate(big_fish, null));
            background.addView(bigFishViews.get(i));
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
        int height;
        boolean isSmallFish = true;

        if(smallFishBeginOffset ==  beginOffset) {
            height = choseRandomYPosition();
        } else {
            isSmallFish = false;
            height = SPAWNING_POINT_OF_BIG_FISH;
        }

        Animation anim = new TranslateAnimation(-beginOffset, width + beginOffset, height, height);
        anim.setFillAfter(true);

        if(isSmallFish) {
            anim.setStartOffset(generateRandomIntegerInRange(MIN_TIME_OFFSET_SMALL_FISH, MAX_TIME_OFFSET_SMALL_FISH));
            anim.setDuration(durationTime);
        } else {
            anim.setStartOffset(generateRandomIntegerInRange(MIN_TIME_OFFSET_BIG_FISH, MAX_TIME_OFFSET_BIG_FISH));
            anim.setDuration(DURATION_OF_BIG_FISH_ANIMATION);
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
}
