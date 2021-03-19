package m2dl.mobe.vacances.challenge.game.player;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;

import java.util.Date;

import m2dl.mobe.vacances.challenge.R;
import m2dl.mobe.vacances.challenge.game.GameActivity;
import m2dl.mobe.vacances.challenge.game.mobengine.sensors.BaseSensorEventListener;


public class AccelerometerEventListener extends BaseSensorEventListener {

    private static final int ACCELERATION_THRESHOLD = 15;
    private static final long TIME_BETWEEN_RESETS = 500;

    private Date lastShake;
    private final GameActivity gameActivity;

    public AccelerometerEventListener(GameActivity game) {
        super(Sensor.TYPE_ACCELEROMETER);
        this.gameActivity = game;
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
        return Math.abs(event.values[0]) > ACCELERATION_THRESHOLD ||
                Math.abs(event.values[1]) > ACCELERATION_THRESHOLD ||
                Math.abs(event.values[2]) > ACCELERATION_THRESHOLD;
    }

    private boolean hasElapsedEnoughTimeBetweenLastShake() {
        return lastShake == null || new Date().getTime() - lastShake.getTime() > TIME_BETWEEN_RESETS;
    }


}
