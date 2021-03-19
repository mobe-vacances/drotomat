package m2dl.mobe.vacances.challenge.game.player;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.BitmapStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;
import m2dl.mobe.vacances.challenge.game.platform.Platform;

public class Player implements Drawable, Updatable {

    private float x;
    private float y;

    private float xSpeed;
    private float ySpeed;

    private float xAcceleration = Constants.PLAYER_X_ACCELERATION;
    private float yAcceleration;

    private int direction = 1;

    private Rect rect;

    private boolean jumping = true;

    private final Paint paint = new Paint();

    private final List<Platform> currentPlatforms = new ArrayList<>();

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

        imageStep = 1;

    }

    int imageStep;
    private final static int PAS = 10;

    @Override
    public int getZIndex() {
        return Constants.Z_INDEX_PLAYER;
    }

    @Override
    public void draw(Canvas canvas) {
        if (ySpeed != 0f) {
            canvas.drawBitmap(
                    Bitmap.createScaledBitmap(BitmapStore.getBitmap(xSpeed < 0 ? R.drawable.player_jump : R.drawable.player_jump_revert), rect.width()+20, rect.height(), false),
                    rect.left-10, rect.top,
                    null
            );
        } else {
            if (xSpeed > 0) {
                canvas.drawBitmap(
                        Bitmap.createScaledBitmap(BitmapStore.getBitmap(incressStep()), rect.width()+20, rect.height(), false),
                        rect.left-10, rect.top,
                        null
                );
            } else {
                canvas.drawBitmap(
                        Bitmap.createScaledBitmap(BitmapStore.getBitmap(incressStep()), rect.width()+20, rect.height(), false),
                        rect.left-10, rect.top,
                        null
                );
            }
        }
    }

    private int incressStep() {
        imageStep = ((imageStep + 1) % (PAS * 3)) + 1;
        switch (Math.floorDiv(imageStep, PAS)) {
            case 0:
                return xAcceleration > 0 ? R.drawable.player_2 : R.drawable.player_2_revert;
            case 1:
                return xAcceleration > 0 ? R.drawable.player_3 : R.drawable.player_3_revert;
            case 2:
                return xAcceleration > 0 ? R.drawable.player_1 : R.drawable.player_1_revert;
            default:
                return xAcceleration > 0 ? R.drawable.player_1 : R.drawable.player_1_revert;
        }
    }

    @Override
    public void update(int delta) {
        yAcceleration = Math.min(0, yAcceleration + Constants.PLAYER_Y_INERTIA * delta);

        xSpeed = Math.max(-1*Constants.PLAYER_MAX_X_SPEED, Math.min(xSpeed + direction*xAcceleration*delta, Constants.PLAYER_MAX_Y_SPEED));
        ySpeed = Math.max(-1*Constants.PLAYER_MAX_Y_SPEED, Math.min(ySpeed + Constants.PLAYER_GRAVITY*delta + yAcceleration*delta, Constants.PLAYER_MAX_Y_SPEED));

        for(Platform platform : currentPlatforms) {
            if(ySpeed > 0 && platform.getRectangle().top < rect.bottom && rect.right > platform.getRectangle().left && rect.left < platform.getRectangle().right) {
                ySpeed = 0;
                y = platform.getRectangle().top - Constants.PLAYER_HEIGHT + 1;
                rect.offsetTo((int) x, (int) y);
            }
        }

        x = Math.max(0, Math.min(x + xSpeed * delta, DisplayScale.getRect().right - Constants.PLAYER_WIDTH));

        if(x == 0f || x == DisplayScale.getRect().right - Constants.PLAYER_WIDTH) {
            xSpeed = 0;
        }

        y += ySpeed * delta;

        rect.offsetTo((int) x, (int) y);

        if(!Rect.intersects(rect, DisplayScale.getRect())) {
            System.out.println("Perdu");
        }
    }

    public void jump() {
        if (ySpeed == 0) {
            yAcceleration = Constants.PLAYER_JUMP_ACCELERATION;
        }
    }

    public void changeDirection() {
        direction = -1 * direction;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public Rect getRect() {
        return rect;
    }

    public void addCurrentPlatform(Platform currentPlatform) {
        this.currentPlatforms.add(currentPlatform);
    }

    public void removeCurrentPlatform(Platform platform) {
        this.currentPlatforms.remove(platform);
    }

    public void setX(float x) {
        this.x = x;
        this.rect.offsetTo((int)x,(int)y);
    }

    public void setY(float y) {
        this.y = y;
        this.rect.offsetTo((int)x,(int)y);
    }
}
