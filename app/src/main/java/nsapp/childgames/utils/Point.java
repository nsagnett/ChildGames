package nsapp.childgames.utils;

public class Point {
    private float x, y;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }


}
