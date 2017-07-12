package oceantreasur.es;

import android.content.Context;

public class ViewUtils {
    public static boolean isTablet(Context context){
        return context.getResources().getBoolean(R.bool.isTablet);
    }
}
