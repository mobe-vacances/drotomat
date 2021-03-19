package m2dl.mobe.vacances.challenge.game.platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;

import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.player.Player;

public class PlateformeFlick extends Platform {

    private boolean state;
    private int type;


    public PlateformeFlick(int left, int top, int right, int bottom, int type, Player player) {
        super(left, top, right, bottom, player);
        this.borderPaint = new Paint();
        state = (type == 1);
        this.type = type;
        Flicker.addPlateforme(this);
    }

    public void initPaints() {
        borderPaint.reset();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5);
        fillPaint.setStyle(Paint.Style.FILL);

        if(!state) {
            borderPaint.setPathEffect(new DashPathEffect(new float[] {10f,20f}, 0f));
            fillPaint.setColor(Color.TRANSPARENT);
            borderPaint.setColor(type == 1 ? Constants.FLICK_PLATFORM_COLOR_1 : Constants.FLICK_PLATFORM_COLOR_2);
        } else {
            borderPaint.setColor(Color.TRANSPARENT);
            fillPaint.setColor(type == 1 ? Constants.FLICK_PLATFORM_COLOR_1 : Constants.FLICK_PLATFORM_COLOR_2);
        }
    }

    public void switchPlateform(){
        this.state = !this.state;
    }

    @Override
    public void draw(Canvas canvas) {
        if (state && Rect.intersects(player.getRect(), rectangle) && player.getRect().bottom < rectangle.bottom) {
            player.addCurrentPlatform(this);
        } else {
            player.removeCurrentPlatform(this);
        }

        initPaints();
        canvas.drawRect(rectangle, fillPaint);

        if(borderPaint != null) {
            canvas.drawRect(rectangle, borderPaint);
        }
    }

}
