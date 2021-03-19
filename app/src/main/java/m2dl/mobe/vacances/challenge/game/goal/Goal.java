package m2dl.mobe.vacances.challenge.game.goal;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.end_animation.Particle;
import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Drawable;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.RandomService;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;
import m2dl.mobe.vacances.challenge.game.player.Player;
import m2dl.mobe.vacances.challenge.onTouch.OnTouchListener;
import m2dl.mobe.vacances.challenge.onTouch.StateTouch;

public class Goal implements Drawable, Updatable {

    private Player player;
    private Rect hitbox;
    private Paint fillPaint;

    public Goal(Player player,int left,int top, int right, int bot){
        this.hitbox = new Rect(left,top,right,bot);
        this.player = player;
        this.fillPaint = new Paint();
    }


    @Override
    public int getZIndex() {
        return 5;
    }

    public void initPaints() {
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.YELLOW);
    }

    @Override
    public void draw(Canvas canvas) {
        initPaints();
        canvas.drawRect(hitbox, fillPaint);
    }

    @Override
    public void update(int delta) {
        if(Rect.intersects(hitbox, player.getRect())) {
            spawnVictoryParticles(player.getRect().exactCenterX(), player.getRect().exactCenterY());
        }

        if(player.isCanMove() && this.hitbox.intersect(player.getRect())){
            SoundStore.stopLoopedSound(R.raw.game_midi);
            SoundStore.playSound(R.raw.victory,100);
            player.setCanMove(false);
            OnTouchListener.setStateTouch(StateTouch.WAITING_END);
        }
    }

    private static void spawnVictoryParticles(float x, float y) {
        for (int i = Constants.PLAYER_DEATH_PARTICLE_NUMBER; i > 0; i--) {
            GameEngine.addGameElements(new Particle(
                    x,
                    y,
                    RandomService.nextFloatBetween(Constants.PLAYER_DEATH_PARTICLE_MIN_RADIUS, Constants.PLAYER_DEATH_PARTICLE_MAX_RADIUS),
                    RandomService.nextDoubleBetween(Constants.PLAYER_DEATH_PARTICLE_MIN_SPEED, Constants.PLAYER_DEATH_PARTICLE_MAX_SPEED),
                    2 * Math.PI * RandomService.get().nextDouble(),
                    Constants.VICTORY_PARTICLE_COLORS[RandomService.get().nextInt(Constants.VICTORY_PARTICLE_COLORS.length)]
            ));
        }
    }
}
