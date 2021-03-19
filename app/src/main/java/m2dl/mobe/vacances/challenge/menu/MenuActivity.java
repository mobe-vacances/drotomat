package m2dl.mobe.vacances.challenge.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import m2dl.mobe.vacances.challenge.Levels;
import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.credits.CreditsActivity;
import m2dl.mobe.vacances.challenge.firebase.FirebaseInstallationService;
import m2dl.mobe.vacances.challenge.firebase.GlobalHighscoresHandler;
import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.BitmapStore;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.PermissionUtil;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.VibratorService;
import m2dl.mobe.vacances.challenge.rules.RulesActivity;
import m2dl.mobe.vacances.challenge.scores.ScoresActivity;
import m2dl.mobe.vacances.challenge.settings.SettingsActivity;

public class MenuActivity extends SoundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        SharedPreferences preferences = getSharedPreferences("scores",Context.MODE_PRIVATE);

        editText.setText(
                preferences.getString(
                        "username",
                        getResources().getString(R.string.default_player_name)
                )
        );

        editText.addTextChangedListener(new UsernameChangedListener(preferences));

        PermissionUtil.checkAndRequestAllPermissions(this);
        VibratorService.requestVibrator(this);
        BitmapStore.decodeBitmaps(Constants.USED_BITMAPs_IDS, getResources());
        SoundStore.createMediaPlayers(Constants.USED_SOUNDS_IDS, this);
        FirebaseInstallationService.init();
        GlobalHighscoresHandler.init();

        SharedPreferences settingsPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        SoundStore.setMasterVolume(settingsPreferences.getFloat("volume", 1f));
        VibratorService.setVibrationsActive(settingsPreferences.getBoolean("vibrations", true));

        SoundStore.loopSound(R.raw.menu, Constants.VOLUME_MENU_MUSIC);
    }

    public void launchLevels(View v) {
        startActivity(new Intent(MenuActivity.this, Levels.class));
        VibratorService.heavyClick();
    }

    public void launchRules(View v) {
        startActivity(new Intent(MenuActivity.this, RulesActivity.class));
    }

    public void launchScores(View v) {
        startActivity(new Intent(MenuActivity.this, ScoresActivity.class));
    }

    public void launchSettings(View v) {
        startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
    }

    public void launchCredits(View v) {
        startActivity(new Intent(MenuActivity.this, CreditsActivity.class));
    }
}