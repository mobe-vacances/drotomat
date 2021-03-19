package m2dl.mobe.vacances.challenge.game.end_animation;

import android.graphics.Canvas;
import android.graphics.Paint;

import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;

public class Particle implements Drawable, Updatable {

    private final Paint paint;

    private final float radius;

    private float x;
    private float y;

    private double speed;
    private final double direction;


    public Particle(float x, float y, float radius, double speed, double direction, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speed = speed;
        this.direction = direction;
        this.paint = new Paint();
        this.paint.setColor(color);
    }

    @Override
    public int getZIndex() {
        return 1;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void update(int delta) {
        speed = Math.max(speed - Constants.PARTICLE_DECELERATION*delta,0);

        x += speed*Math.cos(direction)*delta;
        y += speed*Math.sin(direction)*delta;

        if(speed == 0 || !DisplayScale.getRect().intersects((int)(x - radius),(int)(y - radius), (int)(x + radius), (int)(y + radius))) {
            GameEngine.removeGameElement(this);
        }
    }
}
