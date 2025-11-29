package com.example.ap_josephsmayckelhassenteufelmendieta_sensoresgeolocalizacion_291125;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap myMap;
    private Marker santaCruzMarker;
    private boolean isMoving = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        return view;
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        LatLng santaCruz = new LatLng(-17.7833, -63.1821);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(santaCruz));
        MarkerOptions options = new MarkerOptions().position(santaCruz).title("Santa Cruz Puej");
        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        santaCruzMarker = myMap.addMarker(options);
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(santaCruz, 14f));
    }

    public void updateMarkerOnMovement(boolean moving) {
        if (santaCruzMarker != null && myMap != null) {
            isMoving = moving;
            if (moving) {
                santaCruzMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                santaCruzMarker.setTitle("Santa Cruz Puej");
            } else {
                santaCruzMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                santaCruzMarker.setTitle("Santa Cruz se mueve");
            }
            if (santaCruzMarker.isInfoWindowShown()) {
                santaCruzMarker.showInfoWindow();
            }
        }
    }
}
