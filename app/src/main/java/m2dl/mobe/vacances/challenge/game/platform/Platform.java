package m2dl.mobe.vacances.challenge.game.platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.player.Player;

public abstract class Platform implements Drawable {

    protected final Rect rectangle;
    protected Paint fillPaint = new Paint();
    protected Paint borderPaint = null;

    protected final Player player;

    public Platform(int left , int top, int right, int bottom, Player player) {
        this.rectangle = new Rect(left,
                top,
                right,
                bottom);
        fillPaint.setColor(Color.YELLOW);
        this.player = player;
    }


    @Override
    public int getZIndex() {
        return 1;
    }

    @Override
    public void draw(Canvas canvas) {
        if (Rect.intersects(player.getRect(), rectangle)) {
            player.addCurrentPlatform(this);
        } else {
            player.removeCurrentPlatform(this);
        }

        initPaints();
        canvas.drawRect(rectangle, fillPaint);

        if(borderPaint != null) {
            canvas.drawRect(rectangle, borderPaint);
        }
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public abstract void initPaints();
}

