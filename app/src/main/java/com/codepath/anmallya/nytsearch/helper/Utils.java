package com.codepath.anmallya.nytsearch.helper;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by anmallya on 10/23/2016.
 */
public class Utils {
    public static int convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int)px;
    }
}
