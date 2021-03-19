package m2dl.mobe.vacances.challenge.game.platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;

public abstract class Platform implements Drawable {

    private final Rect rectangle;
    Paint fillPaint = new Paint();

    public Platform(int left , int top, int right, int bottom) {
        this.rectangle = new Rect(left,
                top,
                right,
                bottom);
        fillPaint.setColor(Color.YELLOW);
    }


    @Override
    public int getZIndex() {
        return 1;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rectangle, fillPaint);
    }

}

