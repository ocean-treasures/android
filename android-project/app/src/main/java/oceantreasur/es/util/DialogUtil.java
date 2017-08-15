package oceantreasur.es.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import oceantreasur.es.R;
import oceantreasur.es.network.OceanTreasuresApplication;

/**
 * Created by Dean Panayotov on 8/15/2017
 */

public class DialogUtil {

    public static AlertDialog getNeutralImageAlertDialog(int buttonTextId, int imageId,
                                                          DialogInterface.OnClickListener
                                                                  listener, final
                                                         Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setNeutralButton(activity.getString(buttonTextId), listener);

        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.image_alert_dialog_content, null);
        dialog.setView(dialogLayout);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface d) {
                ImageView image = (ImageView) dialog.findViewById(R.id.iv_dialog);
                Bitmap icon = BitmapFactory.decodeResource(activity.getResources(), R.drawable.fish);
                float imageWidthInPX = (float) image.getWidth();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(Math.round
                        (imageWidthInPX),
                        Math.round(imageWidthInPX * (float) icon.getHeight() / (float) icon
                                .getWidth()));
                image.setLayoutParams(layoutParams);
            }
        });

        return dialog;
    }
}
