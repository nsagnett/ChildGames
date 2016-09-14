package nsapp.childgames.utils;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nsapp.childgames.view.MTextView;

public class Views {

    public static ArrayList<View> getMTextViews(ViewGroup vg) {
        ArrayList<View> views = new ArrayList<>();
        searchMTextViews(vg, views);
        return views;
    }

    private static void searchMTextViews(ViewGroup vg, ArrayList<View> views) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View v = vg.getChildAt(i);
            if (v instanceof MTextView) {
                views.add(v);
            } else if (v instanceof ViewGroup) {
                searchMTextViews((ViewGroup) v, views);
            }
        }
    }
}
