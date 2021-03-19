package m2dl.mobe.vacances.challenge.game.background;

import android.graphics.Canvas;
import android.graphics.Color;

import m2dl.mobe.vacances.challenge.game.GameConstants;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;


public class Background implements Drawable, Updatable {

    private double intensity = 0.0;

    private double targetIntensity = 0.0;

    @Override
    public int getZIndex() {
        return GameConstants.BACKGROUND_Z_INDEX;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE
        );
    }

    @Override
    public void update(int delta) {

    }

}
