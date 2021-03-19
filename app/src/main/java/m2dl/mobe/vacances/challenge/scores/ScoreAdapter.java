package m2dl.mobe.vacances.challenge.scores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import m2dl.mobe.vacances.challenge.R;

public class ScoreAdapter extends ArrayAdapter<Score> {

    private Context context;

    private List<Score> scores;

    public ScoreAdapter(@NonNull Context context, @NonNull List<Score> objects) {
        super(context, 0, objects);
        this.context = context;
        this.scores = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.score_item, parent, false);
        }

        ((TextView) listItem.findViewById(R.id.level_item_index)).setText("#" + (position + 1));
        ((TextView) listItem.findViewById(R.id.score_item_name)).setText(scores.get(position).getName());
        ((TextView) listItem.findViewById(R.id.score_item_value)).setText("" + scores.get(position).getValue());

        return listItem;
    }
}
