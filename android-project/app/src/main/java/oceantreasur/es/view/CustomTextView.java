package oceantreasur.es.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import oceantreasur.es.R;

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode())
            return;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);

        if (ta != null) {
            String fontAsset = ta.getString(R.styleable.CustomTextView_typefaceAsset);

            if (!(fontAsset == null || fontAsset.length() == 0)) {
                Typeface tf = FontManager.getInstance().getFont(fontAsset);
                int style = Typeface.NORMAL;
                float size = getTextSize();

                if (getTypeface() != null)
                    style = getTypeface().getStyle();

                if (tf != null)
                    setTypeface(tf, style);
                else
                    Log.d("FontText", String.format("Could not create a font from asset: %s", fontAsset));
            }
        }
    }
}
