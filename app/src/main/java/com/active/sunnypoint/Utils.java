package com.active.sunnypoint;

import android.graphics.Color;

/**
 * Created by NhanCao on 01-Sep-15.
 */
public class Utils {

    public static int randomColor(int alpha) {
        int r = (int) (0xff * Math.random());
        int g = (int) (0xff * Math.random());
        int b = (int) (0xff * Math.random());

        return Color.argb(alpha, r, g, b);
    }

}
