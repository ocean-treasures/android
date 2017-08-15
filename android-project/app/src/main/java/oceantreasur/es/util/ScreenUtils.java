package oceantreasur.es.util;

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

    public static int getPxFromDp(int id) {

        Resources res = OceanTreasuresApplication.getStaticContext().getResources();

        int dimension = (int) (res.getDimension(id) / res.getDisplayMetrics().density);

        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimension, res.getDisplayMetrics()));
    }

}
