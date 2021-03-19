package m2dl.mobe.vacances.challenge.game.platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.player.Player;

public abstract class Platform implements Drawable {

    private final Rect rectangle;
    Paint fillPaint = new Paint();

    private final Player player;

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
            player.setCurrentPlatform(this);
        }

        canvas.drawRect(rectangle, fillPaint);
    }

    public Rect getRectangle() {
        return rectangle;
    }
}

