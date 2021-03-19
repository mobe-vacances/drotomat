package m2dl.mobe.vacances.challenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.core.GameEngine;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.BitmapStore;
import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;
import m2dl.mobe.vacances.challenge.game.mobengine.utils.VibratorService;

public class Levels extends AppCompatActivity {

    private class LevelAdapter extends ArrayAdapter {

        private Context mContext;
        private int PAS = 4;

        public LevelAdapter(@NonNull Context context) {
            super(context, 0 , levels);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItem = convertView;
            if(listItem == null) {
                listItem = LayoutInflater.from(mContext).inflate(R.layout.level_item, parent, false);
            }
            ((TextView) listItem.findViewById(R.id.level_item_index)).setText("Level " + (position + 1) );
            ((ImageView) listItem.findViewById(R.id.levelImage)).setImageBitmap(BitmapStore.getBitmap(getImage(position)));
            return listItem;
        }

        private int getImage(int position){
            switch (position % PAS){
                case 0:
                    return R.drawable.level1;
                case 1:
                    return R.drawable.level2;
                case 2:
                    return R.drawable.level3;
                default:
                    return R.drawable.level4;
            }
        }

    }
    List<Integer> levels ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        levels = new ArrayList<>();
        levels.add(R.xml.level);
        levels.add(R.xml.level2);
        ListView listView = findViewById(R.id.levels_list);
        LevelAdapter arrayAdapter = new LevelAdapter(this);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchGame(view, levels.get(position));
            }
        });
    }


    public void launchGame(View v, Integer level) {
        GameEngine.level = level;
        startActivity(new Intent(Levels.this, GameActivity.class));
        VibratorService.heavyClick();
    }

    public void back(View view) {
        finish();
    }

    public void launchLevels(View v) {
        startActivity(new Intent(Levels.this, GameActivity.class));
        VibratorService.heavyClick();
        SoundStore.stopLoopedSound(R.raw.menu);
    }


}