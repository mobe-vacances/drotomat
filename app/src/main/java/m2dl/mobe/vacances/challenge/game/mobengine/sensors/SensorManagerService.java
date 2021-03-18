package m2dl.mobe.vacances.challenge.game.mobengine.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SensorManagerService {

    private static SensorManager sensorManager;

    private static final List<BaseSensorEventListener> eventListeners = new ArrayList<>();

    public static void requestSensorManager(Context context) {
        unregisterListeners();
        eventListeners.clear();

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public static Sensor getDefaultSensor(int type) {
        if(sensorManager != null) {
            return sensorManager.getDefaultSensor(type);
        }
        return null;
    }

    public static void addSensorListeners(BaseSensorEventListener... baseSensorEventListeners) {
        eventListeners.addAll(Arrays.asList(baseSensorEventListeners));
    }

    public static void registerListeners() {
        for (BaseSensorEventListener baseSensorEventListener : eventListeners) {
            sensorManager.registerListener(baseSensorEventListener, baseSensorEventListener.getSensor(), SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public static void unregisterListeners() {
        for (BaseSensorEventListener baseSensorEventListener : eventListeners) {
            sensorManager.unregisterListener(baseSensorEventListener);
        }
    }

    public static SensorManager getSensorManager() {
        return sensorManager;
    }

    public static List<BaseSensorEventListener> getEventListeners() {
        return eventListeners;
    }
}
