package m2dl.mobe.vacances.challenge.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.MobeGameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.SoundActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game.player.Player;
import m2dl.mobe.vacances.challenge.game_over.GameOverActivity;

public class GameActivity extends MobeGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameEngine.addGameElements(
                new Player(0,0)
        );

    }

    public void loose(View v) {
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        intent.putExtra("score", new Random().nextInt(10000));
        startActivity(intent);
        finish();
    }
}