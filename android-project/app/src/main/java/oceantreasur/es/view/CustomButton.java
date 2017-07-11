package oceantreasur.es.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import oceantreasur.es.R;

import static oceantreasur.es.view.FontManager.applyCustomFont;

public class CustomButton extends android.support.v7.widget.AppCompatButton {

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        manageFont(attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        manageFont(attrs);
    }

    public void manageFont(AttributeSet attrs) {

            int typefaceAsset = R.styleable.CustomButton_typefaceButtonAsset;
            int[] elements = R.styleable.CustomButton;
            applyCustomFont(elements, typefaceAsset, attrs, this);
    }
}
