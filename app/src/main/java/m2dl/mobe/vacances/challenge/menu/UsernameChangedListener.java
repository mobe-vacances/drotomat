package m2dl.mobe.vacances.challenge.menu;

import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;

public class UsernameChangedListener implements TextWatcher {

    private final SharedPreferences preferences;

    public UsernameChangedListener(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.length() > 0) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", s.toString());
            editor.apply();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
