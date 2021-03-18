package m2dl.mobe.vacances.challenge.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import m2dl.mobe.vacances.challenge.scores.Score;

public class GlobalHighscoresHandler {
    private static final List<Score> scores = new ArrayList<>();

    private static final DatabaseReference scoresReference = FirebaseDatabase.getInstance().getReference().child("scores");

    public static void init() {
        scoresReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                scores.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    try {
                        scores.add(postSnapshot.getValue(Score.class));
                    } catch (Exception ignored) {}
                }
                scores.sort((s1, s2) -> s2.getValue() - s1.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static List<Score> getScores() {
        return scores;
    }
}
