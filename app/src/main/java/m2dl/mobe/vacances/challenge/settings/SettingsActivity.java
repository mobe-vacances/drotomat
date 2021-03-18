package m2dl.mobe.vacances.challenge.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;

public class SettingsActivity extends SoundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void back(View view) {
        finish();
    }
}