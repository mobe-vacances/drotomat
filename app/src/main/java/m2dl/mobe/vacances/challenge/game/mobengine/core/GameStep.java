package m2dl.mobe.vacances.challenge.game.mobengine.core;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class GameStep implements Runnable {

    private final SurfaceHolder surfaceHolder;
    private final GameView gameView;
    private final GameEngine gameEngine;
    private Canvas canvas;

    private long lastTimeStamp = System.currentTimeMillis();

    public GameStep(SurfaceHolder surfaceHolder, GameView gameView, GameEngine gameEngine) {
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        long currentTimeStamp = System.currentTimeMillis();

        if(GameEngine.isRunning()) {

            int delta = (int) (currentTimeStamp - lastTimeStamp);

            gameEngine.addElementsToAdd();
            for (Updatable updatable : gameEngine.getUpdatables()) {
                updatable.update(delta);
            }
            gameEngine.removeElementsToRemove();
        }
        try {
            canvas = this.surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                    this.gameView.draw(canvas);
            }
        } catch (Exception ignored) {
        } finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(GameEngine.isRunning()){
            lastTimeStamp = currentTimeStamp;
        }else{
            lastTimeStamp= System.currentTimeMillis();
        }
    }
}
