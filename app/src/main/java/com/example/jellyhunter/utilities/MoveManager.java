package com.example.jellyhunter.utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.jellyhunter.interfaces.SensorCallback;

public class MoveManager {
    private SensorCallback sensorCallback;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    private boolean checkX = false;
    private boolean checkY = false;

    private long movementTimestamp = 0L;
    private float lastX = 0;
    private float threshhold = 1.0f;
    private int moveTimeDelay = 100;
    private long speedTimestamp = 0L;
    private int speedTimeDelay = 100;

    public MoveManager(Context context, SensorCallback sensorCallback) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.sensorCallback = sensorCallback;
        initListener();
    }

    private void initListener() {
        this.sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                if (checkX) calculateMove(x);
                if (checkY) calculateSpeed(y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        };
    }

    private void calculateMove(float x) {
        if (isChanged(x) && System.currentTimeMillis() - movementTimestamp > moveTimeDelay) {
            movementTimestamp = System.currentTimeMillis();
            if (sensorCallback != null)
                if (x > 4.0)
                    sensorCallback.Move("left");
                else if (x < -4.0)
                    sensorCallback.Move("right");
        }
    }

    private boolean isChanged(float x) {
        if (x > lastX - threshhold && x < lastX + threshhold)
            return false;
        this.lastX = x;
        return true;
    }

    private void calculateSpeed(float y) {
        if (System.currentTimeMillis() - speedTimestamp > speedTimeDelay) {
            speedTimestamp = System.currentTimeMillis();
            if (sensorCallback != null)
                if (y > 8.0)
                    sensorCallback.ChangeSpeed(50);
                else if (y < 4.0)
                    sensorCallback.ChangeSpeed(-50);
        }
    }

    public void start() {
        sensorManager.registerListener(
          sensorEventListener,
          sensor,
          SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop() {
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }

    public void stopX() {
        checkX = false;
    }
    public void startX() {
        checkX = true;
    }
    public void stopY() {
        checkY = false;
    }
    public void startY() {
        checkY = true;
    }
}
