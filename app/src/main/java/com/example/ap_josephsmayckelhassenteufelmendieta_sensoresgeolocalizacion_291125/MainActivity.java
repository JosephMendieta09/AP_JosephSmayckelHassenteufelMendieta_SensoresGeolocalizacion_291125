package com.example.ap_josephsmayckelhassenteufelmendieta_sensoresgeolocalizacion_291125;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements AcelerometroFragment.OnMovementDetectedListener{

    private MapaFragment mapaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapaFragment = (MapaFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMapa);
    }

    @Override
    public void onMovementDetected(boolean isMoving) {
        if (mapaFragment != null) {
            mapaFragment.updateMarkerOnMovement(isMoving);
        }
    }
}