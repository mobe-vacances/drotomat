package m2dl.mobe.vacances.challenge.game_over;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.firebase.HighscoreHandler;
import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;
import m2dl.mobe.vacances.challenge.menu.MenuActivity;
import m2dl.mobe.vacances.challenge.scores.Score;
import m2dl.mobe.vacances.challenge.scores.ScoresActivity;

public class GameOverActivity extends SoundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Score score = new Score(
                getSharedPreferences("scores",MODE_PRIVATE).getString(
                        "username",
                        getResources().getString(R.string.default_player_name)),
                getIntent().getIntExtra("score",0)
        );

        HighscoreHandler.updateHighScore(score);

        ((TextView)findViewById(R.id.scoreView)).setText(
                getResources().getString(
                        R.string.game_over_your_score_is,
                        score.getValue()
                )
        );

        ((TextView)findViewById(R.id.highScoreView)).setText(
                getResources().getString(
                        R.string.game_over_your_highscore_is,
                        HighscoreHandler.getHighScore() == null? score.getValue() : Math.max(HighscoreHandler.getHighScore().getValue(),score.getValue())
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