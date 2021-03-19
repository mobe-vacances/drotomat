package m2dl.mobe.vacances.challenge.game.player;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;

import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;

public class Player implements Drawable, Updatable {

    private float x;
    private float y;

    private float xSpeed;
    private float ySpeed;

    private float xAcceleration;
    private float yAcceleration;

    private Rect rect;

    private final Paint paint = new Paint();

    public Player(float x, float y) {
        this.x = x;
        this.y = y;

        rect = new Rect(
                (int) x,
                (int) y,
                (int) x + Constants.PLAYER_WIDTH,
                (int) y + Constants.PLAYER_HEIGHT
        );

        paint.setColor(Color.RED);

        new Handler().postDelayed(this::jump, 5000);
    }

    @Override
    public int getZIndex() {
        return Constants.Z_INDEX_PLAYER;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update(int delta) {

        yAcceleration = Math.min(0, yAcceleration + Constants.PLAYER_Y_INERTIA*delta);

        xSpeed = Math.max(-1*Constants.PLAYER_MAX_X_SPEED, Math.min(xSpeed + xAcceleration*delta, Constants.PLAYER_MAX_Y_SPEED));
        ySpeed = Math.max(-1*Constants.PLAYER_MAX_Y_SPEED, Math.min(ySpeed + Constants.PLAYER_GRAVITY*delta + yAcceleration*delta, Constants.PLAYER_MAX_Y_SPEED));

        x += xSpeed * delta;
        y = Math.min(y + ySpeed * delta, DisplayScale.getRect().bottom - Constants.PLAYER_HEIGHT) ;


        rect.offsetTo((int)x,(int)y);
    }

    public void jump() {
        yAcceleration = Constants.PLAYER_JUMP_ACCELERATION;
    }
}
