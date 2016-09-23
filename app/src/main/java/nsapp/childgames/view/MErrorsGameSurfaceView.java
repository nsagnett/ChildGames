package nsapp.childgames.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import nsapp.childgames.utils.OnTerminatedGameListener;
import nsapp.childgames.utils.Point;

public class MErrorsGameSurfaceView extends View {

    private static final int RADIUS = 70;

    private int errorsCount;
    private final Paint paint = new Paint();
    private ArrayList<Point> points = new ArrayList<>();
    private OnTerminatedGameListener onTerminatedGameListener;

    public void setOnTerminatedGameListener(OnTerminatedGameListener onTerminatedGameListener) {
        this.onTerminatedGameListener = onTerminatedGameListener;
    }

    public void setErrorsCount(int errorsCount) {
        this.errorsCount = errorsCount;
    }

    public void removeLastPoint() {
        if (!points.isEmpty()) {
            points.remove(points.size() - 1);
            postInvalidate();
        }
    }

    public void removeSelectedPoint() {
        boolean done = false;
        int length = points.size();
        int i = 0;
        while (!done && i < length) {
            if (points.get(i).isSelected()) {
                points.remove(i);
                done = true;
            } else {
                i++;
            }
        }
        if (done) {
            postInvalidate();
        }
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
        paint.setStrokeWidth(16);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Point p : points) {
            paint.setColor(p.isSelected() ? Color.RED : Color.BLUE);
            canvas.drawCircle(p.getX(), p.getY(), RADIUS, paint);
        }
        if (points.size() == errorsCount) {
            onTerminatedGameListener.onTerminatedGame();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float eventX = motionEvent.getX();
        float eventY = motionEvent.getY();
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && points.size() < errorsCount) {
            if (isPointSelected(eventX, eventY)) {
                postInvalidate();
            } else {
                unselectPoint();
                points.add(new Point(eventX, eventY));
                postInvalidate();
            }
        }
        return true;
    }

    private boolean isPointSelected(float x, float y) {
        for (Point point : points) {
            if (x > point.getX() - RADIUS && x < point.getX() + RADIUS
                    && y > point.getY() - RADIUS && y < point.getY() + RADIUS) {
                unselectPoint();
                point.setSelected(true);
                return true;
            }
        }
        return false;
    }

    private void unselectPoint() {
        for (Point p : points) {
            if (p.isSelected()) {
                p.setSelected(false);
                break;
            }
        }
    }
}