package oceantreasur.es.view;

import android.content.res.Resources;
import android.widget.ImageView;

import oceantreasur.es.R;

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
}
