package oceantreasur.es.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import oceantreasur.es.R;

import static oceantreasur.es.view.FontManager.applyCustomFont;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        int typefaceAsset = R.styleable.CustomTextView_typefaceAsset;
        int[] elements = R.styleable.CustomTextView;
        applyCustomFont(elements, typefaceAsset, attrs, this);
    }
}
