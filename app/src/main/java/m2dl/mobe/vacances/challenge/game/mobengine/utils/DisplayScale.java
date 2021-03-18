package m2dl.mobe.vacances.challenge.game.mobengine.utils;

import android.graphics.Point;
import android.graphics.Rect;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class DisplayScale {
    private static final int BASE_WIDTH = 800;
    private static final int BASE_HEIGHT = 1280;

    private static float scale = 1.0f;

    private static final Rect rect = new Rect(0,0,BASE_WIDTH,BASE_HEIGHT);

    public static void updateScale(Point size) {
        if(size.x == BASE_WIDTH && size.y == BASE_HEIGHT) {
            return;
        }

        scale = Math.min((float) min(size.x, size.y) / (float) BASE_WIDTH, (float) max(size.x, size.y) / (float) BASE_HEIGHT);
        rect.bottom = (int) (size.y/scale);
        rect.right = (int) (size.x/scale);
    }

    public static float getScale() {
        return scale;
    }

    public static Rect getRect() {
        return rect;
    }
}
