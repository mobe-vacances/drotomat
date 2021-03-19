package m2dl.mobe.vacances.challenge.scores;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.firebase.GlobalHighscoresHandler;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;

public class ScoresActivity extends SoundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ((ListView) findViewById(R.id.score_list)).setAdapter(
                new ScoreAdapter(this, GlobalHighscoresHandler.getScores())
        );
    }

    public void back(View view) {
        finish();
    }
}