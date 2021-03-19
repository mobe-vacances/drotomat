package m2dl.mobe.vacances.challenge.game.player;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import java.util.Date;

import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.mobengine.sensors.BaseSensorEventListener;
import m2dl.mobe.vacances.challenge.game.player.Player;

import static m2dl.mobe.vacances.challenge.game.Constants.LIGHT_THRESHOLD_PERCENT;


public class LightEventListener extends BaseSensorEventListener {

    private float maxLight = 0.0f;
    private final Player player;
    private Date lastEvent;

    private boolean dark = true;

    public LightEventListener(Player p) {
        super(Sensor.TYPE_LIGHT);
        player = p;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightIntensity = event.values[0];
            if (lightIntensity > maxLight) {
                maxLight = lightIntensity;
            }

            if (isLightBelowThreshold(lightIntensity) && hasElapsedEnoughTimeSinceLastEvent() && !dark) {
               dark = true;
               lastEvent = new Date();
               player.changeDirection();
           }else if (!isLightBelowThreshold(lightIntensity)) {
                dark = false;
            }
        }
    }

    private boolean isLightBelowThreshold(float lightIntensity) {
        return lightIntensity / maxLight < (LIGHT_THRESHOLD_PERCENT / 100f);
    }

    private boolean hasElapsedEnoughTimeSinceLastEvent() {
        return lastEvent == null || new Date().getTime() - lastEvent.getTime() > Constants.TIME_BETWEEN_LIGHT_EVENTS;
    }
}
