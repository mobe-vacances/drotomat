package m2dl.mobe.vacances.challenge.game.background;

import android.graphics.Canvas;
import android.graphics.Color;

import m2dl.mobe.vacances.challenge.game.GameConstants;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;


public class Background implements Drawable {

    @Override
    public int getZIndex() {
        return GameConstants.BACKGROUND_Z_INDEX;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
    }

}
