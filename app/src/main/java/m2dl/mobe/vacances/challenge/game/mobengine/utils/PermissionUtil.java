package m2dl.mobe.vacances.challenge.game.mobengine.utils;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionUtil {

    public static void checkAndRequestAllPermissions(Activity activity) {
        String[] requestedPermissions = new String[0];
        try {
            requestedPermissions = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        for(String perm : requestedPermissions) {

            if (ActivityCompat.checkSelfPermission(activity, perm) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        activity,
                        new String[]{perm},
                        101
                );
            }
        }
    }
}
