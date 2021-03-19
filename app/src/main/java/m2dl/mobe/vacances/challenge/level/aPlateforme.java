package m2dl.mobe.vacances.challenge.level;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;


import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;

public abstract class aPlateforme implements Drawable {

    private RectF rectangle;
    Paint fillPaint = new Paint();

    public aPlateforme(int left , int top, int right, int bottom) {
        this.rectangle = new RectF(left,
                top,
                right,
                bottom);
    }

    void initPaints() {

        // fill
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.YELLOW);

    }

    @Override
    public int getZIndex() {
        return 1;
    }

    @Override
    public void draw(Canvas canvas) {
        initPaints();
        canvas.drawRect(rectangle, fillPaint);

    }

    public abstract void doSomething();

}
