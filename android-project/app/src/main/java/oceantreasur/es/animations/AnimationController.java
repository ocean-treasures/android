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

import static oceantreasur.es.R.dimen.small_fish_width;
import static oceantreasur.es.R.layout.big_fish;
import static oceantreasur.es.R.layout.small_fish;
import static oceantreasur.es.animations.AnimationConstants.MAX_BIG_FISHES;
import static oceantreasur.es.animations.AnimationConstants.MAX_DURATION;
import static oceantreasur.es.animations.AnimationConstants.MAX_DURATION_OF_BIG_FISH_ANIMATION;
import static oceantreasur.es.animations.AnimationConstants.MAX_SMALL_FISH;
import static oceantreasur.es.animations.AnimationConstants.MAX_TIME_OFFSET_BIG_FISH;
import static oceantreasur.es.animations.AnimationConstants.MAX_TIME_OFFSET_SMALL_FISH;
import static oceantreasur.es.animations.AnimationConstants.MIN_DURATION;
import static oceantreasur.es.animations.AnimationConstants.MIN_DURATION_OF_BIG_FISH_ANIMATION;
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

    private static class AnimationTiming {
        private int minDuration;
        private int maxDuration;
        private int minTimeOffset;
        private int maxTimeOffset;

        AnimationTiming(int minDuration, int maxDuration, int minTimeOffset, int maxTimeOffset) {
            this.minDuration = minDuration;
            this.maxDuration = maxDuration;
            this.minTimeOffset = minTimeOffset;
            this.maxTimeOffset = maxTimeOffset;
        }

        public int getMinDuration() {
            return minDuration;
        }

        public int getMaxDuration() {
            return maxDuration;
        }

        public int getMinTimeOffset() {
            return minTimeOffset;
        }

        public int getMaxTimeOffset() {
            return maxTimeOffset;
        }
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
        animateFishArray(smallFishViews,smallFishBeginOffset, new AnimationTiming(MIN_DURATION, MAX_DURATION,
                                                            MIN_TIME_OFFSET_SMALL_FISH, MAX_TIME_OFFSET_SMALL_FISH));

        animateFishArray(bigFishViews, bigFishBeginOffset, new AnimationTiming(MIN_DURATION_OF_BIG_FISH_ANIMATION,
                                                            MAX_DURATION_OF_BIG_FISH_ANIMATION, MIN_TIME_OFFSET_BIG_FISH, MAX_TIME_OFFSET_BIG_FISH));
    }

    private void animateFishArray(ArrayList<View> views, int positionOffset, AnimationTiming animTiming) {
        int startTimeOffset;
        int duration;

        for(int i = 0; i < views.size(); i++) {
            duration = generateRandomIntegerInRange(animTiming.getMinDuration(), animTiming.getMaxDuration());
            startTimeOffset = generateRandomIntegerInRange(animTiming.getMinTimeOffset(), animTiming.getMaxTimeOffset());

            animateSingleFish(views.get(i), duration, positionOffset, startTimeOffset);
        }
    }

    private int chooseRandomYPosition(int heightOfTheFish) {
            return generateRandomIntegerInRange(1, screenHeight - heightOfTheFish - 50);
    }

    private void animateSingleFish(final View fish, final int durationTime, final int positionOffset, final int startTimeOffset) {
        int width = ScreenUtils.getScreenWidth();
        fish.measure( View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        int height = chooseRandomYPosition(fish.getMeasuredHeight());

        Animation anim = new TranslateAnimation(-positionOffset, width + positionOffset, height, height);
        anim.setFillAfter(true);
        anim.setStartOffset(startTimeOffset);
        anim.setDuration(durationTime);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                animateSingleFish(fish, durationTime, positionOffset, startTimeOffset);
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
