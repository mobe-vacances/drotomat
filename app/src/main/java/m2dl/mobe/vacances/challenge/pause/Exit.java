package m2dl.mobe.vacances.challenge.pause;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.GameConstants;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.BitmapStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;

public class Exit implements Drawable {

    private static final int LEFT = DisplayScale.getRect().width() - 110;
    private static final int TOP = 0;
    private static final int WIDTH =  100;
    private static final int HEIGHT = 100;

    static Rect rect;

    @Override
    public int getZIndex() {
        return GameConstants.LEVEL_Z_INDEX;
    }

    public Exit(){
        rect = new Rect(LEFT, TOP, LEFT + WIDTH, TOP + HEIGHT);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                Bitmap.createScaledBitmap(BitmapStore.getBitmap(R.drawable.exit), WIDTH, HEIGHT, false),
                LEFT,
                TOP,
                null
        );
    }


    public static boolean intersectsWithPause(int x, int y) {
        return rect.contains(x, y);
    }

    public static void pauseGame(){
        GameEngine.pause();
    }
}
