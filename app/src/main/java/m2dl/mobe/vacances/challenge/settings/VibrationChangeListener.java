package m2dl.mobe.vacances.challenge.settings;

import android.content.SharedPreferences;
import android.widget.CompoundButton;

import m2dl.mobe.vacances.challenge.game.mobengine.utils.VibratorService;

public class VibrationChangeListener implements CompoundButton.OnCheckedChangeListener {

    private SharedPreferences sharedPreferences;

    public VibrationChangeListener(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        VibratorService.setVibrationsActive(isChecked);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("vibrations", isChecked);
        editor.apply();
    }
}
