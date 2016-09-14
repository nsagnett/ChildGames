package nsapp.childgames.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MSurfaceView extends View {

    private final Paint paint = new Paint();
    private ArrayList<Pair<Float, Float>> points = new ArrayList<>();

    public MSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        points.add(new Pair<Float, Float>(500.f * metrics.density, 500.f * metrics.density));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Pair<Float, Float> p : points) {
            canvas.drawCircle(p.first, p.second, 50, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            points.add(new Pair<>(motionEvent.getX(), motionEvent.getY()));
            postInvalidate();
        }
        return true;
    }
}