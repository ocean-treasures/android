package oceantreasur.es.view;

import android.widget.ImageView;

import static oceantreasur.es.view.ScreenUtils.getScreenHeight;

/**
 * Created by stoyan-ivanov on 24.07.17.
 */

public class AnimationConstants {

    public static final int MAX_SMALL_FISH = 5;
    public static final int MAX_BIG_FISHES = 1;

    public static final int MAX_DURATION = 14000;
    public static final int MIN_DURATION = 8000;

    public static final int MIN_TIME_OFFSET_SMALL_FISH = 100;
    public static final int MAX_TIME_OFFSET_SMALL_FISH = 500;

    public static final int MIN_TIME_OFFSET_BIG_FISH = 1500;
    public static final int MAX_TIME_OFFSET_BIG_FISH = 2000;
    public static final int DURATION_OF_BIG_FISH_ANIMATION = 22000;
    public static final int SPAWNING_POINT_OF_BIG_FISH = getScreenHeight() / 9;

    public static final double MIN_SPAWNING_POINT_IN_PERCENTS= 0.05;
    public static final double MAX_SPAWNING_POINT_IN_PERCENTS = 0.90;

    public static final int FISH_MARGIN_BOTTOM = 50;
    public static final int FISH_MARGIN_TOP = 50;
}
