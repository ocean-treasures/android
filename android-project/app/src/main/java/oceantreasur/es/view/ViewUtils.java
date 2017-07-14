package oceantreasur.es.view;

import android.content.Context;

import oceantreasur.es.R;

public class ViewUtils {
    public static boolean isTablet(Context context){
        return context.getResources().getBoolean(R.bool.isTablet);
    }
}
