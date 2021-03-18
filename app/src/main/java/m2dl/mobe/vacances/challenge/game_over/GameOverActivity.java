package m2dl.mobe.vacances.challenge.game_over;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.menu.MenuActivity;
import m2dl.mobe.vacances.challenge.scores.ScoresActivity;

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        ((TextView)findViewById(R.id.scoreView)).setText(
                getResources().getString(
                        R.string.game_over_your_score_is,
                        getIntent().getIntExtra("score",0)
                )
        );

    }

    public void launchScores(View v) {
        startActivity(new Intent(GameOverActivity.this, ScoresActivity.class));
    }

    public void back(View v) {
        finish();
    }

    public void restart(View v) {
        startActivity(new Intent(GameOverActivity.this, GameActivity.class));
        finish();
    }
}