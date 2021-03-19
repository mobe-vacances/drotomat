package m2dl.mobe.vacances.challenge.game.mobengine.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import static m2dl.mobe.vacances.challenge.game.Constants.LIGHT_THRESHOLD_PERCENT;


public class LightEventListener extends BaseSensorEventListener {

    private float maxLight = 0.0f;


    public LightEventListener() {
        super(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightIntensity = event.values[0];
            if (lightIntensity > maxLight) {
                maxLight = lightIntensity;
            }
            System.out.println(isLightBelowThreshold(lightIntensity));
            // TODO Add action performed when light below threshold
        }
    }

    private boolean isLightBelowThreshold(float lightIntensity) {
        return lightIntensity / maxLight < (LIGHT_THRESHOLD_PERCENT / 100f);
    }

}
