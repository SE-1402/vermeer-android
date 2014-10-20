package com.candroid.app.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Helper class for converting specific unit formats
 * (dp -> px, px -> dp, etc)
 */
public class Convert {

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     * Used primarily in new LayoutParams(width, height) since it requires pixels.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return An int value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        final float scale = metrics.density;
        // Doubling the output in order to better see the objects
        // TODO: Remove 2 * from code once done.
        return 2 * (int) (dp * scale + 0.5f);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    /**
     * This method converts device specfic pixels to scaled pixels for fonts.
     *
     * @param context
     * @param px
     * @return
     */
    public static float pixelsToSp(Context context, Float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }

    public static boolean isNumeric(String str) {
        try {
            int integer = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
