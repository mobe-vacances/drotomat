package m2dl.mobe.vacances.challenge.onTouch;

import android.view.MotionEvent;
import android.view.View;

import m2dl.mobe.vacances.challenge.game.flickPlateformes.Flicker;

public class onTouchListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
            Flicker.switchAll();
        }
            else if(event.getAction() == MotionEvent.ACTION_UP){
            Flicker.switchAll();
        }
        return true;
    }
}
