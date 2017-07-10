package oceantreasur.es.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import oceantreasur.es.R;

public class CustomButton extends android.support.v7.widget.AppCompatButton {

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);

        applyCustomFont(ta, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);

        applyCustomFont(ta, attrs);
    }

    public void applyCustomFont(TypedArray ta, AttributeSet attrs) {

        if (ta != null) {
            String fontAsset = ta.getString(R.styleable.CustomButton_typefaceButtonAsset);

            if (!(fontAsset == null || fontAsset.length() == 0)) {
                Typeface tf = FontManager.getInstance().getFont(fontAsset);

                if (tf != null)
                    this.setTypeface(tf);
                else
                    Log.d("FontText", String.format("Could not create a font from asset: %s", fontAsset));
            }
        }
    }
}
