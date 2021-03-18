package m2dl.mobe.vacances.challenge.game.mobengine.resource_stores;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

public class BitmapStore {

    private static Map<Integer, Bitmap> bitmapMap;

    public static void decodeBitmaps(int[] bitmapIds, Resources resources) {
        bitmapMap = new HashMap<>();
        for(int bitmapId : bitmapIds) {
            bitmapMap.put(bitmapId, BitmapFactory.decodeResource(resources, bitmapId));
        }
    }

    public static Bitmap getBitmap(int bitmapId) {
        return bitmapMap.get(bitmapId);
    }

}
