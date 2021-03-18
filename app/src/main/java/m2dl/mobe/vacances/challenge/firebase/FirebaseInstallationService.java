package m2dl.mobe.vacances.challenge.firebase;

import com.google.firebase.installations.FirebaseInstallations;

public class FirebaseInstallationService {

    private static String id;

    public static void init() {
        FirebaseInstallations.getInstance().getId()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        id = task.getResult();
                        HighscoreHandler.init(id);
                    }
                });
    }

    public static String getId() {
        return id;
    }
}
