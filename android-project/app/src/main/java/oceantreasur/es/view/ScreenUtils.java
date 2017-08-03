package oceantreasur.es.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.ImageView;

import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;


public class ScreenUtils {

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getDimensionOfFishInPx(int fishId) {

        Resources res = OceanTreasuresApplication.getStaticContext().getResources();

        int widthOfFish = (int) (res.getDimension(fishId) / res.getDisplayMetrics().density);

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthOfFish, res.getDisplayMetrics())) + 10;
    }

}
