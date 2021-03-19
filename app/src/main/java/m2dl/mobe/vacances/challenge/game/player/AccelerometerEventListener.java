package m2dl.mobe.vacances.challenge.game.player;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import java.util.Date;

import m2dl.mobe.vacances.challenge.game.Constants;
import m2dl.mobe.vacances.challenge.game.mobengine.sensors.BaseSensorEventListener;


public class AccelerometerEventListener extends BaseSensorEventListener {


    private Date lastShake;

    public AccelerometerEventListener() {
        super(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (shakeDetected(event) && hasElapsedEnoughTimeBetweenLastShake()) {
                lastShake = new Date();
                // TODO Add action performed when shake
            }
        }
    }

    private boolean shakeDetected(SensorEvent event) {
        return Math.abs(event.values[0]) > Constants.ACCELERATION_THRESHOLD ||
                Math.abs(event.values[1]) > Constants.ACCELERATION_THRESHOLD ||
                Math.abs(event.values[2]) > Constants.ACCELERATION_THRESHOLD;
    }

    private boolean hasElapsedEnoughTimeBetweenLastShake() {
        return lastShake == null || new Date().getTime() - lastShake.getTime() > Constants.TIME_BETWEEN_RESETS;
    }


}
