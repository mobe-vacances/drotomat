package m2dl.mobe.vacances.challenge.onTouch;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import m2dl.mobe.vacances.challenge.level.Flicker;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.DisplayScale;
import m2dl.mobe.vacances.challenge.pause.Exit;


public class OnTouchListener implements View.OnTouchListener {

    Activity activity;

    public OnTouchListener(Activity context){
        this.activity = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                if (Exit.intersectsWithPause((int) (event.getX() / DisplayScale.getScale()), (int) (event.getY() / DisplayScale.getScale()))) {
                    activity.finish();
                }
        }

        if(event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            Flicker.switchAll();
        }
        else if(event.getAction() == MotionEvent.ACTION_UP){
            Flicker.switchAll();
        }

        return true;
    }
}
