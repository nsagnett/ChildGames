package nsapp.childgames.utils;

import android.content.Context;

import java.io.InputStream;

public class Resources {

    public static String loadJSONFromAsset(Context context, String fileName) {
        String s;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            s = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return s;
    }
}
