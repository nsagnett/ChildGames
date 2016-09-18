package nsapp.childgames.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import nsapp.childgames.utils.OnTerminatedGameListener;

public class MErrorsGameSurfaceView extends View {

    private static final int RADIUS = 70;

    private int errorsCount;
    private final Paint paint = new Paint();
    private ArrayList<Pair<Float, Float>> points = new ArrayList<>();
    private OnTerminatedGameListener onTerminatedGameListener;

    public void setOnTerminatedGameListener(OnTerminatedGameListener onTerminatedGameListener) {
        this.onTerminatedGameListener = onTerminatedGameListener;
    }

    public void setErrorsCount(int errorsCount) {
        this.errorsCount = errorsCount;
    }

    public void removeLastPoint() {
        points.remove(points.size() - 1);
        postInvalidate();
    }

    public MErrorsGameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MErrorsGameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MErrorsGameSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(16);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Pair<Float, Float> p : points) {
            canvas.drawCircle(p.first, p.second, RADIUS, paint);
        }
        if (points.size() == errorsCount) {
            onTerminatedGameListener.onTerminatedGame();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float eventX = motionEvent.getX();
        float eventY = motionEvent.getY();
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && points.size() <= errorsCount && checkPointValidity(eventX, eventY)) {
            points.add(new Pair<>(eventX, eventY));
            postInvalidate();
        }
        return true;
    }

    private boolean checkPointValidity(float x, float y) {
        for (Pair<Float, Float> point : points) {
            if (x > point.first - RADIUS && x < point.first + RADIUS
                    && y > point.second - RADIUS && y < point.second + RADIUS)
                return false;
        }
        return true;
    }
}