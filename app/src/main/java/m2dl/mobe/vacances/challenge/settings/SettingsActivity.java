package m2dl.mobe.vacances.challenge.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.VibratorService;

public class SettingsActivity extends SoundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ((SeekBar)findViewById(R.id.volumeSeekBar)).setProgress((int) (SoundStore.getMasterVolume()*100));
        ((SeekBar)findViewById(R.id.volumeSeekBar)).setOnSeekBarChangeListener(new VolumeChangeListener(getSharedPreferences("settings", Context.MODE_PRIVATE)));

        ((Switch)findViewById(R.id.vibrationSwitch)).setChecked(VibratorService.isVibrationsActive());
        ((Switch)findViewById(R.id.vibrationSwitch)).setOnCheckedChangeListener(new VibrationChangeListener(getSharedPreferences("settings", Context.MODE_PRIVATE)));
    }

    public void back(View view) {
        finish();
    }
}