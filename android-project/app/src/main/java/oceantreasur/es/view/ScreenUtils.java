package oceantreasur.es.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.ImageView;

import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;

/**
 * Created by stoyan-ivanov on 24.07.17.
 */

public class ScreenUtils {

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getWidthOfFish () {

        Resources res = OceanTreasuresApplication.getStaticContext().getResources();

        int widthOfFish = (int) (res.getDimension(R.dimen.fish_width) / res.getDisplayMetrics().density);

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthOfFish, res.getDisplayMetrics())) + 10;
    }
}
