package m2dl.mobe.vacances.challenge.game.mobengine.activities;

import androidx.appcompat.app.AppCompatActivity;

import m2dl.mobe.vacances.challenge.game.mobengine.utils.ActiveActivitiesTracker;


public class SoundAppCompatActivity extends AppCompatActivity {

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
