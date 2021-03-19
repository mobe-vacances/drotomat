package m2dl.mobe.vacances.challenge.credits;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;

public class CreditsActivity extends SoundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }

    public void back(View view) {
        finish();
        SoundStore.playSound(R.raw.click,100);
    }
}