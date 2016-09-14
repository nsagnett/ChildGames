package nsapp.childgames.utils;

import android.text.TextUtils;

public class Strings {

    public static String firstLetterUppercase(String s) {
        if (TextUtils.isEmpty(s)) {
            return s;
        } else {
            s = s.toLowerCase();
            StringBuilder sb = new StringBuilder();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    sb.append(String.valueOf(s.charAt(i)).toUpperCase());
                } else {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
    }
}
