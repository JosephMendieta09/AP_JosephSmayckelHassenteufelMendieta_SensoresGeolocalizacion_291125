package com.example.ap_josephsmayckelhassenteufelmendieta_sensoresgeolocalizacion_291125;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class AcelerometroFragment extends Fragment implements SensorEventListener {
    TextView xCoor, yCoor, zCoor;
    SensorManager gestorSensores;
    Sensor acelerometro;

    private static final float MOVEMENT_THRESHOLD = 1.5f;
    private OnMovementDetectedListener movementListener;
    private boolean isMoving = false;

    public interface OnMovementDetectedListener {
        void onMovementDetected(boolean isMoving);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            movementListener = (OnMovementDetectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " debe implementar OnMovementDetectedListener");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acelerometro, container, false);

        xCoor = view.findViewById(R.id.xcoor);
        yCoor = view.findViewById(R.id.ycoor);
        zCoor = view.findViewById(R.id.zcoor);

        gestorSensores = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        acelerometro = gestorSensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return view;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onResume() {
        super.onResume();
        gestorSensores.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        gestorSensores.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            xCoor.setText(String.format("X: %.2f", x));
            yCoor.setText(String.format("Y: %.2f", y));
            zCoor.setText(String.format("Z: %.2f", z));

            float movement = Math.abs(x) + Math.abs(y) + Math.abs(z - SensorManager.GRAVITY_EARTH);

            boolean currentlyMoving = movement > MOVEMENT_THRESHOLD;

            if (currentlyMoving != isMoving) {
                isMoving = currentlyMoving;
                if (movementListener != null) {
                    movementListener.onMovementDetected(isMoving);
                }
            }
        }
    }
}
