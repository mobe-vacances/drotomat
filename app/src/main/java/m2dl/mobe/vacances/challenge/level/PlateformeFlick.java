package m2dl.mobe.vacances.challenge.level;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

import m2dl.mobe.vacances.challenge.game.flickPlateformes.Flicker;
import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;

public class PlateformeFlick extends aPlateforme {

    private boolean state;
    private int type;


    public PlateformeFlick(int left, int top, int right, int bottom, int type) {
        super(left, top, right, bottom);
        this.borderPaint = new Paint();
        if(type == 1){
            state = false;
            this.type = type;
        }
        else{
            state = true;
            this.type = type;
        }
        Flicker.addPlateforme(this);
    }

    public void initPaints() {

        // fill
        fillPaint.setStyle(Paint.Style.FILL);
        if(type == 1 && state) {
            fillPaint.setColor(Color.BLUE);
        }
        else if(type == 1 && !state){
            fillPaint.setColor(Color.TRANSPARENT);
        }
        else if (type == 2 && state) {
            fillPaint.setColor(Color.RED);
        }
        else if(type == 2 && !state){
            fillPaint.setColor(Color.TRANSPARENT);
        }

        borderPaint.reset();

        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5);
        if(!state){
            borderPaint.setARGB(255, 0, 0,0);
            borderPaint.setPathEffect(new DashPathEffect(new float[] {10f,20f}, 0f));
        }
        if(type == 1) {
            borderPaint.setColor(Color.BLUE);
        }
        else{
            borderPaint.setColor(Color.RED);
        }

    }

    public void switchPlateform(){
        this.state = !this.state;
    }

    @Override
    public void doSomething() {
        //switch state
    }

}
