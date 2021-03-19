package m2dl.mobe.vacances.challenge.onTouch;

import android.view.MotionEvent;
import android.view.View;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;
import m2dl.mobe.vacances.challenge.interaction.Exit;
import m2dl.mobe.vacances.challenge.game.platform.Flicker;
import m2dl.mobe.vacances.challenge.interaction.Press;


public class OnTouchListener implements View.OnTouchListener {

    GameActivity activity;
    public static StateTouch stateTouch = StateTouch.WAITING_START;
    ;
    private boolean oneTime = false;

    public OnTouchListener(GameActivity context) {
        this.activity = context;
        stateTouch = StateTouch.WAITING_START;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Press.visible = false;
        if (stateTouch.equals(StateTouch.RUNNING)) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (Exit.intersectsWithPause((int) (event.getX() / DisplayScale.getScale()), (int) (event.getY() / DisplayScale.getScale()))) {
                    activity.finish();
                }
            }

            if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                oneTime = true;
                Flicker.switchAll();
            } else if (event.getAction() == MotionEvent.ACTION_UP && oneTime) {
                Flicker.switchAll();
            }
        } else if (stateTouch.equals(StateTouch.WAITING_START)) {
            SoundStore.playSound(R.raw.start, 80);
            activity.getPlayer().setCanMove(true);
            stateTouch = StateTouch.RUNNING;
        } else if (stateTouch.equals(StateTouch.WAITING_END)) {
            SoundStore.stopLoopedSound(R.raw.victory);
            SoundStore.stopLoopedSound(R.raw.game_midi);
            SoundStore.playSound(R.raw.menu, 100);
            activity.finish();
        }
        return true;
    }

    public static void setStateTouch(StateTouch stateTouch) {
        OnTouchListener.stateTouch = stateTouch;

    }
}


