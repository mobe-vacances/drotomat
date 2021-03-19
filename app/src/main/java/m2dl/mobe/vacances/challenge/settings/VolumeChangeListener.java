package m2dl.mobe.vacances.challenge.settings;

import android.content.SharedPreferences;
import android.widget.SeekBar;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;

public class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    public VolumeChangeListener(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        SoundStore.setMasterVolume(progress / 100f);
        SoundStore.getMediaPlayerMap().get(R.raw.menu).setVolume(
                SoundStore.getMasterVolume()* Constants.VOLUME_MENU_MUSIC,
                SoundStore.getMasterVolume()* Constants.VOLUME_MENU_MUSIC
        );

        editor.putFloat("volume", progress / 100f);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        editor = preferences.edit();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        editor.apply();
    }
}
