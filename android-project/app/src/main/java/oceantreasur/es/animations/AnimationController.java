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
import oceantreasur.es.view.ScreenUtils;

import static oceantreasur.es.R.dimen.big_fish_height;
import static oceantreasur.es.R.dimen.small_fish_height;
import static oceantreasur.es.R.dimen.small_fish_width;
import static oceantreasur.es.R.layout.big_fish;
import static oceantreasur.es.R.layout.small_fish;
import static oceantreasur.es.animations.AnimationConstants.DURATION_OF_BIG_FISH_ANIMATION;
import static oceantreasur.es.animations.AnimationConstants.MAX_BIG_FISHES;
import static oceantreasur.es.animations.AnimationConstants.MAX_DURATION;
import static oceantreasur.es.animations.AnimationConstants.MAX_SMALL_FISH;
import static oceantreasur.es.animations.AnimationConstants.MAX_TIME_OFFSET_BIG_FISH;
import static oceantreasur.es.animations.AnimationConstants.MAX_TIME_OFFSET_SMALL_FISH;
import static oceantreasur.es.animations.AnimationConstants.MIN_DURATION;
import static oceantreasur.es.animations.AnimationConstants.MIN_TIME_OFFSET_BIG_FISH;
import static oceantreasur.es.animations.AnimationConstants.MIN_TIME_OFFSET_SMALL_FISH;
import static oceantreasur.es.view.ScreenUtils.getPxFromDp;


public class AnimationController {

    private ArrayList<View> smallFishViews = new ArrayList<>();
    private ArrayList<View> bigFishViews = new ArrayList<>();
    private int smallFishBeginOffset = getPxFromDp(small_fish_width);
    private int bigFishBeginOffset = getPxFromDp(R.dimen.big_fish_width);
    private int screenHeight = ScreenUtils.getScreenHeight();

    public AnimationController(RelativeLayout background) {
        inflateFishViews(background);
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

    public void animateFishes() {
        animateFishArray(smallFishViews,smallFishBeginOffset, true);
        animateFishArray(bigFishViews, bigFishBeginOffset, false);
    }

    private void animateFishArray(ArrayList<View> views, int positionOffset, boolean isSmallFish) {
        int startTimeOffset;
        int duration;

        for(int i = 0; i < views.size(); i++) {

            if(isSmallFish) {
                duration = generateRandomIntegerInRange(MIN_DURATION, MAX_DURATION);
                startTimeOffset = generateRandomIntegerInRange(MIN_TIME_OFFSET_SMALL_FISH, MAX_TIME_OFFSET_SMALL_FISH);
            } else {
                startTimeOffset = generateRandomIntegerInRange(MIN_TIME_OFFSET_BIG_FISH, MAX_TIME_OFFSET_BIG_FISH);
                duration =  DURATION_OF_BIG_FISH_ANIMATION;
            }

            animateSingleFish(views.get(i), duration, positionOffset, startTimeOffset, isSmallFish);
        }
    }

    private int chooseRandomYPosition() {
            return generateRandomIntegerInRange(getPxFromDp(small_fish_height) / 2, screenHeight - getPxFromDp(small_fish_height));
    }

    private void animateSingleFish(final View fish, final int durationTime, final int positionOffset, final int startTimeOffset, final boolean isSmallFish) {
        int width = ScreenUtils.getScreenWidth();
        int height;

        if(isSmallFish) {
            height = chooseRandomYPosition();
        } else {
            height = screenHeight / 2  - getPxFromDp(big_fish_height) / 2;
        }

        Animation anim = new TranslateAnimation(-positionOffset, width + positionOffset, height, height);
        anim.setFillAfter(true);
        anim.setStartOffset(startTimeOffset);
        anim.setDuration(durationTime);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                animateSingleFish(fish, durationTime, positionOffset, startTimeOffset, isSmallFish);
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
