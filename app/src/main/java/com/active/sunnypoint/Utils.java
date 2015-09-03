package com.active.sunnypoint;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

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

    public static void setBackground(View view, int color) {
        GradientDrawable border = new GradientDrawable();
        border.setColor(0x00000000);
        border.setCornerRadius(5);
        border.setStroke(1, color);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(border);
        } else {
            view.setBackground(border);
        }
    }

}
