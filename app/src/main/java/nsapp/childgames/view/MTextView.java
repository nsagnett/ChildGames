package nsapp.childgames.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class MTextView extends TextView {

    public MTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/font.ttf"));
        }
    }
}
