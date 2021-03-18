package m2dl.mobe.vacances.challenge.game.mobengine.core;

import android.graphics.Canvas;

public interface Drawable {

    int getZIndex();
    void draw(Canvas canvas);
}
