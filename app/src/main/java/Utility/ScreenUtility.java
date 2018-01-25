package Utility;

/**
 * Created by Amin on 20/12/2017.
 */

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtility {

    private float dpWidth;
    private float dpHeight;

    public ScreenUtility(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = activity.getResources().getDisplayMetrics().density;

        dpWidth = outMetrics.widthPixels / density;
        dpHeight = outMetrics.heightPixels / density;

    }

    public float getDpWidth() {
        return dpWidth;
    }

    public float getDpHeight() {
        return dpHeight;
    }

}