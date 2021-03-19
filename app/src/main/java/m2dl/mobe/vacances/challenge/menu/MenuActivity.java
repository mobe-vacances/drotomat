package m2dl.mobe.vacances.challenge.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import m2dl.mobe.vacances.challenge.Levels;
import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.credits.CreditsActivity;
import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.BitmapStore;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.PermissionUtil;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.VibratorService;
import m2dl.mobe.vacances.challenge.rules.RulesActivity;
import m2dl.mobe.vacances.challenge.settings.SettingsActivity;

public class MenuActivity extends SoundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        PermissionUtil.checkAndRequestAllPermissions(this);
        VibratorService.requestVibrator(this);
        BitmapStore.decodeBitmaps(Constants.USED_BITMAPs_IDS, getResources());
        SoundStore.createMediaPlayers(Constants.USED_SOUNDS_IDS, this);
        SoundStore.createMediaPlayers(new int[]{R.raw.click},this);

        SharedPreferences settingsPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SoundStore.setMasterVolume(settingsPreferences.getFloat("volume", 1f));
        VibratorService.setVibrationsActive(settingsPreferences.getBoolean("vibrations", true));

        SoundStore.loopSound(R.raw.menu, Constants.VOLUME_MENU_MUSIC);
    }

    public void launchLevels(View v) {
        startActivity(new Intent(MenuActivity.this, Levels.class));
        VibratorService.heavyClick();
        SoundStore.playSound(R.raw.click,100);
    }

    public void launchRules(View v) {
        SoundStore.playSound(R.raw.click,100);
        startActivity(new Intent(MenuActivity.this, RulesActivity.class));
    }

    public void launchSettings(View v) {
        SoundStore.playSound(R.raw.click,100);
        startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
    }

    public void launchCredits(View v) {
        SoundStore.playSound(R.raw.click,100);
        startActivity(new Intent(MenuActivity.this, CreditsActivity.class));
    }
}