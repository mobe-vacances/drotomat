package m2dl.mobe.vacances.challenge.interaction;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.BitmapStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;

public class Press implements Drawable {

    private static final int WIDTH =  DisplayScale.getRect().width() /2;
    private static final int HEIGHT = DisplayScale.getRect().width() /2;
    private static final int LEFT = (DisplayScale.getRect().width() /2) - (WIDTH/2);
    private static final int TOP = (DisplayScale.getRect().height() /2) - (HEIGHT);

    static Rect rect;
    public static boolean visible;
    private Paint headerPaint;

    @Override
    public int getZIndex() {
        return Constants.LEVEL_Z_INDEX;
    }

    public Press(){
        rect = new Rect(LEFT, TOP, LEFT + WIDTH, TOP + HEIGHT);
        visible = true;
    }

    @Override
    public void draw(Canvas canvas) {
        if(visible) {

            headerPaint = new Paint();
            headerPaint.setColor(Color.rgb(244, 252, 214
            ));
            headerPaint.setAlpha(200);
            canvas.drawRect(
                    0,
                    0,
                    DisplayScale.getRect().width(),
                    DisplayScale.getRect().height(),
                    headerPaint);

            canvas.drawBitmap(
                    Bitmap.createScaledBitmap(BitmapStore.getBitmap(R.drawable.press), WIDTH, HEIGHT, false),
                    LEFT,
                    TOP,
                    null
            );

        }
    }


    public static boolean intersectsWithPause(int x, int y) {
        return rect.contains(x, y);
    }

    public static void pauseGame(){
        GameEngine.pause();
    }
}
