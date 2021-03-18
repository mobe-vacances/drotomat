package m2dl.mobe.vacances.challenge.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.credits.CreditsActivity;
import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.rules.RulesActivity;
import m2dl.mobe.vacances.challenge.scores.ScoresActivity;
import m2dl.mobe.vacances.challenge.settings.SettingsActivity;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        SharedPreferences preferences = getSharedPreferences("preferences",Context.MODE_PRIVATE);

        editText.setText(
                preferences.getString(
                        "username",
                        getResources().getString(R.string.default_player_name)
                )
        );

        editText.addTextChangedListener(new UsernameChangedListener(preferences));
    }

    public void launchGame(View v) {
        startActivity(new Intent(MenuActivity.this, GameActivity.class));
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