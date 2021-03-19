package m2dl.mobe.vacances.challenge.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import m2dl.mobe.vacances.challenge.game.background.Background;
import m2dl.mobe.vacances.challenge.game.mobengine.activities.MobeGameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game_over.GameOverActivity;
import m2dl.mobe.vacances.challenge.level.PlateformeDure;
import m2dl.mobe.vacances.challenge.level.aPlateforme;
import m2dl.mobe.vacances.challenge.utils.XMLParser;

public class GameActivity extends MobeGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Background background = new Background();


        GameEngine.reset();
        GameEngine.addGameElements(
                background
        );
        readLevel();
        GameEngine.start();
    }


    private void readLevel(){
        XMLParser xmlParser = new XMLParser();
        System.out.println(xmlParser.read(this));

    }


    public void loose(View v) { // À ENLEVER QUAND IL Y AURA UN VRAI JEU
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        intent.putExtra("score", new Random().nextInt(10000));
        startActivity(intent);
        finish();
    }
}