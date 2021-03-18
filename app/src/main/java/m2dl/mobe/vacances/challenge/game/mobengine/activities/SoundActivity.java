package m2dl.mobe.vacances.challenge.game.mobengine.activities;

import android.app.Activity;

import m2dl.mobe.vacances.challenge.game.mobengine.utils.ActiveActivitiesTracker;


public class SoundActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        ActiveActivitiesTracker.activityStarted();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActiveActivitiesTracker.activityStopped();
    }
}
