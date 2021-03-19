package m2dl.mobe.vacances.challenge.onTouch;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.game.flickPlateformes.Flicker;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;
import m2dl.mobe.vacances.challenge.menu.MenuActivity;
import m2dl.mobe.vacances.challenge.pause.Exit;
import m2dl.mobe.vacances.challenge.rules.RulesActivity;


public class OnTouchListener implements View.OnTouchListener {

    GameActivity activity;
    static StateTouch stateTouch = StateTouch.WAITING_START;;
    private boolean oneTime = false;

    public OnTouchListener(GameActivity context) {
        this.activity = context;
        stateTouch = StateTouch.WAITING_START;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(stateTouch.equals(StateTouch.RUNNING)) {
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
        }
        else if(stateTouch.equals(StateTouch.WAITING_START)){
            activity.getPlayer().setCanMove(true);
            stateTouch = StateTouch.RUNNING;
        }
        else if(stateTouch.equals(StateTouch.WAITING_END)){
            activity.finish();
        }
        return true;
    }

    public static void setStateTouch(StateTouch stateTouch) {
        OnTouchListener.stateTouch = stateTouch;

    }
}


