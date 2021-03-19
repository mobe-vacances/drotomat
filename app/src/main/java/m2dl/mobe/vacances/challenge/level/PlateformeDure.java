package m2dl.mobe.vacances.challenge.level;

import android.graphics.Color;
import android.graphics.Paint;

public class PlateformeDure extends aPlateforme{


    public PlateformeDure(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    @Override
    public void initPaints() {
            fillPaint.setStyle(Paint.Style.FILL);
            fillPaint.setColor(Color.YELLOW);

    }

    @Override
    public void doSomething() {
        //rien ici
    }
}
