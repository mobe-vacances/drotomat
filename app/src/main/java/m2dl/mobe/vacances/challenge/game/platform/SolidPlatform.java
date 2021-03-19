package m2dl.mobe.vacances.challenge.game.platform;


import android.graphics.Color;
import android.graphics.Paint;

import m2dl.mobe.vacances.challenge.game.player.Player;

public class SolidPlatform extends Platform {

    public SolidPlatform(int left, int top, int right, int bottom, Player player) {
        super(left, top, right, bottom, player);
    }

    @Override
    public void initPaints() {
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.YELLOW);
    }

}
