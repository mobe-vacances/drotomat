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

    private Rect rect;

    private boolean jumping = true;

    private boolean canMove;

    private final Paint paint = new Paint();


    private Platform currentPlatform = null;

    public Player() {

        this.canMove = false;

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
                    Bitmap.createScaledBitmap(BitmapStore.getBitmap(xSpeed < 0 ? R.drawable.player_jump : R.drawable.player_jump_revert), rect.width(), rect.height(), false),
                    rect.left, rect.top,
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
                        Bitmap.createScaledBitmap(BitmapStore.getBitmap(incressStep()), rect.width(), rect.height(), false),
                        rect.left, rect.top,
                        null
                );
            }
        }
    }

    private int incressStep() {
        imageStep = ((imageStep + 1) % (PAS * 3)) + 1;
        switch (Math.floorDiv(imageStep, PAS)) {
            case 0:
                return xSpeed > 0 ? R.drawable.player_2 : R.drawable.player_2_revert;
            case 1:
                return xSpeed > 0 ? R.drawable.player_3 : R.drawable.player_3_revert;
            case 2:
                return xSpeed > 0 ? R.drawable.player_1 : R.drawable.player_1_revert;
            default:
                return xSpeed > 0 ? R.drawable.player_1 : R.drawable.player_1_revert;
        }
    }

    @Override
    public void update(int delta) {
        if(!canMove)
            return;
        yAcceleration = Math.min(0, yAcceleration + Constants.PLAYER_Y_INERTIA * delta);

        xSpeed = Math.max(-1*Constants.PLAYER_MAX_X_SPEED, Math.min(xSpeed + xAcceleration*delta, Constants.PLAYER_MAX_Y_SPEED));

        if(currentPlatform == null || !Rect.intersects(rect, currentPlatform.getRectangle())) {
            jumping = true;
        }

        if(jumping) {
            ySpeed = Math.max(-1*Constants.PLAYER_MAX_Y_SPEED, Math.min(ySpeed + Constants.PLAYER_GRAVITY*delta + yAcceleration*delta, Constants.PLAYER_MAX_Y_SPEED));
            if(ySpeed > 0 && currentPlatform != null && currentPlatform.getRectangle().top <= rect.bottom && rect.right > currentPlatform.getRectangle().left && rect.left < currentPlatform.getRectangle().right) {
                ySpeed = 0;
                jumping = false;
                y = currentPlatform.getRectangle().top - Constants.PLAYER_HEIGHT;
                //jump();
            }
        }

        x += xSpeed * delta;
        y += ySpeed * delta;

        rect.offsetTo((int) x, (int) y);
    }

    public void jump() {
        if (ySpeed == 0) {
            currentPlatform = null;
            jumping = true;
            yAcceleration = Constants.PLAYER_JUMP_ACCELERATION;
        }
    }

    public void changeDirection() {
        xAcceleration = -1 * xAcceleration;
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

    public void setCurrentPlatform(Platform currentPlatform) {
        this.currentPlatform = currentPlatform;
    }

    public void setXY(float x, float y){
        this.x = x;
        this.y = y;
        this.rect = new Rect(
                (int) x,
                (int) y,
                (int) x + Constants.PLAYER_WIDTH,
                (int) y + Constants.PLAYER_HEIGHT
        );
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isCanMove(){
        return this.canMove;
    }

}
