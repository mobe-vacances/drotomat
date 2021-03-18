package m2dl.mobe.vacances.challenge.game.mobengine.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private final GameEngine gameEngine;
    private ScheduledExecutorService executor;

    public GameView(Context context, GameEngine gameEngine) {
        super(context);
        this.gameEngine = gameEngine;
        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new GameStep(getHolder(), this, gameEngine),0, 16, TimeUnit.MILLISECONDS);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Point size = new Point();
        size.x = width;
        size.y = height;
        DisplayScale.updateScale(size);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        executor.shutdown();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.scale(DisplayScale.getScale(), DisplayScale.getScale());
        for(Drawable drawable : gameEngine.getDrawables()) {
            drawable.draw(canvas);
        }
    }
}
